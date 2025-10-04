package the.green.one.game;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Neo4jDatabaseConfig {

    private String neo4jdatabaseURI, neo4jdatabaseUser, neo4jdatabasePassword;

    public Neo4jDatabaseConfig() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getResourceAsStream("neo4j_database_properties.properties")) {
            properties.load(input);
            this.neo4jdatabaseURI = properties.getProperty("NEO4J_DATABASE_URI");
            this.neo4jdatabaseUser = properties.getProperty("NEO4J_DATABASE_USER");
            this.neo4jdatabasePassword = properties.getProperty("NEO4J_DATABASE_PASSWORD");
        } catch (IOException ex) {
            Logger.getLogger(Neo4jDatabaseConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNeo4jdatabaseURI() {
        return this.neo4jdatabaseURI;
    }

    public String getNeo4jdatabaseUser() {
        return this.neo4jdatabaseUser;
    }

    public String getNeo4jdatabasePassword() {
        return this.neo4jdatabasePassword;
    }
}
