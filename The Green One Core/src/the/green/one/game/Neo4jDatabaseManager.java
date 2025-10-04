package the.green.one.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.Neo4jException;
import org.neo4j.driver.exceptions.SessionExpiredException;

public class Neo4jDatabaseManager implements AutoCloseable {

    private static Neo4jDatabaseManager neo4jdatabase;
    private static final Logger LOGGER = Logger.getLogger(Neo4jDatabaseManager.class.getName());
    private final Driver driver;
    private final Map<String, String> myMessagesIDs = new HashMap<>();
    private final Map<String, Object> paramsOfRelationship = new HashMap<>(), 
            paramsOfGettingMessages = new HashMap<>(), paramsOfSettingStatus = new HashMap<>(), paramsOfGettingStatus = new HashMap<>(),
            paramsOfCreatingPlayer = new HashMap<>(), paramsOfSeen = new HashMap<>(), paramsOfGettingLastMessage = new HashMap<>(),
            paramsOfGettingSenders = new HashMap<>(), paramsOfGettingCounters = new HashMap<>(),
            paramsOfSettingStatusDuration = new HashMap<>(), paramsOfSettingTypingStatus = new HashMap<>(),
            paramsOfGettingTypingStatus = new HashMap<>(), paramsOfDeletingPlayerNode = new HashMap<>(),
            paramsOfGettingSeenStatus = new HashMap<>(), paramsOfGettingTheImage = new HashMap<>(),
            paramsOfSettingPreviousMessageID = new HashMap<>(), paramsOfGettingMyMessage = new HashMap<>(),
            paramsOfGettingPlayerScore = new HashMap<>(), paramsOfSettingPlayerScore = new HashMap<>(),
            paramsOfGettingPlayerVerify = new HashMap<>();
    private List<String> senders;
    private int counter = 0, isTyping, photoMessageIncreamentNumber = 0, photoMyMessageIncreamentNumber = 0,
            myMessagesCounter = 0, playersCount = 0, playerScore = 0;
    private String messageContent = "", res = "<html><head></head><body>", base64STR = "";
    private final Session session, session1, session2, session3, session4, session5;
    private boolean isSeen, isVerified;
    private final String photoMessage = "Photo", photoMyMessage = "MyPhoto";
    private final Neo4jDatabaseConfig neo4jDatabaseConfig = new Neo4jDatabaseConfig();

    private Neo4jDatabaseManager() {
        this.driver = GraphDatabase.driver(this.neo4jDatabaseConfig.getNeo4jdatabaseURI(), AuthTokens.basic(this.neo4jDatabaseConfig.getNeo4jdatabaseUser(), this.neo4jDatabaseConfig.getNeo4jdatabasePassword()), Config.defaultConfig());
        this.session = this.driver.session(SessionConfig.forDatabase("neo4j"));
        this.session1 = this.driver.session(SessionConfig.forDatabase("neo4j"));
        this.session2 = this.driver.session(SessionConfig.forDatabase("neo4j"));
        this.session3 = this.driver.session(SessionConfig.forDatabase("neo4j"));
        this.session4 = this.driver.session(SessionConfig.forDatabase("neo4j"));
        this.session5 = this.driver.session(SessionConfig.forDatabase("neo4j"));
    }

    public static Neo4jDatabaseManager getInstance() {
        if (neo4jdatabase == null) {
            neo4jdatabase = new Neo4jDatabaseManager();
        }
        return neo4jdatabase;
    }

    @Override
    public void close() throws Exception {
        this.driver.close();
    }

    protected void createRelationship(String person1Name, String person2Name, final String date, final String content) {
        person1Name = person1Name.split("@")[0];
        person2Name = person2Name.split("@")[0];
        this.paramsOfRelationship.clear();
        this.paramsOfRelationship.put("person1_name", person1Name);
        this.paramsOfRelationship.put("person2_name", person2Name);
        this.paramsOfRelationship.put("message_content", content);
        this.paramsOfRelationship.put("message_date", date);
        this.paramsOfRelationship.put("seen", 0);
        this.paramsOfRelationship.put("messageLength", content.length());
        String createRelationshipQuery = "MERGE (p1:Player {User_Of_Email: $person1_name})"
                + " MERGE (p2:Player {User_Of_Email: $person2_name})"
                + " MERGE (p1)-[r:Sent_Message {content: $message_content, date: $message_date, seen: $seen,"
                + " messageLength: $messageLength, isImage: 0}]->(p2)";
        try {
            try (Transaction transaction = this.session.beginTransaction()) {
                transaction.run(createRelationshipQuery, this.paramsOfRelationship);
                transaction.commit();
                transaction.close();
            }
        } catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, createRelationshipQuery + " raised an exception", ex);
        }
        this.setPreviousMessageID(person1Name, person2Name, content, date, 0);
    }

    private void setPreviousMessageID(final String userOfReceiverEmail, final String userOfSenderEmail,
            final String content, final String date, final int isImage) {
        String setPreviousMessageIDQuery = "MATCH (p1:Player {User_Of_Email: $user_Of_Receiver_Email})<-"
                + "[r1:Sent_Message {seen: 1}]-(p2:Player {User_Of_Email: $user_Of_Sender_Email}) "
                + "WITH r1, ID(r1) AS message_id, p1 AS player1, p2 AS player2 ORDER BY r1.date DESC LIMIT 1 "
                + "MATCH (player1)-[r2:Sent_Message]->(player2) "
                + "WHERE r2.content = $message_content AND r2.date = $message_date AND r2.isImage = $is_image "
                + "SET r2.previousMessageID = message_id RETURN r2";
        this.paramsOfSettingPreviousMessageID.clear();
        this.paramsOfSettingPreviousMessageID.put("user_Of_Receiver_Email", userOfReceiverEmail);
        this.paramsOfSettingPreviousMessageID.put("user_Of_Sender_Email", userOfSenderEmail);
        this.paramsOfSettingPreviousMessageID.put("message_content", content);
        this.paramsOfSettingPreviousMessageID.put("message_date", date);
        this.paramsOfSettingPreviousMessageID.put("is_image", isImage);
        try {
            try (Transaction transaction = this.session.beginTransaction()) {
                transaction.run(setPreviousMessageIDQuery, this.paramsOfSettingPreviousMessageID);
                transaction.commit();
                transaction.close();
            }
        } catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, setPreviousMessageIDQuery + " raised an exception", ex);
            System.out.println(ex.getMessage());
        }
    }

    protected void createRelationship(String person1Name, String person2Name, final String date, final String content, final String base64OfImage) {
        person1Name = person1Name.split("@")[0];
        person2Name = person2Name.split("@")[0];
        this.paramsOfRelationship.clear();
        this.paramsOfRelationship.put("person1_name", person1Name);
        this.paramsOfRelationship.put("person2_name", person2Name);
        this.paramsOfRelationship.put("message_content", content);
        this.paramsOfRelationship.put("base64_of_image", base64OfImage);
        this.paramsOfRelationship.put("message_date", date);
        this.paramsOfRelationship.put("seen", 0);
        this.paramsOfRelationship.put("messageLength", content.length());
        String createRelationshipQuery = "MERGE (p1:Player {User_Of_Email: $person1_name})"
                + " MERGE (p2:Player {User_Of_Email: $person2_name})"
                + " MERGE (p1)-[r:Sent_Message {content: $message_content, date: $message_date, seen: $seen,"
                + " messageLength: $messageLength, img: $base64_of_image, isImage: 1}]->(p2)";
        try {
            try (Transaction transaction = this.session.beginTransaction()) {
                transaction.run(createRelationshipQuery, this.paramsOfRelationship);
                transaction.commit();
                transaction.close();
            }
        } catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, createRelationshipQuery + " raised an exception", ex);
        }
        this.setPreviousMessageID(person1Name, person2Name, content, date, 1);
    }

    protected void addPlayer(String person_name) {
        this.paramsOfCreatingPlayer.clear();
        this.paramsOfCreatingPlayer.put("person_name", person_name);
        String createPlayerQuery = "MERGE (p:Player {User_Of_Email: $person_name, isTyping: 0, score: 0, status: 0, statusDuration: 0, verified: 'false'})";
        try {
            try (Transaction transaction = this.session.beginTransaction()) {
                transaction.run(createPlayerQuery, this.paramsOfCreatingPlayer);
                transaction.commit();
                transaction.close();
            }
        } catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, createPlayerQuery + " raised an exception", ex);
        }
    }

    protected List<String> getSenders(final String userOfEmail) {
        this.senders = new ArrayList<>();
        String getSendersQuery = "MATCH (p1:Player)<-[r:Sent_Message]-(p2:Player) "
                + "WHERE p1.User_Of_Email = $user_Of_Email RETURN DISTINCT p2.User_Of_Email AS UserOfEmail";
        this.paramsOfGettingSenders.clear();
        this.paramsOfGettingSenders.put("user_Of_Email", userOfEmail);
        try (Transaction transaction = this.session.beginTransaction()) {
            Result result = transaction.run(getSendersQuery, this.paramsOfGettingSenders);
            while (result.hasNext()) {
                Record record = result.next();
                Map<String, Object> row = record.asMap();
                row.entrySet().forEach((column) -> {
                    this.senders.add(column.getValue().toString());
                });
            }
            transaction.close();
        }
        return this.senders;
    }

    protected int getCounters(final String userOfReceiverEmail, final String userOfSenderEmail) {
        String getCountersQuery = "MATCH (p1:Player)<-[r:Sent_Message]-(p2:Player) "
                + "WHERE p1.User_Of_Email = $user_Of_Receiver_Email AND p2.User_Of_Email = $user_Of_Sender_Email RETURN count(r)";
        this.paramsOfGettingCounters.clear();
        this.paramsOfGettingCounters.put("user_Of_Receiver_Email", userOfReceiverEmail);
        this.paramsOfGettingCounters.put("user_Of_Sender_Email", userOfSenderEmail);
        try (Transaction transaction = this.session.beginTransaction()) {
            Result result = transaction.run(getCountersQuery, this.paramsOfGettingCounters);
            while (result.hasNext()) {
                Record record = result.next();
                Map<String, Object> row = record.asMap();
                row.entrySet().forEach((column) -> {
                    this.counter = Integer.parseInt(column.getValue().toString());
                });
            }
            transaction.close();
        }
        return this.counter;
    }

    protected int getPlayersCount() {
        String getPlayersCountQuery = "MATCH (p:Player) RETURN count(p)";
        try (Transaction transaction = this.session.beginTransaction()) {
            Result result = transaction.run(getPlayersCountQuery);
            while (result.hasNext()) {
                Record record = result.next();
                Map<String, Object> row = record.asMap();
                row.entrySet().forEach((column) -> {
                    this.playersCount = Integer.parseInt(column.getValue().toString());
                });
            }
            transaction.close();
        }
        return this.playersCount;
    }

    protected int getPlayerScore(final String userOfEmail) {
        try {
            String getPlayerScoreQuery = "MATCH (p:Player) WHERE p.User_Of_Email = $user_Of_Email RETURN p.score";
            this.paramsOfGettingPlayerScore.clear();
            this.paramsOfGettingPlayerScore.put("user_Of_Email", userOfEmail);
            try (Transaction transaction = this.session.beginTransaction()) {
                Result result = transaction.run(getPlayerScoreQuery, this.paramsOfGettingPlayerScore);
                while (result.hasNext()) {
                    Record record = result.next();
                    Map<String, Object> row = record.asMap();
                    row.entrySet().forEach((column) -> {
                        this.playerScore = Integer.parseInt(column.getValue().toString());
                    });
                }
                transaction.close();
            }
        } catch (SessionExpiredException ex) {
        }
        return this.playerScore;
    }

    protected String getMessages(final String userOfReceiverEmail, final String userOfSenderEmail, HashMap<String, String> tableOfUsers) {
        String getAllMessagesQuery = "MATCH (p1:Player)<-[r:Sent_Message]-(p2:Player) "
                + "WHERE p1.User_Of_Email = $user_Of_Receiver_Email AND p2.User_Of_Email = $user_Of_Sender_Email "
                + "RETURN r.content + ' ------' + r.date + r.isImage + 'id=' + ID(r) ORDER BY r.date";
        this.paramsOfGettingMessages.clear();
        this.paramsOfGettingMessages.put("user_Of_Receiver_Email", userOfReceiverEmail);
        this.paramsOfGettingMessages.put("user_Of_Sender_Email", userOfSenderEmail);
        try (Transaction transaction = this.session.beginTransaction()) {
            Result result = transaction.run(getAllMessagesQuery, this.paramsOfGettingMessages);
            while (result.hasNext()) {
                Record record = result.next();
                Map<String, Object> row = record.asMap();
                row.entrySet().forEach((column) -> {
                    this.messageContent = column.getValue().toString();
                    int i = this.messageContent.lastIndexOf("id=");
                    int id = Integer.parseInt(this.messageContent.substring(i + 3, this.messageContent.length()));
                    this.messageContent = this.messageContent.substring(0, i);
                    if (this.messageContent.charAt(this.messageContent.length() - 1) == '0') {
                        this.messageContent = this.messageContent.substring(0, this.messageContent.length() - 1);
                        this.setMessageAsSeen(userOfReceiverEmail, userOfSenderEmail, this.messageContent);
                        this.res += "<h2 style='background-color: black'><font color='white'>" + "From:" + "| " + userOfSenderEmail + "@"
                                + tableOfUsers.get(userOfSenderEmail) + " -> " + "</h2></font>"
                                + "<h1><font style='color: #e80c7a; background-color: white;'>" + this.messageContent + "</font></h1>" + "\n\n";
                    } else {
                        try {
                            this.photoMessageIncreamentNumber++;
                            this.messageContent = this.messageContent.substring(0, this.messageContent.length() - 1);
                            String base64String = this.getTheImage(userOfReceiverEmail, userOfSenderEmail, this.messageContent);
                            String imageFormat = this.getTheImageFormat(base64String);
                            String path = "C:/Users/" + System.getProperty("user.name") + "/Pictures/" + this.photoMessage + Integer.toString(this.photoMessageIncreamentNumber) + "." + imageFormat;
                            File imageFile = new File(path);
                            byte[] imageData = Base64.getDecoder().decode(base64String);
                            try (OutputStream outputStream = new FileOutputStream(path)) {
                                outputStream.write(imageData);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Neo4jDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println(ex.getMessage());
                            } catch (IOException ex) {
                                Logger.getLogger(Neo4jDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println(ex.getMessage());
                            }
                            this.setMessageAsSeen(userOfReceiverEmail, userOfSenderEmail, this.messageContent);
                            this.res += "<h2 style='background-color: black'><font color='white'>" + "From:" + "| " + userOfSenderEmail + "@"
                                    + tableOfUsers.get(userOfSenderEmail) + " -> " + "</h2></font>"
                                    + "<h1><font style='color: #e80c7a; background-color: white;'>" + this.messageContent + "</font></h1><br>"
                                    + "<img src='" + imageFile.toURI().toURL().toExternalForm() + "' width='150' height='150' align='left'/>" + "\n\n";
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(Neo4jDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println(ex.getMessage());
                        }
                    }
                    this.getMyMessage(userOfReceiverEmail, userOfSenderEmail, id);
                });
            }
            transaction.close();
        }
        return this.res + "</body></html>";
    }

    private void getMyMessage(final String userOfReceiverEmail, final String userOfSenderEmail, final int id) {
        String getMyMessageQuery = "MATCH (p1:Player)-[r:Sent_Message {previousMessageID: $message_id}]->(p2:Player) "
                + "WHERE p1.User_Of_Email = $user_Of_Receiver_Email AND p2.User_Of_Email = $user_Of_Sender_Email "
                + "RETURN r.content + ' ------' + r.date + r.isImage + r.seen ORDER BY r.date";
        this.paramsOfGettingMyMessage.clear();
        this.paramsOfGettingMyMessage.put("user_Of_Receiver_Email", userOfReceiverEmail);
        this.paramsOfGettingMyMessage.put("user_Of_Sender_Email", userOfSenderEmail);
        this.paramsOfGettingMyMessage.put("message_id", id);
        try (Transaction transaction = this.session5.beginTransaction()) {
            Result result = transaction.run(getMyMessageQuery, this.paramsOfGettingMyMessage);
            while (result.hasNext()) {
                Record record = result.next();
                Map<String, Object> row = record.asMap();
                row.entrySet().forEach((column) -> {
                    this.messageContent = column.getValue().toString();
                    int seen = this.messageContent.charAt(this.messageContent.length() - 1) - '0';
                    this.messageContent = this.messageContent.substring(0, this.messageContent.length() - 1);
                    String color = seen == 0 ? "orange" : "green";
                    if (this.messageContent.charAt(this.messageContent.length() - 1) == '0') {
                        this.messageContent = this.messageContent.substring(0, this.messageContent.length() - 1);
                        this.res += "<h2 style='background-color: black'><font color='white'>" + "Me: "
                                + "</h2></font>" + "<h1 style='background-color: white' align='right'>"
                                + "<font id='msg" + this.myMessagesCounter + "' color='" + color + "'>"
                                + this.messageContent + "</font></h1>" + "\n\n";
                    } else {
                        try {
                            this.photoMyMessageIncreamentNumber++;
                            this.messageContent = this.messageContent.substring(0, this.messageContent.length() - 1);
                            String base64String = this.getTheImage(userOfReceiverEmail, userOfSenderEmail, this.messageContent);
                            String imageFormat = this.getTheImageFormat(base64String);
                            String path = "C:/Users/" + System.getProperty("user.name") + "/Pictures/" + this.photoMyMessage + Integer.toString(this.photoMyMessageIncreamentNumber) + "." + imageFormat;
                            File imageFile = new File(path);
                            byte[] imageData = Base64.getDecoder().decode(base64String);
                            try (OutputStream outputStream = new FileOutputStream(path)) {
                                outputStream.write(imageData);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Neo4jDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println(ex.getMessage());
                            } catch (IOException ex) {
                                Logger.getLogger(Neo4jDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println(ex.getMessage());
                            }
                            this.res += "<h2 style='background-color: black'><font color='white'>" + "Me: " + "</h2></font>"
                                    + "<h1 style='background-color: white' align='right'><font id='msg"
                                    + this.myMessagesCounter + "' color='" + color + "'>"
                                    + this.messageContent + "</font></h1><br><p align='right'><img src='"
                                    + imageFile.toURI().toURL().toExternalForm() + "' width='150' height='150' align='right'/></p>" + "\n\n";
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(Neo4jDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println(ex.getMessage());
                        }
                    }
                    if (seen == 0) {
                        this.myMessagesIDs.put(this.messageContent, "msg" + this.myMessagesCounter);
                        this.myMessagesCounter--;
                    }
                });
            }
            transaction.close();
        }
    }

    public Map<String, String> getMyMessagesIDsHashmap() {
        return this.myMessagesIDs;
    }

    private String getTheImage(final String userOfReceiverEmail, final String userOfSenderEmail, final String messageContent) {
        String getTheImage = "MATCH (p1:Player)<-[r:Sent_Message]-(p2:Player) "
                + "WHERE p1.User_Of_Email = $user_Of_Receiver_Email AND p2.User_Of_Email = $user_Of_Sender_Email "
                + "AND r.content = $content AND r.date = $date RETURN r.img LIMIT 1";
        this.paramsOfGettingTheImage.clear();
        this.paramsOfGettingTheImage.put("user_Of_Receiver_Email", userOfReceiverEmail);
        this.paramsOfGettingTheImage.put("user_Of_Sender_Email", userOfSenderEmail);
        this.paramsOfGettingTheImage.put("content", messageContent.substring(0, messageContent.lastIndexOf(" ------")));
        this.paramsOfGettingTheImage.put("date", messageContent.substring(messageContent.lastIndexOf(" ------") + 7, messageContent.length()).trim());
        try (Transaction transaction = this.session4.beginTransaction()) {
            Result result = transaction.run(getTheImage, this.paramsOfGettingTheImage);
            if (result.hasNext()) {
                while (result.hasNext()) {
                    Record record = result.next();
                    Map<String, Object> row = record.asMap();
                    row.entrySet().forEach((column) -> {
                        this.base64STR = column.getValue().toString();
                    });
                }
            }
            transaction.close();
        }
        return this.base64STR;
    }

    private String getTheImageFormat(final String base64String) {
        byte[] decodedImage = null;
        try {
            decodedImage = Base64.getDecoder().decode(base64String);
        } catch (IllegalArgumentException ex) {
        }
        String format = "";
        if (decodedImage != null) {
            if (decodedImage.length >= 2 && decodedImage[0] == (byte) 0xFF && decodedImage[1] == (byte) 0xD8) {
                format = "jpg";
            } else if (decodedImage.length >= 2 && decodedImage[0] == (byte) 0x42 && decodedImage[1] == (byte) 0x4D) {
                format = "bmp";
            } else if (decodedImage.length >= 3 && decodedImage[0] == (byte) 0x47 && decodedImage[1] == (byte) 0x49 && decodedImage[2] == (byte) 0x46) {
                format = "gif";
            } else if (decodedImage.length >= 8 && decodedImage[0] == (byte) 0x89 && decodedImage[1] == (byte) 0x50 && decodedImage[2] == (byte) 0x4E && decodedImage[3] == (byte) 0x47 && decodedImage[4] == (byte) 0x0D && decodedImage[5] == (byte) 0x0A && decodedImage[6] == (byte) 0x1A && decodedImage[7] == (byte) 0x0A) {
                format = "png";
            } else if (decodedImage.length >= 3 && decodedImage[0] == (byte) 0x89 && decodedImage[1] == (byte) 0x50 && decodedImage[2] == (byte) 0x4E) {
                format = "png";
            }
        }
        return format;
    }

    protected void setMessageAsSeen(final String userOfReceiverEmail, final String userOfSenderEmail, final String messageContent) {
        String setMessageAsSeenQuery = "MATCH (p1:Player)<-[r:Sent_Message]-(p2:Player) "
                + "WHERE p1.User_Of_Email = $user_Of_Receiver_Email AND p2.User_Of_Email = $user_Of_Sender_Email "
                + "AND r.content = $content AND r.date = $date SET r.seen = 1 RETURN r LIMIT 1";
        this.paramsOfSeen.clear();
        this.paramsOfSeen.put("user_Of_Receiver_Email", userOfReceiverEmail);
        this.paramsOfSeen.put("user_Of_Sender_Email", userOfSenderEmail);
        this.paramsOfSeen.put("content", messageContent.substring(0, messageContent.lastIndexOf(" ------")));
        this.paramsOfSeen.put("date", messageContent.substring(messageContent.lastIndexOf(" ------") + 7, messageContent.length()).trim());
        try {
            try (Transaction transaction = this.session1.beginTransaction()) {
                transaction.run(setMessageAsSeenQuery, this.paramsOfSeen);
                transaction.commit();
                transaction.close();
            }
        } catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, setMessageAsSeenQuery + " raised an exception", ex);
            System.out.println(ex.getMessage());
        }
    }

    protected boolean getMessageSeenStatus(final String userOfReceiverEmail, final String userOfSenderEmail, final String messageContent) {
        String getMessageSeenStatus = "MATCH (p1:Player)-[r:Sent_Message]->(p2:Player) "
                + "WHERE p1.User_Of_Email = $user_Of_Receiver_Email AND p2.User_Of_Email = $user_Of_Sender_Email "
                + "AND r.content = $content AND r.date = $date RETURN r.seen LIMIT 1";
        this.paramsOfGettingSeenStatus.clear();
        this.paramsOfGettingSeenStatus.put("user_Of_Receiver_Email", userOfReceiverEmail);
        this.paramsOfGettingSeenStatus.put("user_Of_Sender_Email", userOfSenderEmail);
        this.paramsOfGettingSeenStatus.put("content", messageContent.substring(0, messageContent.lastIndexOf(" ------")));
        this.paramsOfGettingSeenStatus.put("date", messageContent.substring(messageContent.lastIndexOf(" ------") + 7, messageContent.length()).trim());
        try (Transaction transaction = this.session3.beginTransaction()) {
            Result result = transaction.run(getMessageSeenStatus, this.paramsOfGettingSeenStatus);
            if (result.hasNext()) {
                while (result.hasNext()) {
                    Record record = result.next();
                    Map<String, Object> row = record.asMap();
                    row.entrySet().forEach((column) -> {
                        this.isSeen = Integer.parseInt(column.getValue().toString()) != 0;
                    });
                }
            }
            transaction.close();
        }
        return this.isSeen;
    }

    protected String getLastMessage(final String userOfReceiverEmail, final String userOfSenderEmail, HashMap<String, String> tableOfUsers) {
        String getLastMessageQuery = "MATCH (p1:Player)<-[r:Sent_Message {seen: 0}]-(p2:Player) "
                + "WHERE p1.User_Of_Email = $user_Of_Receiver_Email AND p2.User_Of_Email = $user_Of_Sender_Email "
                + "RETURN r.content + ' ------' + r.date + r.isImage ORDER BY r.date LIMIT 5";
        this.paramsOfGettingLastMessage.clear();
        this.paramsOfGettingLastMessage.put("user_Of_Receiver_Email", userOfReceiverEmail);
        this.paramsOfGettingLastMessage.put("user_Of_Sender_Email", userOfSenderEmail);
        try (Transaction transaction = this.session.beginTransaction()) {
            Result result = transaction.run(getLastMessageQuery, this.paramsOfGettingLastMessage);
            if (result.hasNext()) {
                while (result.hasNext()) {
                    Record record = result.next();
                    Map<String, Object> row = record.asMap();
                    row.entrySet().forEach((column) -> {
                        this.messageContent = column.getValue().toString();
                        if (!this.messageContent.isEmpty() && this.messageContent != null) {
                            if (this.messageContent.charAt(this.messageContent.length() - 1) == '0') {
                                this.messageContent = this.messageContent.substring(0, this.messageContent.length() - 1);
                                this.setMessageAsSeen(userOfReceiverEmail, userOfSenderEmail, this.messageContent);
                                this.res += "<h2 style='background-color: black'><font color='white'>" + "From:" + "| " + userOfSenderEmail + "@"
                                        + tableOfUsers.get(userOfSenderEmail) + " -> " + "</h2></font>"
                                        + "<h1><font style='color: #e80c7a; background-color: white;'>" + this.messageContent + "</font></h1>" + "\n\n";
                            } else {
                                this.photoMessageIncreamentNumber++;
                                this.messageContent = this.messageContent.substring(0, this.messageContent.length() - 1);
                                ExecutorService executor = Executors.newSingleThreadExecutor();
                                Future<?> future = executor.submit(() -> {
                                    try {
                                        String base64String = this.getTheImage(userOfReceiverEmail, userOfSenderEmail, this.messageContent);
                                        String imageFormat = this.getTheImageFormat(base64String);
                                        String path = "C:/Users/" + System.getProperty("user.name") + "/Pictures/" + this.photoMessage + Integer.toString(this.photoMessageIncreamentNumber) + "." + imageFormat;
                                        File imageFile = new File(path);
                                        byte[] imageData = Base64.getDecoder().decode(base64String);
                                        try (OutputStream outputStream = new FileOutputStream(path)) {
                                            outputStream.write(imageData);
                                        } catch (FileNotFoundException ex) {
                                            Logger.getLogger(Neo4jDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                                            System.out.println(ex.getMessage());
                                        } catch (IOException ex) {
                                            Logger.getLogger(Neo4jDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                                            System.out.println(ex.getMessage());
                                        }
                                        this.setMessageAsSeen(userOfReceiverEmail, userOfSenderEmail, this.messageContent);
                                        this.res += "<h2 style='background-color: black'><font color='white'>" + "From:" + "| " + userOfSenderEmail + "@"
                                                + tableOfUsers.get(userOfSenderEmail) + " -> " + "</h2></font>"
                                                + "<h1'><font style='color: #e80c7a; background-color: white;'>" + this.messageContent + "</font></h1><br>"
                                                + "<img src='" + imageFile.toURI().toURL().toExternalForm() + "' width='150' height='150' align='left'/>" + "\n\n";
                                    } catch (MalformedURLException ex) {
                                        Logger.getLogger(Neo4jDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                                        System.out.println(ex.getMessage());
                                    }
                                });
                                try {
                                    future.get();
                                } catch (InterruptedException | ExecutionException ex) {
                                    Logger.getLogger(Neo4jDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                                    System.out.println(ex.getMessage());
                                }
                                executor.shutdown();
                            }
                        } else {
                            this.res = "";
                        }
                    });
                }
            } else {
                this.res = "";
            }
            transaction.close();
        }
        return this.res;
    }

    protected void setStatusDurationAutomation(String person_name) {
        String countdownName = person_name + "StatusCountdown";
        String setStatusDurationAutomationQuery = "CALL apoc.periodic.countdown('" + countdownName + "', "
                + "\"MATCH (p:Player) WHERE p.User_Of_Email = '" + person_name + "' SET p.statusDuration = p.statusDuration - 1, "
                + "p.status = CASE WHEN p.statusDuration = 1 THEN 0 ELSE p.status END return p.statusDuration\", 1)";
        try {
            Result result = this.session.run(setStatusDurationAutomationQuery);
            while (result.hasNext()) {
                System.out.println(result.list().toString());
            }
        } catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, setStatusDurationAutomationQuery + " raised an exception", ex);
            System.out.println(ex.getMessage());
        }
    }

    protected void setStatusDuration(String person_name, int duration) {
        this.paramsOfSettingStatusDuration.clear();
        this.paramsOfSettingStatusDuration.put("person_name", person_name);
        this.paramsOfSettingStatusDuration.put("duration", duration);
        String setStatusDuration = "MATCH (p:Player {User_Of_Email: $person_name}) SET p.statusDuration = $duration RETURN p";
        try {
            try (Transaction transaction = this.session.beginTransaction()) {
                transaction.run(setStatusDuration, this.paramsOfSettingStatusDuration);
                transaction.commit();
                transaction.close();
            }
        } catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, setStatusDuration + " raised an exception", ex);
            System.out.println(ex.getMessage());
        }
        this.setStatusDurationAutomation(person_name);
    }

    protected void setPlayerScore(String person_name, int score) {
        this.paramsOfSettingPlayerScore.clear();
        this.paramsOfSettingPlayerScore.put("person_name", person_name);
        this.paramsOfSettingPlayerScore.put("score", score);
        String setPlayerScoreQuery = "MATCH (p:Player {User_Of_Email: $person_name}) SET p.score = $score RETURN p";
        try {
            try (Transaction transaction = this.session.beginTransaction()) {
                transaction.run(setPlayerScoreQuery, this.paramsOfSettingPlayerScore);
                transaction.commit();
                transaction.close();
            }
        } catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, setPlayerScoreQuery + " raised an exception", ex);
            System.out.println(ex.getMessage());
        }
    }

    protected void setPlayerScore(int score) {
        String myUser = null;
        try (java.io.BufferedReader getJson = new java.io.BufferedReader(new java.io.FileReader(new File("sources/info.json").getAbsolutePath()))) {
            String info = getJson.readLine();
            Object o2 = org.json.simple.JSONValue.parse(info);
            org.json.simple.JSONObject jsonObj2 = (org.json.simple.JSONObject) o2;
            myUser = ((String) jsonObj2.get("AutoEmail")).split("@")[0];
            getJson.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.paramsOfSettingPlayerScore.clear();
        this.paramsOfSettingPlayerScore.put("person_name", myUser);
        this.paramsOfSettingPlayerScore.put("score", score);
        String setPlayerScoreQuery = "MATCH (p:Player {User_Of_Email: $person_name}) SET p.score = $score RETURN p";
        try {
            try (Transaction transaction = this.session.beginTransaction()) {
                transaction.run(setPlayerScoreQuery, this.paramsOfSettingPlayerScore);
                transaction.commit();
                transaction.close();
            }
        } catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, setPlayerScoreQuery + " raised an exception", ex);
            System.out.println(ex.getMessage());
        }
    }

    protected void setPlayerStatus(String email, byte[] imageBytes) {
        String imageContents = Base64.getEncoder().encodeToString(imageBytes), person_name = email.split("@")[0];
        this.cancelPlayerStatusDurationAutomation(person_name);
        this.paramsOfSettingStatus.clear();
        this.paramsOfSettingStatus.put("status", imageContents);
        this.paramsOfSettingStatus.put("person_name", person_name);
        String setStatusQuery = "MATCH (p:Player {User_Of_Email: $person_name}) SET p.status = $status RETURN p";
        try {
            try (Transaction transaction = this.session.beginTransaction()) {
                transaction.run(setStatusQuery, this.paramsOfSettingStatus);
                transaction.commit();
                transaction.close();
            }
        } catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, setStatusQuery + " raised an exception", ex);
        }
        this.setStatusDuration(person_name, 86400);
    }

    protected void cancelPlayerStatusDurationAutomation(String person_name) {
        String countdownName = person_name + "StatusCountdown";
        String cancelStatusDurationAutomation = "CALL apoc.periodic.cancel('" + countdownName + "')";
        try {
            this.session.run(cancelStatusDurationAutomation);
        } catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, cancelStatusDurationAutomation + " raised an exception", ex);
            System.out.println(ex.getMessage());
        }
    }

    protected void removePlayerStatus(String email) {
        String person_name = email.split("@")[0];
        this.cancelPlayerStatusDurationAutomation(person_name);
        this.paramsOfSettingStatus.clear();
        this.paramsOfSettingStatus.put("person_name", person_name);
        String setStatusQuery = "MATCH (p:Player {User_Of_Email: $person_name}) SET p.status = 0 RETURN p";
        try {
            try (Transaction transaction = this.session.beginTransaction()) {
                transaction.run(setStatusQuery, this.paramsOfSettingStatus);
                transaction.commit();
                transaction.close();
            }
        } catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, setStatusQuery + " raised an exception", ex);
            System.out.println(ex.getMessage());
        }
        this.setStatusDuration(person_name, 0);
    }

    protected String getPlayerStatus(String person_name) {
        this.paramsOfGettingStatus.clear();
        this.paramsOfGettingStatus.put("person_name", person_name);
        String getStatusQuery = "MATCH (p:Player {User_Of_Email: $person_name}) RETURN p.status";
        try (Transaction transaction = this.session2.beginTransaction()) {
            Result result = transaction.run(getStatusQuery, this.paramsOfGettingStatus);
            while (result.hasNext()) {
                Record record = result.next();
                Map<String, Object> row = record.asMap();
                row.entrySet().forEach((column) -> {
                    this.base64STR = column.getValue().toString().trim();
                });
            }
            transaction.close();
        }
        return this.base64STR;
    }

    protected boolean getIsPlayerVerified(String person_name) {
        this.paramsOfGettingPlayerVerify.clear();
        this.paramsOfGettingPlayerVerify.put("person_name", person_name);
        String getIsPlayerVerifiedQuery = "MATCH (p:Player {User_Of_Email: $person_name}) RETURN p.verified";
        try (Transaction transaction = this.session2.beginTransaction()) {
            Result result = transaction.run(getIsPlayerVerifiedQuery, this.paramsOfGettingPlayerVerify);
            while (result.hasNext()) {
                Record record = result.next();
                Map<String, Object> row = record.asMap();
                row.entrySet().forEach((column) -> {
                    this.isVerified = Boolean.parseBoolean(column.getValue().toString().trim());
                });
            }
            transaction.close();
        }
        return this.isVerified;
    }

    protected synchronized void setTypingStatus(String person_name, int isTyping) {
        this.paramsOfSettingTypingStatus.clear();
        this.paramsOfSettingTypingStatus.put("person_name", person_name);
        this.paramsOfSettingTypingStatus.put("status", isTyping);
        String setTypingStatusQuery = "MATCH (p:Player {User_Of_Email: $person_name}) SET p.isTyping = $status RETURN p.isTyping";
        Session session = this.driver.session(SessionConfig.forDatabase("neo4j"));
        try {
            try (Transaction transaction = session.beginTransaction()) {
                transaction.run(setTypingStatusQuery, this.paramsOfSettingTypingStatus);
                transaction.commit();
                transaction.close();
            }
        } catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, setTypingStatusQuery + " raised an exception", ex);
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
    }

    protected synchronized int getTypingStatus(String person_name) {
        this.paramsOfGettingTypingStatus.clear();
        this.paramsOfGettingTypingStatus.put("person_name", person_name);
        String getTypingStatusQuery = "MATCH (p:Player {User_Of_Email: $person_name}) RETURN p.isTyping";
        Session session = this.driver.session(SessionConfig.forDatabase("neo4j"));
        try {
            try (Transaction transaction = session.beginTransaction()) {
                Result result = transaction.run(getTypingStatusQuery, this.paramsOfGettingTypingStatus);
                while (result.hasNext()) {
                    Record record = result.next();
                    Map<String, Object> row = record.asMap();
                    row.entrySet().forEach((column) -> {
                        this.isTyping = Integer.parseInt(column.getValue().toString());
                    });
                }
                transaction.close();
            }
        } catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, getTypingStatusQuery + " raised an exception", ex);
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return this.isTyping;
    }

    protected void deletePlayerNode(String person_name) {
        this.paramsOfDeletingPlayerNode.clear();
        this.paramsOfDeletingPlayerNode.put("person_name", person_name);
        String deletingPlayerNodeQuery = "MATCH (p:Player {User_Of_Email: $person_name}) DETACH DELETE p";
        try {
            try (Transaction transaction = this.session.beginTransaction()) {
                transaction.run(deletingPlayerNodeQuery, this.paramsOfDeletingPlayerNode);
                transaction.commit();
                transaction.close();
            }
        } catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, deletingPlayerNodeQuery + " raised an exception", ex);
            System.out.println(ex.getMessage());
        }
    }

}
