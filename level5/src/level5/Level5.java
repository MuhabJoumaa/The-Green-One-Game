package level5;

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.col.ColModelImporter;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableFloatArray;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Camera;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.fxyz3d.geometry.Point3D;
import org.fxyz3d.shapes.primitives.CuboidMesh;
import org.fxyz3d.shapes.primitives.SpringMesh;

public class Level5 extends Application {

    private DropShadow bright;
    private final Color SunColor = Color.rgb(255, 244, 74, 0.91), red = Color.RED,
            shadowColor = new Color(0, 0, 0, 0.4), shadowColorTransparent = new Color(0, 0, 0, 0.0);
    boolean jump = false, close = false, isBulletMoving = false;
    // transitions
    private final TranslateTransition jumping, falling, shadowMovement, s1t, s2t, s3t, s4t, s5t,
            bulletShooting;
    private final RotateTransition s1, s2, s3, s4, s5, spaceshipRotation;
    private RotateTransition bossRotation;
    private ParallelTransition pt1, pt2, pt3, pt4, pt5;
    private final PathTransition moveByPath;
    private Path MovePath;
    private Bounds s1Rect, s2Rect, s3Rect, s4Rect, s5Rect, tgoRect;
    private final Random moveRandomly;
    private final Rectangle EnergyRect, Energy, EnergyRectBoss, EnergyBoss;
    private double tgoZ = 0.0, dist1, dist2, dist3, dist4, dist5, mouseCursorY = 0.0d,
            mouseCursorZ = 0.0d, dy = 0.0d, dz = 0.0d;
    private int counter = 90, range1, range2, range3, range4, range5, EnergyAmount = 60,
            EnergyAmountBoss = 90, num = 1, bulletsNumber = 22;
    ;
    private final Timer timer;
    private final Image stone = new Image(getClass().getResourceAsStream("/sources/ston.png"));
    private Sphere ston1, ston2, ston3, ston4, ston5, tgo;
    private final Alert loseAlert;
    private final ButtonType TryAgain, Exit;
    private AnimationTimer animationtimer;
    // sounds
    private final File fallSound = new File("sounds/falling.wav"),
            gameOver = new File("sounds/gameOver.wav"),
            bulletHitting = new File("sounds/bulletHittingMetalSound.wav"),
            emptyGun = new File("sounds/emptyGunSound.wav"),
            emptyGunSound = new File(emptyGun.getAbsolutePath()),
            gunReloading = new File("sounds/gunReloadSound.wav"),
            gunReloadingSound = new File(gunReloading.getAbsolutePath()),
            music = new File("sounds/backgroundSound.wav"),
            oh = new File("sounds/oh.wav"),
            Oooh = new File(oh.getAbsolutePath()),
            count = new File("sounds/count1.wav"),
            countSound = new File(count.getAbsolutePath()),
            hitGround = new File("sounds/hit.wav"),
            hitGroundSound = new File(hitGround.getAbsolutePath()),
            bird = new File("sounds/birdSound.wav"),
            birdSound = new File(bird.getAbsolutePath()),
            shoot = new File("sounds/shootingEffect.wav"),
            shootingEffect = new File(shoot.getAbsolutePath()),
            bulletHittingMetalSoundEffect = new File(bulletHitting.getAbsolutePath()),
            tp = new File("sounds/TakePhoto.wav"),
            TakePhotoSound = new File(tp.getAbsolutePath());
    private File spaceshipFile, bossFile, gunFile, bulletFile;
    // shadow
    private final double[] lightSourceCoords = new double[3];
    private PhongMaterial shadowMaterial;
    private final java.awt.Rectangle shadowRect = new java.awt.Rectangle(),
            leftRect = new java.awt.Rectangle(),
            rightRect = new java.awt.Rectangle();
    // 3D models
    private final ObjModelImporter spaceshipImporter, bossImporter, bulletImporter;
    private final ColModelImporter gunImporter;
    private final String spaceshipPath = "sources/3DModels/13884_UFO_Saucer_v1_l2.obj",
            bossPath = "sources/3DModels/C3PO.obj", gunPath = "sources/3DModels/Gun.dae",
            bulletPath = "sources/3DModels/bullet.obj", spaceshipDiffusePath = "/sources/spaceship_diffuse.jpg";
    private AABB3D[] bossRects, bulletRects;
    // mouse movement
    private final Rotate rotateGunByY = new Rotate(0.0d, Rotate.Y_AXIS),
            rotateGunByZ = new Rotate(0.0d, Rotate.Z_AXIS);
    // gamepad handling
    private final GamepadHandler gamepadHandler;
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private final double rotateSpeedY = 4.0d, rotateSpeedZ = 2.0d;
    // springs
    private final int numberOfSpringsInEachSide = 5;
    private final double[] leftSideSpringsRotationSpeeds = {0.02, 0.015, 0.01, 0.025, 0.03},
            rightSideSpringsRotationSpeeds = {0.04, 0.033, 0.023, 0.05, 0.049},
            leftSideSpringsRotationAngles = new double[this.numberOfSpringsInEachSide],
            rightSideSpringsRotationAngles = new double[this.numberOfSpringsInEachSide];

    public Level5() {
        this.gamepadHandler = new GamepadHandler();
        this.gamepadHandler.setToCenter();
        this.gamepadHandler.gamepadStatuses[0] = this.gamepadHandler.checkConnection();
        Stage stage = (Stage) this.alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.toFront();
        this.alert.setTitle("Information Message");
        this.alert.setHeaderText("This is an information message");
        this.alert.setContentText("The gamepad has disconnected.");
        this.showControllingInstructions();
        this.soeffects(this.music);
        this.jumping = new TranslateTransition();
        this.falling = new TranslateTransition();
        this.shadowMovement = new TranslateTransition();
        this.s1 = new RotateTransition();
        this.s2 = new RotateTransition();
        this.s3 = new RotateTransition();
        this.s4 = new RotateTransition();
        this.s5 = new RotateTransition();
        this.spaceshipRotation = new RotateTransition();
        this.bossRotation = new RotateTransition();
        this.s1t = new TranslateTransition();
        this.s2t = new TranslateTransition();
        this.s3t = new TranslateTransition();
        this.s4t = new TranslateTransition();
        this.s5t = new TranslateTransition();
        this.bulletShooting = new TranslateTransition();
        this.bossRotation = new RotateTransition();
        this.spaceshipImporter = new ObjModelImporter();
        this.bossImporter = new ObjModelImporter();
        this.gunImporter = new ColModelImporter();
        this.bulletImporter = new ObjModelImporter();
        this.moveByPath = new PathTransition();
        this.EnergyRect = new Rectangle(600, 50, 62, 20);
        this.EnergyRect.setFill(Color.BLACK);
        this.EnergyRect.setStroke(Color.GRAY);
        this.EnergyRect.setStrokeWidth(7);
        this.Energy = new Rectangle(this.EnergyRect.getX() + 0.7, this.EnergyRect.getY(), this.EnergyAmount, 20);
        this.Energy.setFill(Color.LIGHTGREEN);
        this.EnergyRectBoss = new Rectangle(570, 85, 92, 20);
        this.EnergyRectBoss.setFill(Color.BLACK);
        this.EnergyRectBoss.setStroke(Color.GRAY);
        this.EnergyRectBoss.setStrokeWidth(7);
        this.EnergyBoss = new Rectangle(this.EnergyRectBoss.getX() + 0.7,
                this.EnergyRectBoss.getY(), this.EnergyAmountBoss, 20);
        this.EnergyBoss.setFill(Color.WHITE);
        this.moveRandomly = new Random();
        this.timer = new Timer();
        this.range1 = this.moveRandomly.nextInt(20);
        this.range2 = this.moveRandomly.nextInt(20);
        this.range3 = this.moveRandomly.nextInt(20);
        this.range4 = this.moveRandomly.nextInt(20);
        this.range5 = this.moveRandomly.nextInt(20);
        this.loseAlert = new Alert(AlertType.CONFIRMATION);
        this.TryAgain = new ButtonType("Try Again");
        this.Exit = new ButtonType("Exit");
        this.loseAlert.setTitle("Game Over");
        this.loseAlert.setHeaderText("You Lose");
        this.loseAlert.setContentText("Do You To Try Again");
        this.loseAlert.getButtonTypes().setAll(this.TryAgain, this.Exit);
    }

    private void showControllingInstructions() {
        try {
            String pic = this.gamepadHandler.checkConnection() ? "level5Gamepad.jpeg" : "level5Controlling.png";
            java.awt.Image img = ImageIO.read(new File("pics/" + pic).getAbsoluteFile());
            java.awt.Image resizedImg = img.getScaledInstance(618, 462, java.awt.Image.SCALE_SMOOTH);
            JOptionPane.showMessageDialog(null, "Control Instructions Of The Game", "Control Instructions", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(resizedImg));
        } catch (IOException ex) {
            Logger.getLogger(Level5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("The Green One");
        stage.setWidth(700);
        stage.setHeight(620);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/sources/tgo.png")));
        stage.centerOnScreen();
        stage.setOnCloseRequest((WindowEvent evt) -> {
            ClosingOperation.closeBackendProgram();
            stage.close();
            System.exit(0);
        });
        Camera camera = new PerspectiveCamera();
        Group root = new Group();
        Image bgImage = new Image(getClass().getResourceAsStream("/sources/bg.jpg"));
        ImageView bg = new ImageView(bgImage);
        CuboidMesh wood = this.Wood();
        // System.out.println(((TriangleMesh) wood.getMesh()).getPoints().toString());
        Circle sun = (Circle) this.Sun();
        PointLight SunLight = new PointLight();
        SunLight.setColor(Color.LIGHTYELLOW);
        SunLight.getTransforms().setAll(sun.getTransforms());
        Text counter = (Text) this.count();
        this.tgo();
        Cylinder shadow = (Cylinder) this.Shadow(sun, this.tgo);
        ImageView Bird = (ImageView) this.initBird();
        Sphere[] stonesArray = (Sphere[]) this.intiStones();
        try {
            this.spaceshipFile = new File(this.spaceshipPath);
            this.bossFile = new File(this.bossPath);
            this.gunFile = new File(this.gunPath);
            this.bulletFile = new File(this.bulletPath);
            this.spaceshipImporter.read(this.spaceshipFile);
            this.bossImporter.read(this.bossFile);
            this.gunImporter.read(this.gunFile);
            this.bulletImporter.read(this.bulletFile);
        } catch (ImportException e) {
            System.out.println(e);
        }
        MeshView[] spaceship = this.spaceshipImporter.getImport();
        MeshView[] boss = this.bossImporter.getImport();
        Node[] gun = this.gunImporter.getImport();
        Node[] bullets = this.initBulletsArray();
        MeshView[] bullet = this.bulletImporter.getImport();
        this.bossRects = new AABB3D[boss.length];
        this.bulletRects = new AABB3D[bullet.length];
        this.spaceshipImporter.close();
        this.bossImporter.close();
        this.gunImporter.close();
        this.bulletImporter.close();
        this.translateAndRotateSpaceship(spaceship);
        this.setSpaceshipMaterial(spaceship);
        this.translateScaleBoss(boss, wood);
        this.translateScaleGun(gun, this.tgo);
        this.rotateAndScaleBullet(bullet);
        stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            Rotate rotateCamera;
            Transform cameraTransform = new Rotate();

            @Override
            public void handle(KeyEvent evt) {
                int AngelOfX = 0;
                KeyCode KeyCode = evt.getCode();
                switch (KeyCode) {
                    case D:
                        AngelOfX += 10;
                        this.rotateCamera = new Rotate(AngelOfX, Rotate.Y_AXIS);
                        this.cameraTransform = this.cameraTransform.createConcatenation(this.rotateCamera);
                        camera.getTransforms().clear();
                        camera.getTransforms().addAll(this.cameraTransform);
                        break;
                    case A:
                        Level5.this.aButtonPressed(stage);
                        break;
                    default:
                        break;
                }
            }
        });
        SpringMesh[] leftSideSprings = new SpringMesh[this.numberOfSpringsInEachSide],
                rightSideSprings = new SpringMesh[this.numberOfSpringsInEachSide];
        double leftSideSpringsX = -2300.0, initialLeftSideSpringsZ = 20000.0,
                rightSideSpringsX = -leftSideSpringsX, initialRightSideSpringsZ = 2500.0,
                distanceBetweenSprings = 2500.0;
        for (int i = 0; i < this.numberOfSpringsInEachSide; i++) {
            leftSideSprings[i] = new SpringMesh(140, 20, 15, stage.getHeight() + 5000, 800, 100, 0, 0);
            leftSideSprings[i].setCullFace(CullFace.BACK);
            leftSideSprings[i].setTextureModeVertices3D((int) ((Math.random() * (2000 - 100)) + 100), p -> p.f);
            leftSideSprings[i].getTransforms().add(new Rotate(90.0, Rotate.X_AXIS));
            leftSideSprings[i].setTranslateX(leftSideSpringsX);
            leftSideSprings[i].setTranslateY(leftSideSprings[i].getLength() / 2);
            leftSideSprings[i].setTranslateZ(initialLeftSideSpringsZ -= distanceBetweenSprings);
            PhongMaterial leftSideSpringMaterial = new PhongMaterial();
            leftSideSpringMaterial.setDiffuseColor(Color.color(Math.random(), Math.random(), Math.random()));
            leftSideSprings[i].setMaterial(leftSideSpringMaterial);
            rightSideSprings[i] = new SpringMesh(140, 20, 15, stage.getHeight() + 5000, 800, 100, 0, 0);
            rightSideSprings[i].setCullFace(CullFace.BACK);
            rightSideSprings[i].setTextureModeVertices3D(1530, p -> p.f);
            rightSideSprings[i].getTransforms().add(new Rotate(90.0, Rotate.X_AXIS));
            rightSideSprings[i].setTranslateX(rightSideSpringsX);
            rightSideSprings[i].setTranslateY(rightSideSprings[i].getLength() / 2);
            rightSideSprings[i].setTranslateZ(initialRightSideSpringsZ -= distanceBetweenSprings);
            PhongMaterial rightSideSpringMaterial = new PhongMaterial();
            rightSideSpringMaterial.setDiffuseColor(Color.color(Math.random(), Math.random(), Math.random()));
            rightSideSprings[i].setMaterial(rightSideSpringMaterial);
        }
        Timeline leftSideSpringsRotationTimeline = new Timeline(
                new KeyFrame(Duration.millis(16), evt -> {
                    for (int i = 0; i < this.numberOfSpringsInEachSide; i++) {
                        this.leftSideSpringsRotationAngles[i] += this.leftSideSpringsRotationSpeeds[i];
                        double x = (-leftSideSpringsX / 2) * Math.cos(this.leftSideSpringsRotationAngles[i]),
                                z = (-leftSideSpringsX / 2) * Math.sin(this.leftSideSpringsRotationAngles[i]);
                        leftSideSprings[i].setTranslateX(x);
                        leftSideSprings[i].setTranslateZ(z);
                    }
                })
        );
        leftSideSpringsRotationTimeline.setCycleCount(Animation.INDEFINITE);
        leftSideSpringsRotationTimeline.setAutoReverse(true);
        Timeline rightSideSpringsRotationTimeline = new Timeline(
                new KeyFrame(Duration.millis(16), evt -> {
                    for (int i = 0; i < this.numberOfSpringsInEachSide; i++) {
                        this.rightSideSpringsRotationAngles[i] += this.rightSideSpringsRotationSpeeds[i];
                        double x = (rightSideSpringsX / 2) * Math.cos(this.rightSideSpringsRotationAngles[i]),
                                z = (rightSideSpringsX / 2) * Math.sin(this.rightSideSpringsRotationAngles[i]);
                        rightSideSprings[i].setTranslateX(x);
                        rightSideSprings[i].setTranslateZ(z);
                    }
                })
        );
        rightSideSpringsRotationTimeline.setCycleCount(Animation.INDEFINITE);
        rightSideSpringsRotationTimeline.setAutoReverse(true);
        ParallelTransition springsCircularPathParallelTransition = new ParallelTransition(leftSideSpringsRotationTimeline, rightSideSpringsRotationTimeline);
        springsCircularPathParallelTransition.play();
        root.getChildren().add(bg);
        root.getChildren().add(SunLight);
        root.getChildren().addAll(leftSideSprings);
        root.getChildren().addAll(rightSideSprings);
        root.getChildren().add(wood);
        root.getChildren().add(sun);
        root.getChildren().add(counter);
        root.getChildren().add(Bird);
        root.getChildren().addAll(Arrays.asList(stonesArray));
        root.getChildren().addAll(spaceship);
        root.getChildren().addAll(boss);
        root.getChildren().addAll(bullet);
        root.getChildren().addAll(gun);
        root.getChildren().addAll(bullets);
        root.getChildren().add(this.tgo);
        root.getChildren().add(shadow);
        root.getChildren().add(this.EnergyRect);
        root.getChildren().add(this.Energy);
        root.getChildren().add(this.EnergyRectBoss);
        root.getChildren().add(this.EnergyBoss);
        Scene s = new Scene(root, 400, 400, false);
        s.setCursor(Cursor.CROSSHAIR);
        s.setCamera(camera);
        s.setFill(Color.rgb(50, 50, 45));
        s.setOnMouseMoved((MouseEvent mev) -> {
            Level5.this.getMouseCursorLocationAndRotateGun(mev, gun, boss);
        });
        s.setOnMousePressed((MouseEvent mev) -> {
            Level5.this.shootByMouse(mev, bullet, this.tgo, gun);
        });
        stage.setScene(s);
        stage.show();
        this.keyEvents(stage, this.tgo, (TriangleMesh) wood.getMesh(), shadow, bullet, gun);
        this.putRect(this.tgo);
        this.StonesMoving(stonesArray);
        this.bossMoving(boss);
        this.StonesRects(stonesArray);
        this.timer(stage, this.tgo, shadow, stonesArray, root, bg, wood, sun,
                counter, Bird, SunLight, spaceship, boss, gun, bullet,
                leftSideSprings, rightSideSprings);
        this.MoveBird(Bird);
        this.Timing(counter, boss);
    }

    private void getMouseCursorLocationAndRotateGun(MouseEvent evt, Node[] gun, MeshView[] boss) {
        this.dy = this.rotateSpeedY * (this.mouseCursorY - evt.getSceneX());
        this.dz = this.rotateSpeedZ * (this.mouseCursorZ - evt.getSceneY());
        this.rotateGunByY.pivotYProperty().set(boss[5].getTranslateY());
        this.rotateGunByZ.pivotZProperty().set(boss[5].getTranslateZ());
        this.rotateGunByY.setAngle((this.rotateGunByY.getAngle() - ((-this.dz / gun[0].getBoundsInParent().getHeight() * 360.0d) * (Math.PI / 180.0d))));
        this.rotateGunByZ.setAngle((this.rotateGunByZ.getAngle() - ((-this.dy / gun[0].getBoundsInParent().getWidth() * -360.0d) * (Math.PI / 180.0d))));
        if ((this.mouseCursorY >= -600.0d && this.mouseCursorY <= 600.0d) && (this.mouseCursorZ >= -600.0d && this.mouseCursorZ <= 600.0d)) {
            gun[0].getTransforms().removeAll(this.rotateGunByY, this.rotateGunByZ);
            gun[0].getTransforms().addAll(this.rotateGunByY, this.rotateGunByZ);
        }
        this.mouseCursorY = evt.getSceneX();
        this.mouseCursorZ = evt.getSceneY();
    }

    public void getJoystickLocationAndRotateGun(Node[] gun, MeshView[] boss) {
        float joystickX = this.gamepadHandler.getJoystickX(),
                joystickY = this.gamepadHandler.getJoystickY();
        this.dy = this.rotateSpeedY * 8 * joystickX;
        this.dz = this.rotateSpeedZ * 8 * -joystickY;
        this.rotateGunByY.pivotYProperty().set(boss[5].getTranslateY());
        this.rotateGunByZ.pivotZProperty().set(boss[5].getTranslateZ());
        this.rotateGunByY.setAngle(this.rotateGunByY.getAngle() - ((-dz / gun[0].getBoundsInParent().getHeight() * 360.0d) * (Math.PI / 180.0d)));
        this.rotateGunByZ.setAngle(this.rotateGunByZ.getAngle() - ((-dy / gun[0].getBoundsInParent().getWidth() * -360.0d) * (Math.PI / 180.0d)));
        gun[0].getTransforms().removeAll(this.rotateGunByY, this.rotateGunByZ);
        gun[0].getTransforms().addAll(this.rotateGunByY, this.rotateGunByZ);
    }

    private void shootByMouse(MouseEvent evt, MeshView[] bullet, Sphere tgo, Node[] gun) {
        if (evt.isPrimaryButtonDown()) {
            this.shoot(bullet, tgo, gun);
        }
    }

    public void putRect(Sphere tgo) {
        this.tgoZ = tgo.getTranslateZ();
        this.tgoRect = tgo.getBoundsInParent();
    }

    public void Timing(Text counting, MeshView[] boss) {
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Level5.this.counter--;
                if (Level5.this.counter == 3 || Level5.this.counter == 2 || Level5.this.counter == 1) {
                    Level5.this.soeffects(Level5.this.countSound);
                    counting.setFill(Color.RED);
                }
                if (Level5.this.counter % 5 == 0 && Level5.this.counter != 90) {
                    Level5.this.bossRotation.play();
                }
                if (Level5.this.counter % 10 == 0) {
                    Level5.this.soeffects(Level5.this.birdSound);
                }
                counting.setText(Integer.toString(Level5.this.counter));
                for (MeshView component : boss) {
                    component.setTranslateZ(component.getTranslateZ() - 9.0d);
                }
            }
        }, 1000, 1000);
    }

    public void timer(Stage s, Sphere tgo, Cylinder shadow, Sphere[] stones,
            Group root, ImageView bg, CuboidMesh wood, Circle sun, Text counter,
            ImageView Bird, PointLight SunLight, MeshView[] spaceship,
            MeshView[] boss, Node[] gun, MeshView[] bullet,
            SpringMesh[] leftSideSprings, SpringMesh[] rightSideSprings) {
        Level5.this.animationtimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Level5.this.handleGamepad(tgo, (TriangleMesh) wood.getMesh(),
                        shadow, s, bullet, gun, boss);
                Level5.this.UpdateScreen(root, bg, wood, sun, counter, Bird,
                        stones, tgo, shadow, SunLight, spaceship,
                        boss, gun, bullet, leftSideSprings, rightSideSprings,
                        Level5.this.initBulletsArray());
                Level5.this.Dinstants(tgo, stones, boss);
                Level5.this.generatingBossAndBulletRectangles(bullet, boss);
                Level5.this.bulletCollision();
                Level5.this.StonesRects(stones);
                Level5.this.Renew();
                Level5.this.CheckCollisionAndLosing(tgo, stones);
                Level5.this.renderingShadow(tgo, shadow, wood);
                Level5.this.renderingGun(gun, tgo);
                Level5.this.Debugging(stones);
            }
        };
        this.animationtimer.start();
    }

    private strictfp void generatingBossAndBulletRectangles(MeshView[] bullet, MeshView[] boss) {
        for (int i = 0; i < this.bulletRects.length; i++) {
            this.bulletRects[i] = new AABB3D(bullet[i].getBoundsInParent().getMinX() + 7, bullet[i].getBoundsInParent().getMinY() + 6,
                    bullet[i].getBoundsInParent().getMinZ() + 20, 12.5d, 13.5d, 12.0d);
        }
        for (int i = 0; i < this.bossRects.length; i++) {
            this.bossRects[i] = new AABB3D(boss[i].getBoundsInParent().getMinX() + 6, boss[i].getBoundsInParent().getMinY() + 6,
                    boss[i].getBoundsInParent().getMinZ() + 30, 14.5d, 29.5d, 16.5d);
        }
    }

    private strictfp void bulletCollision() {
        for (Rectangle3D bulletRect : this.bulletRects) {
            for (Rectangle3D bossRect : this.bossRects) {
                if (bulletRect.intersects(bossRect)) {
                    this.EnergyAmountBoss -= 14.5;
                    this.EnergyBoss.setWidth(this.EnergyAmountBoss);
                    this.soeffects(this.bulletHittingMetalSoundEffect);
                    break;
                }
            }
        }
    }

    private void UpdateScreen(Group root, ImageView bg, CuboidMesh wood, Circle sun,
            Text counter, ImageView Bird, Sphere[] stonesArray, Sphere tgo,
            Cylinder shadow, PointLight SunLight, MeshView[] spaceship,
            MeshView[] boss, Node[] gun, MeshView[] bullet,
            SpringMesh[] leftSideSprings, SpringMesh[] rightSideSprings,
            Node[] bullets) {
        root.getChildren().clear();
        root.getChildren().add(bg);
        root.getChildren().add(SunLight);
        root.getChildren().addAll(leftSideSprings);
        root.getChildren().addAll(rightSideSprings);
        root.getChildren().add(wood);
        root.getChildren().add(sun);
        root.getChildren().add(counter);
        root.getChildren().add(Bird);
        root.getChildren().addAll(Arrays.asList(stonesArray));
        root.getChildren().addAll(spaceship);
        root.getChildren().addAll(boss);
        root.getChildren().addAll(bullet);
        root.getChildren().addAll(gun);
        root.getChildren().addAll(bullets);
        root.getChildren().add(tgo);
        root.getChildren().add(shadow);
        root.getChildren().add(this.EnergyRect);
        root.getChildren().add(this.Energy);
        root.getChildren().add(this.EnergyRectBoss);
        root.getChildren().add(this.EnergyBoss);
    }

    private void handleGamepad(Sphere tgo, TriangleMesh woodMesh,
            Cylinder shadow, Stage s, MeshView[] bullet, Node[] gun, MeshView[] boss) {
        this.gamepadHandler.gamepadStatuses[1] = this.gamepadHandler.update();
        if (this.gamepadHandler.gamepadStatuses[0] && !this.gamepadHandler.gamepadStatuses[1]) {
            this.gamepadHandler.gamepadStatuses[0] = this.gamepadHandler.gamepadStatuses[1];
            this.alert.show();
            return;
        }
        if (this.gamepadHandler.isUpButtonPressed()) {
            this.upButtonPressed(tgo, shadow);
        } else if (this.gamepadHandler.isLeftButtonPressed()) {
            this.leftButtonPressed(tgo, woodMesh);
        } else if (this.gamepadHandler.isRightButtonPressed()) {
            this.rightButtonPressed(tgo, woodMesh);
        } else if (this.gamepadHandler.isAButtonPressed()) {
            this.aButtonPressed(s);
        } else if (this.gamepadHandler.isR2ButtonPressed()) {
            this.r2ButtonPressed(bullet, tgo, gun);
        } else if (this.gamepadHandler.isRightJoystickMoved()) {
            this.getJoystickLocationAndRotateGun(gun, boss);
            this.gamepadHandler.updatePrevJoystickPositions();
        }
        this.gamepadHandler.setToCenter();
    }

    private strictfp void Dinstants(Sphere tgo, Sphere[] stones, MeshView[] boss) {
        this.dist1 = Math.sqrt(Math.pow((stones[0].getTranslateX()) - (tgo.getTranslateX()), 2)
                + Math.pow((stones[0].getTranslateY()) - (tgo.getTranslateY()), 2)
                + Math.pow((stones[0].getTranslateZ()) - (tgo.getTranslateZ()), 2));
        this.dist2 = Math.sqrt(Math.pow((stones[1].getTranslateX()) - (tgo.getTranslateX()), 2)
                + Math.pow((stones[1].getTranslateY()) - (tgo.getTranslateY()), 2)
                + Math.pow((stones[1].getTranslateZ()) - (tgo.getTranslateZ()), 2));
        this.dist3 = Math.sqrt(Math.pow((stones[2].getTranslateX()) - (tgo.getTranslateX()), 2)
                + Math.pow((stones[2].getTranslateY()) - (tgo.getTranslateY()), 2)
                + Math.pow((stones[2].getTranslateZ()) - (tgo.getTranslateZ()), 2));
        this.dist4 = Math.sqrt(Math.pow((stones[3].getTranslateX()) - (tgo.getTranslateX()), 2)
                + Math.pow((stones[3].getTranslateY()) - (tgo.getTranslateY()), 2)
                + Math.pow((stones[3].getTranslateZ()) - (tgo.getTranslateZ()), 2));
        this.dist5 = Math.sqrt(Math.pow((stones[4].getTranslateX()) - (tgo.getTranslateX()), 2)
                + Math.pow((stones[4].getTranslateY()) - (tgo.getTranslateY()), 2)
                + Math.pow((stones[4].getTranslateZ()) - (tgo.getTranslateZ()), 2));
    }

    private void setSpaceshipMaterial(MeshView[] spaceship) {
        PhongMaterial spaceshipMaterial = new PhongMaterial();
        Image spaceshipDiffuse = new Image(getClass().getResourceAsStream(this.spaceshipDiffusePath));
        spaceshipMaterial.setDiffuseMap(spaceshipDiffuse);
        for (MeshView component : spaceship) {
            component.setMaterial(spaceshipMaterial);
        }
    }

    private void translateAndRotateSpaceship(MeshView[] spaceship) {
        Rotate rotateSpaceship = new Rotate(90.0d, Rotate.X_AXIS);
        this.spaceshipRotation.setDuration(Duration.millis(700));
        this.spaceshipRotation.setAxis(Rotate.Y_AXIS);
        this.spaceshipRotation.setByAngle(360.0d);
        this.spaceshipRotation.setCycleCount(Animation.INDEFINITE);
        for (MeshView component : spaceship) {
            component.setTranslateX(900.0d);
            component.setTranslateY(-100.0d);
            component.setTranslateZ(1500.0d);
            component.getTransforms().add(rotateSpaceship);
            this.spaceshipRotation.setNode(component);
        }
        this.spaceshipRotation.play();
    }

    private void translateScaleBoss(MeshView[] boss, CuboidMesh wood) {
        Scale scaleBoss = new Scale();
        scaleBoss.setX(3.0d);
        scaleBoss.setY(3.0d);
        for (MeshView component : boss) {
            component.setTranslateX((wood.getTranslateX() + wood.getHeight() * 0.5) - 75.0d);
            component.setTranslateY(wood.getTranslateY() - wood.getDepth());
            component.setTranslateZ(100.0d);
            component.getTransforms().add(scaleBoss);
            component.toFront();
        }
    }

    private void translateScaleGun(Node[] gun, Sphere tgo) {
        Rotate rotateGun = new Rotate(90.0d, Rotate.Z_AXIS);
        Scale scaleGun = new Scale();
        scaleGun.setX(120.0d);
        scaleGun.setY(120.0d);
        scaleGun.setZ(120.0d);
        for (Node component : gun) {
            component.setTranslateX(tgo.getTranslateX() + tgo.getRadius());
            component.setTranslateY((tgo.getTranslateY() - tgo.getRadius()) - 1.8d);
            component.setTranslateZ(tgo.getTranslateZ() + tgo.getRadius());
            component.getTransforms().add(rotateGun);
            component.getTransforms().add(scaleGun);
        }
    }

    private void rotateAndScaleBullet(MeshView[] bullet) {
        Rotate rotateBullet = new Rotate(190.0d, Rotate.Y_AXIS);
        Scale scaleBullet = new Scale();
        scaleBullet.setX(3.5d);
        scaleBullet.setY(3.5d);
        scaleBullet.setZ(3.5d);
        for (MeshView component : bullet) {
            component.getTransforms().add(rotateBullet);
            component.getTransforms().add(scaleBullet);
            component.setVisible(false);
        }
    }

    private void renderingGun(Node[] gun, Sphere tgo) {
        for (Node component : gun) {
            component.setTranslateX(tgo.getTranslateX() + tgo.getRadius());
            component.setTranslateY((tgo.getTranslateY() - tgo.getRadius()) - 2.2d);
            component.setTranslateZ(tgo.getTranslateZ() + tgo.getRadius());
        }
    }

    private void shoot(MeshView[] bullet, Sphere tgo, Node[] gun) {
        this.bulletShooting.statusProperty().addListener((ObservableValue<? extends Status> ov, Status s1, Status s2) -> {
            this.isBulletMoving = s2 == Status.RUNNING;
        });
        this.bulletShooting.setDuration(Duration.millis(4070));
        this.bulletShooting.setFromX((gun[0].getTranslateX() - 30.0d) + this.dz);
        this.bulletShooting.setToX((gun[0].getTranslateX() - 30.0d) + this.dz);
        this.bulletShooting.setFromY((gun[0].getTranslateY() + 20.0d) + this.rotateGunByY.angleProperty().doubleValue());
        this.bulletShooting.setToY((gun[0].getTranslateY() + 20.0d) + this.rotateGunByY.angleProperty().doubleValue());
        this.bulletShooting.setFromZ((tgo.getTranslateZ() - tgo.getRadius()) + 18.0d);
        this.bulletShooting.setToZ((tgo.getTranslateZ() - tgo.getRadius()) + 16000.0d);
        this.bulletShooting.setCycleCount(1);
        this.bulletShooting.setAutoReverse(true);
        if (!this.isBulletMoving) {
            Bloom bloom = new Bloom();
            bloom.setThreshold(1.0d);
            for (MeshView component : bullet) {
                this.bulletShooting.setNode(component);
                component.setEffect(bloom);
                component.setVisible(true);
            }
            Rotate rotateGun = new Rotate(20.0d, Rotate.Y_AXIS);
            for (Node component : gun) {
                component.getTransforms().add(rotateGun);
            }
            this.bulletShooting.setOnFinished((ActionEvent evt) -> {
                this.isBulletMoving = false;
                this.soeffects(this.gunReloadingSound);
                for (Node component : gun) {
                    component.getTransforms().remove(rotateGun);
                }
                for (Node component : bullet) {
                    component.setEffect(null);
                }
            });
            this.bulletsNumber--;
            this.soeffects(this.shootingEffect);
            this.bulletShooting.play();
        } else {
            this.soeffects(this.emptyGunSound);
        }
    }

    private void MoveBird(ImageView Bird) {
        this.MovePath = new Path();
        MoveTo startPoint = new MoveTo(30.0, 80.0);
        LineTo lineto = new LineTo();
        lineto.setX(80);
        lineto.setY(130);
        CubicCurveTo cubicPath = new CubicCurveTo();
        cubicPath.setControlX1(80);
        cubicPath.setControlY1(130);
        cubicPath.setControlX2(550);
        cubicPath.setControlY2(30);
        cubicPath.setX(750);
        cubicPath.setY(150);
        this.MovePath.getElements().addAll(startPoint, lineto, cubicPath);
        this.moveByPath.setDuration(Duration.seconds(2));
        this.moveByPath.setCycleCount(Animation.INDEFINITE);
        this.moveByPath.setNode(Bird);
        this.moveByPath.setPath(MovePath);
        this.moveByPath.play();
    }

    private Node initBird() {
        Image birdImage = new Image(getClass().getResourceAsStream("/sources/bird.png"));
        ImageView Bird = new ImageView(birdImage);
        Bird.setX(80);
        Bird.setY(80);
        return Bird;
    }

    private Node[] initBulletsArray() {
        Image bulletImage = new Image(getClass().getResourceAsStream("/sources/b.png"));
        ImageView[] bullets = new ImageView[this.bulletsNumber];
        for (int i = 0, x = 330; i < bullets.length; i++, x += 11) {
            bullets[i] = new ImageView(bulletImage);
            bullets[i].setX(x);
            bullets[i].setY(38);
        }
        return bullets;
    }

    private void Renew() {
        this.range1 = this.moveRandomly.nextInt(15);
        this.range2 = this.moveRandomly.nextInt(15);
        this.range3 = this.moveRandomly.nextInt(15);
        this.range4 = this.moveRandomly.nextInt(15);
        this.range5 = this.moveRandomly.nextInt(15);
        if (this.range1 == 0) {
            this.range1 = 2;
        } else if (this.range2 == 0) {
            this.range2 = 2;
        } else if (this.range3 == 0) {
            this.range3 = 2;
        } else if (this.range4 == 0) {
            this.range4 = 2;
        } else if (this.range5 == 0) {
            this.range5 = 2;
        }
        this.s1.setDuration(Duration.seconds(this.range1));
        this.s2.setDuration(Duration.seconds(this.range2));
        this.s3.setDuration(Duration.seconds(this.range3));
        this.s4.setDuration(Duration.seconds(this.range4));
        this.s5.setDuration(Duration.seconds(this.range5));
        this.s1t.setDuration(Duration.seconds(this.range1));
        this.s2t.setDuration(Duration.seconds(this.range2));
        this.s3t.setDuration(Duration.seconds(this.range3));
        this.s4t.setDuration(Duration.seconds(this.range4));
        this.s5t.setDuration(Duration.seconds(this.range5));
    }

    private void CheckCollisionAndLosing(Sphere tgo, Sphere[] stones) {
        double rad1 = (this.tgo.getRadius() + stones[0].getRadius()) + 0.0;
        double rad2 = (this.tgo.getRadius() + stones[1].getRadius()) + 0.0;
        double rad3 = (this.tgo.getRadius() + stones[2].getRadius()) + 0.0;
        double rad4 = (this.tgo.getRadius() + stones[3].getRadius()) + 0.0;
        double rad5 = (this.tgo.getRadius() + stones[4].getRadius()) + 0.0;
        if (this.dist1 < rad1) {
            this.EnergyAmount -= 3.0;
            this.Energy.setWidth(this.EnergyAmount);
            this.soeffects(this.Oooh);
            Toolkit.getDefaultToolkit().beep();
        }
        if (this.dist2 < rad2) {
            this.EnergyAmount -= 3.0;
            this.Energy.setWidth(this.EnergyAmount);
            this.soeffects(this.Oooh);
            Toolkit.getDefaultToolkit().beep();
        }
        if (this.dist3 < rad3) {
            this.EnergyAmount -= 3.0;
            this.Energy.setWidth(this.EnergyAmount);
            this.soeffects(this.Oooh);
            Toolkit.getDefaultToolkit().beep();
        }
        if (this.dist4 < rad4) {
            this.EnergyAmount -= 3.0;
            this.Energy.setWidth(this.EnergyAmount);
            this.soeffects(this.Oooh);
            Toolkit.getDefaultToolkit().beep();
        }
        if (this.dist5 < rad5) {
            this.EnergyAmount -= 3.0;
            this.Energy.setWidth(this.EnergyAmount);
            this.soeffects(this.Oooh);
            Toolkit.getDefaultToolkit().beep();
        }
        if (this.EnergyAmount <= 20) {
            this.Energy.setFill(this.red);
        }
        if (this.EnergyAmountBoss <= 20) {
            this.EnergyBoss.setFill(this.red);
        }
        if (this.EnergyAmount == 0 || this.counter == 0) {
            this.lose();
        }
        if (this.EnergyAmountBoss <= 0) {
            this.close = true;
        }
        if (tgo.getTranslateX() < 233 || tgo.getTranslateX() > 450) {
            this.fall(tgo);
        }
        if (this.close) {
            try {
                Runtime.getRuntime().exec(new File("end.exe").getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(Level5.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ClosingOperation.closeBackendProgram();
                System.exit(0);
            }
        }
    }

    private void Debugging(Sphere[] stones) {
        /*System.out.println("Face:" + this.tgo.getTranslateX() + " - " + this.tgo.getTranslateY() + " - " + this.tgo.getTranslateZ());
        System.out.println("Stone 3:" + stones[2].getTranslateX() + " - " + stones[2].getTranslateY() + " - " + stones[2].getTranslateZ());
        System.out.println("Distant 3:" + this.dist3);
        System.out.println();
        double rad = this.tgo.getRadius() + stones[2].getRadius();
        System.out.println("Raduis Of Face + Stone3: " + rad);*/
    }

    private void StonesMoving(Sphere[] StonesArray) {
        // rotate
        this.s1.setDuration(Duration.seconds(this.range1));
        this.s2.setDuration(Duration.seconds(this.range2));
        this.s3.setDuration(Duration.seconds(this.range3));
        this.s4.setDuration(Duration.seconds(this.range4));
        this.s5.setDuration(Duration.seconds(this.range5));
        this.s1.setByAngle(360);
        this.s2.setByAngle(360);
        this.s3.setByAngle(360);
        this.s4.setByAngle(360);
        this.s5.setByAngle(360);
        this.s1.setCycleCount(Animation.INDEFINITE);
        this.s2.setCycleCount(Animation.INDEFINITE);
        this.s3.setCycleCount(Animation.INDEFINITE);
        this.s4.setCycleCount(Animation.INDEFINITE);
        this.s5.setCycleCount(Animation.INDEFINITE);
        this.s1.setNode(StonesArray[0]);
        this.s2.setNode(StonesArray[1]);
        this.s3.setNode(StonesArray[2]);
        this.s4.setNode(StonesArray[3]);
        this.s5.setNode(StonesArray[4]);
        // move
        this.s1t.setDuration(Duration.seconds(this.range1));
        this.s2t.setDuration(Duration.seconds(this.range2));
        this.s3t.setDuration(Duration.seconds(this.range3));
        this.s4t.setDuration(Duration.seconds(this.range4));
        this.s5t.setDuration(Duration.seconds(this.range5));
        this.s1t.setToZ(StonesArray[0].getTranslateZ() - 10000);
        this.s2t.setToZ(StonesArray[1].getTranslateZ() - 10000);
        this.s3t.setToZ(StonesArray[2].getTranslateZ() - 10000);
        this.s4t.setToZ(StonesArray[3].getTranslateZ() - 10000);
        this.s5t.setToZ(StonesArray[4].getTranslateZ() - 10000);
        this.s1t.setCycleCount(Animation.INDEFINITE);
        this.s2t.setCycleCount(Animation.INDEFINITE);
        this.s3t.setCycleCount(Animation.INDEFINITE);
        this.s4t.setCycleCount(Animation.INDEFINITE);
        this.s5t.setCycleCount(Animation.INDEFINITE);
        this.s1t.setNode(StonesArray[0]);
        this.s2t.setNode(StonesArray[1]);
        this.s3t.setNode(StonesArray[2]);
        this.s4t.setNode(StonesArray[3]);
        this.s5t.setNode(StonesArray[4]);
        this.pt1 = new ParallelTransition(StonesArray[0], this.s1, this.s2, this.s3, this.s4, this.s5, this.s1t, this.s2t, this.s3t, this.s4t, this.s5t);
        this.pt2 = new ParallelTransition(StonesArray[1], this.s1, this.s2, this.s3, this.s4, this.s5, this.s1t, this.s2t, this.s3t, this.s4t, this.s5t);
        this.pt3 = new ParallelTransition(StonesArray[2], this.s1, this.s2, this.s3, this.s4, this.s5, this.s1t, this.s2t, this.s3t, this.s4t, this.s5t);
        this.pt4 = new ParallelTransition(StonesArray[3], this.s1, this.s2, this.s3, this.s4, this.s5, this.s1t, this.s2t, this.s3t, this.s4t, this.s5t);
        this.pt5 = new ParallelTransition(StonesArray[4], this.s1, this.s2, this.s3, this.s4, this.s5, this.s1t, this.s2t, this.s3t, this.s4t, this.s5t);
        ParallelTransition[] ptArray = new ParallelTransition[]{this.pt1, this.pt2, this.pt3, this.pt4, this.pt5};
        this.PlayAll(ptArray);
    }

    private void bossMoving(MeshView[] boss) {
        this.bossRotation.setDuration(Duration.seconds(1.0d));
        this.bossRotation.setAxis(Rotate.Y_AXIS);
        this.bossRotation.setByAngle(360.0d);
        this.bossRotation.setAutoReverse(true);
        this.bossRotation.setCycleCount(2);
        for (int i = 8; i < 15; i++) {
            this.bossRotation.setNode(boss[i]);
        }
    }

    private void PlayAll(ParallelTransition[] ptArray) {
        for (ParallelTransition pt : ptArray) {
            pt.play();
        }
    }

    private void StonesRects(Sphere[] stones) {
        this.s1Rect = stones[0].getBoundsInParent();
        this.s2Rect = stones[1].getBoundsInParent();
        this.s3Rect = stones[2].getBoundsInParent();
        this.s4Rect = stones[3].getBoundsInParent();
        this.s5Rect = stones[4].getBoundsInParent();
    }

    private Node[] intiStones() {
        this.ston1 = new Sphere();
        this.ston2 = new Sphere();
        this.ston3 = new Sphere();
        this.ston4 = new Sphere();
        this.ston5 = new Sphere();
        PhongMaterial stonesImages = (PhongMaterial) StonesImages();
        Sphere[] stonesArray = new Sphere[]{this.ston1, this.ston2, this.ston3, this.ston4, this.ston5};
        stonesArray[0].translateXProperty().set(200);
        stonesArray[1].translateXProperty().set(260);
        stonesArray[2].translateXProperty().set(320);
        stonesArray[3].translateXProperty().set(380);
        stonesArray[4].translateXProperty().set(440);
        for (Sphere stonesArray1 : stonesArray) {
            stonesArray1.setRadius(28.0);
            stonesArray1.translateYProperty().set(370.0);
            stonesArray1.translateZProperty().set(6000.0);
            stonesArray1.setMaterial(stonesImages);
            stonesArray1.toBack();
        }
        return stonesArray;
    }

    private PhongMaterial StonesImages() {
        PhongMaterial stonesImages = new PhongMaterial();
        stonesImages.setDiffuseMap(this.stone);
        return stonesImages;
    }

    private void keyEvents(Stage s, Sphere tgo, TriangleMesh woodMesh,
            Cylinder shadow, MeshView[] bullet, Node[] gun) {
        s.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            KeyCode keycode = evt.getCode();
            switch (keycode) {
                case S:
                case UP:
                    this.upButtonPressed(tgo, shadow);
                    break;
                case Z:
                case LEFT:
                    this.leftButtonPressed(tgo, woodMesh);
                    break;
                case X:
                case RIGHT:
                    this.rightButtonPressed(tgo, woodMesh);
                    break;
                case SPACE:
                    this.shoot(bullet, tgo, gun);
                    break;
                default:
                    break;
            }
        });
    }

    private void upButtonPressed(Sphere tgo, Cylinder shadow) {
        this.jump = true;
        this.jump(tgo, shadow);
    }

    private void leftButtonPressed(final Sphere tgo, final TriangleMesh woodMesh) {
        Task<javafx.geometry.Point3D> task = this.checkSphereTriangleMeshDY(tgo, woodMesh);
        task.setOnSucceeded(evt -> {
            javafx.geometry.Point3D resPoint = task.getValue();
            // System.out.println("tgo bottom point: " + "[" + tgo.getTranslateX() + ", " + (tgo.getTranslateY() + tgo.getRadius()) + ", " + tgo.getTranslateZ() + "]");
            // System.out.println("closest point: " + resPoint.toString());
            double tgoY = tgo.getTranslateY(), dy = resPoint.getY() - (tgoY + tgo.getRadius());
            // System.out.println("dy = " + dy);
            tgo.setTranslateY(tgoY + dy);
        });
        tgo.translateXProperty().set(tgo.getTranslateX() - 10);
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    private void rightButtonPressed(final Sphere tgo, final TriangleMesh woodMesh) {
        Task<javafx.geometry.Point3D> task = this.checkSphereTriangleMeshDY(tgo, woodMesh);
        task.setOnSucceeded(evt -> {
            javafx.geometry.Point3D resPoint = task.getValue();
            // System.out.println("tgo bottom point: " + "[" + tgo.getTranslateX() + ", " + (tgo.getTranslateY() + tgo.getRadius()) + ", " + tgo.getTranslateZ() + "]");
            // System.out.println("closest point: " + resPoint.toString());
            double tgoY = tgo.getTranslateY(), dy = resPoint.getY() - (tgoY + tgo.getRadius());
            // System.out.println("dy = " + dy);
            tgo.setTranslateY(tgoY + dy);
        });
        tgo.translateXProperty().set(tgo.getTranslateX() + 10);
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    private void aButtonPressed(Stage stage) {
        this.soeffects(this.TakePhotoSound);
        this.TakePhoto(stage);
    }

    private void r2ButtonPressed(MeshView[] bullet, Sphere tgo, Node[] gun) {
        this.shoot(bullet, tgo, gun);
    }

    private void TakePhoto(Stage s) {
        if (s != null && s.sceneProperty() != null) {
            Scene thisScene = s.sceneProperty().get();
            Group root = (Group) thisScene.getRoot();
            File toKnowFile = null;
            File toSave = new File("Screenshots/screenshot" + String.valueOf(this.num) + ".jpg");
            try {
                toKnowFile = File.createTempFile("screenshot" + String.valueOf(this.num), ".jpg", new File("Screenshots/"));
            } catch (IOException ex) {
                Logger.getLogger(Level5.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (toKnowFile.exists()) {
                this.num++;
            }
            try {
                Point2D p = root.localToScreen(0, 0);
                int x = (int) p.getX(), y = (int) p.getY();
                File screenshot = new File(toSave.getAbsolutePath());
                Robot TakePhoto = new Robot();
                java.awt.Rectangle ScreenPhoto = new java.awt.Rectangle(x, y, 685, 580);
                BufferedImage capture = TakePhoto.createScreenCapture(ScreenPhoto);
                ImageIO.write(capture, "jpg", screenshot);
            } catch (AWTException | IOException ex) {
                Logger.getLogger(Level5.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void jump(Sphere tgo, Cylinder shadow) {
        this.jumping.setDuration(Duration.millis(250));
        this.jumping.setToY(tgo.getTranslateY() - 180);
        this.jumping.setAutoReverse(true);
        this.jumping.setCycleCount(2);
        this.jumping.setNode(tgo);
        this.soeffects(this.fallSound);
        this.shadowMovement.setDuration(Duration.millis(200));
        this.shadowMovement.setToX(shadow.getTranslateX() + 100);
        this.shadowMovement.setAutoReverse(true);
        this.shadowMovement.setCycleCount(2);
        this.shadowMovement.setNode(shadow);
        if (this.jump) {
            this.jumping.play();
            this.shadowMovement.play();
        }
        this.jumping.setOnFinished((ActionEvent evt) -> {
            this.soeffects(this.hitGroundSound);
            this.jump = false;
        });
    }

    private void fall(Sphere tgo) {
        this.falling.setDuration(Duration.millis(300));
        this.falling.setToY(tgo.getTranslateY() + 2000);
        this.falling.setNode(tgo);
        this.falling.play();
        this.falling.setOnFinished((ActionEvent evt) -> {
            this.soeffects(fallSound);
            this.lose();
        });
    }

    private void lose() {
        this.animationtimer.stop();
        this.timer.cancel();
        this.pt1.stop();
        this.pt2.stop();
        this.pt3.stop();
        this.pt4.stop();
        this.pt5.stop();
        this.moveByPath.stop();
        this.soeffects(this.gameOver);
        Stage s = (Stage) this.loseAlert.getDialogPane().getScene().getWindow();
        s.setAlwaysOnTop(true);
        s.toFront();
        this.loseAlert.show();
        this.loseAlert.setOnCloseRequest(new EventHandler() {
            @Override
            public void handle(Event t) {
                if (Level5.this.loseAlert.getResult() == Level5.this.TryAgain) {
                    try {
                        Runtime.getRuntime().exec(new File("THE GREEN ONE GAME.exe").getAbsolutePath());
                    } catch (IOException ex) {
                        Logger.getLogger(Level5.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        ClosingOperation.closeBackendProgram();
                        System.exit(0);
                    }
                } else if (Level5.this.loseAlert.getResult() == Level5.this.Exit) {
                    ClosingOperation.closeBackendProgram();
                    System.exit(0);
                }
            }
        });
    }

    private Sphere tgo() {
        this.tgo = new Sphere();
        PhongMaterial back = (PhongMaterial) tgoBack();
        this.tgo.setMaterial(back);
        this.tgo.setRadius(35.0);
        this.tgo.translateXProperty().set(345.0);
        this.tgo.translateYProperty().set(370.0);
        this.tgo.translateZProperty().set(-520.0);
        return tgo;
    }

    public Task<javafx.geometry.Point3D> checkSphereTriangleMeshDY(final Sphere sphere, final TriangleMesh mesh) {
        return new Task<javafx.geometry.Point3D>() {
            @Override
            protected javafx.geometry.Point3D call() throws Exception {
                double radius = sphere.getRadius();
                javafx.geometry.Point3D[] meshPoints = Level5.this.convertObservableFloatArrayToJavaFXPoint3DArray(mesh.getPoints());
                javafx.geometry.Point3D sphereBottomPoint = new javafx.geometry.Point3D(
                        sphere.getTranslateX(),
                        sphere.getTranslateY() + radius + 10.0,
                        sphere.getTranslateZ()
                );
                javafx.geometry.Point3D translationPoint = new javafx.geometry.Point3D(340.0 * Math.cos(-89.0), 380.0, -400.0 * Math.sin(90.0)),
                        meshPointAfterTransformation = meshPoints[0].add(translationPoint),
                        resPoint = meshPointAfterTransformation;
                double minDistance = sphereBottomPoint.distance(meshPointAfterTransformation);
                for (javafx.geometry.Point3D meshPoint : meshPoints) {
                    meshPointAfterTransformation = meshPoint.add(translationPoint);
                    double distance = sphereBottomPoint.distance(meshPointAfterTransformation);
                    if (distance < minDistance) {
                        minDistance = distance;
                        resPoint = meshPointAfterTransformation;
                    }
                }
                return resPoint;
            }
        };
    }

    private Text count() {
        Text counter = new Text();
        counter.setText(Integer.toString(this.counter));
        counter.setFill(Color.BLACK);
        counter.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
        counter.setTranslateX(620);
        counter.setTranslateY(17);
        return counter;
    }

    private PhongMaterial grass() {
        PhongMaterial grass = new PhongMaterial();
        Image grassImage = new Image(getClass().getResourceAsStream("/sources/grass.jpg"));
        grass.setDiffuseMap(grassImage);
        return grass;
    }

    private PhongMaterial tgoBack() {
        PhongMaterial tgoBack = new PhongMaterial();
        Image backImage = new Image(getClass().getResourceAsStream("/sources/tgoBack.png"));
        tgoBack.setDiffuseMap(backImage);
        return tgoBack;
    }

    private CuboidMesh Wood() {
        CuboidMesh wood = new CuboidMesh(1000, 150, 20, 7, null);
        wood.setMesh(this.applyPerlinNoiseToWood((TriangleMesh) wood.getMesh()));
        wood.translateXProperty().set(340.0);
        wood.translateYProperty().set(380.0);
        wood.translateZProperty().set(-400.0);
        Transform tx = new Rotate();
        Transform ty = new Rotate();
        Transform tz = new Rotate();
        Rotate xRotate = new Rotate(-89.0, Rotate.X_AXIS);
        Rotate yRotate = new Rotate(0.0, Rotate.Y_AXIS);
        Rotate zRotate = new Rotate(90.0, Rotate.Z_AXIS);
        tx = tx.createConcatenation(xRotate);
        ty = ty.createConcatenation(yRotate);
        tz = tz.createConcatenation(zRotate);
        wood.getTransforms().add(tx);
        wood.getTransforms().add(ty);
        wood.getTransforms().add(tz);
        PhongMaterial grass = (PhongMaterial) grass();
        wood.setMaterial(grass);
        return wood;
    }

    private TriangleMesh applyPerlinNoiseToWood(TriangleMesh woodTriangleMesh) {
        PerlinNoise perlinNoise = new PerlinNoise();
        Point3D[] woodPoints = this.convertObservableFloatArrayToPoint3DArray(woodTriangleMesh.getPoints());
        for (Point3D woodPoint : woodPoints) {
            double noise = perlinNoise.noise(
                    woodPoint.x * 0.01,
                    woodPoint.y * 0.01,
                    woodPoint.z * 0.01
            );
            woodPoint.z = (float) noise * 50;
        }
        woodTriangleMesh.getPoints().clear();
        woodTriangleMesh.getPoints().addAll(this.convertPoint3DArrayToObservableFloatArray(woodPoints));
        return woodTriangleMesh;
    }

    private Point3D[] convertObservableFloatArrayToPoint3DArray(final ObservableFloatArray points) {
        Point3D[] point3DArray = new Point3D[points.size() / 3];
        for (int i = 0; i < points.size(); i += 3) {
            point3DArray[i / 3] = new Point3D(
                    points.get(i),
                    points.get(i + 1),
                    points.get(i + 2)
            );
        }
        return point3DArray;
    }

    private javafx.geometry.Point3D[] convertObservableFloatArrayToJavaFXPoint3DArray(final ObservableFloatArray points) {
        javafx.geometry.Point3D[] point3DArray = new javafx.geometry.Point3D[points.size() / 3];
        for (int i = 0; i < points.size(); i += 3) {
            point3DArray[i / 3] = new javafx.geometry.Point3D(
                    points.get(i),
                    points.get(i + 1),
                    points.get(i + 2)
            );
        }
        return point3DArray;
    }

    private ObservableFloatArray convertPoint3DArrayToObservableFloatArray(final Point3D[] points) {
        float[] floatArray = new float[points.length * 3];
        for (int i = 0; i < points.length; i++) {
            floatArray[i * 3] = (float) points[i].x;
            floatArray[i * 3 + 1] = (float) points[i].y;
            floatArray[i * 3 + 2] = (float) points[i].z;
        }
        return FXCollections.observableFloatArray(floatArray);
    }

    private Effect Brightness() {
        this.bright = new DropShadow();
        this.bright.setBlurType(BlurType.GAUSSIAN);
        this.bright.setColor(Color.rgb(255, 224, 0, 0.75));
        this.bright.setHeight(55);
        this.bright.setWidth(55);
        this.bright.setOffsetX(5);
        this.bright.setOffsetY(5);
        this.bright.setSpread(0.6);
        this.bright.setRadius(40);
        return this.bright;
    }

    private Node Sun() {
        Circle sun = new Circle();
        sun.setFill(this.SunColor);
        sun.setRadius(40);
        sun.setLayoutX(12);
        sun.setLayoutY(12);
        sun.setEffect(this.Brightness());
        return sun;
    }

    private Node Shadow(Circle lightSource, Sphere object) {
        this.shadowMaterial = new PhongMaterial();
        this.shadowMaterial.setDiffuseColor(this.shadowColor);
        double[][] Mshadow = new double[4][4], objectCoords = new double[4][1], shadowCoords = new double[4][1];
        this.lightSourceCoords[0] = lightSource.getTranslateX();
        this.lightSourceCoords[1] = lightSource.getTranslateY();
        this.lightSourceCoords[2] = object.getTranslateZ() + object.getRadius();
        objectCoords[0][0] = object.getTranslateX() + object.getRadius();
        objectCoords[1][0] = object.getTranslateY() + object.getRadius();
        objectCoords[2][0] = object.getTranslateZ() + object.getRadius();
        Mshadow[0][0] = 1;
        Mshadow[0][2] = -this.lightSourceCoords[0] / this.lightSourceCoords[2];
        Mshadow[1][1] = 1;
        Mshadow[1][2] = -this.lightSourceCoords[1] / this.lightSourceCoords[2];
        Mshadow[2][2] = 0.0d;
        Mshadow[3][3] = 1;
        shadowCoords = this.multiplyMatrices(Mshadow, objectCoords);
        Cylinder shadow = new Cylinder();
        shadow.setMaterial(this.shadowMaterial);
        shadow.setRadius(object.getRadius());
        shadow.setHeight(0.1f);
        shadow.setCullFace(CullFace.BACK);
        shadow.setDrawMode(DrawMode.FILL);
        shadow.translateXProperty().set(shadowCoords[0][0]);
        shadow.translateYProperty().set(objectCoords[1][0]);
        shadow.translateZProperty().set(objectCoords[2][0]);
        return shadow;
    }

    private void renderingShadow(Sphere object, Cylinder shadow, CuboidMesh wood) {
        double[][] Mshadow = new double[4][4], objectCoords = new double[4][1], shadowCoords = new double[4][1];
        objectCoords[0][0] = object.getTranslateX() + object.getRadius();
        objectCoords[1][0] = object.getTranslateY() + object.getRadius();
        objectCoords[2][0] = object.getTranslateZ() + object.getRadius();
        Mshadow[0][0] = 1;
        Mshadow[0][2] = -this.lightSourceCoords[0] / this.lightSourceCoords[2];
        Mshadow[1][1] = 1;
        Mshadow[1][2] = -this.lightSourceCoords[1] / this.lightSourceCoords[2];
        Mshadow[2][2] = 0.0d;
        Mshadow[3][3] = 1;
        shadowCoords = this.multiplyMatrices(Mshadow, objectCoords);
        if (!this.jump) {
            shadow.translateXProperty().set(shadowCoords[0][0]);
        }
        this.checkShadowEdge(shadow, wood);
    }

    private void checkShadowEdge(Cylinder shadow, CuboidMesh wood) {
        this.shadowRect.setBounds((int) shadow.getTranslateX(), (int) shadow.getTranslateY(), (int) shadow.getRadius() * 2, (int) shadow.getRadius());
        this.rightRect.setBounds((int) ((wood.getTranslateX() + wood.getHeight()) - 15), (int) wood.getTranslateY(), 150, 50);
        this.leftRect.setBounds((int) wood.getTranslateX() - 80, (int) wood.getTranslateY(), 10, 50);
        if (this.shadowRect.intersects(this.leftRect) || this.shadowRect.intersects(this.rightRect)) {
            this.shadowMaterial.setDiffuseColor(this.shadowColorTransparent);
            shadow.setMaterial(this.shadowMaterial);
        } else if (!this.shadowRect.intersects(this.leftRect) && !this.shadowRect.intersects(this.rightRect)) {
            this.shadowMaterial.setDiffuseColor(this.shadowColor);
            shadow.setMaterial(this.shadowMaterial);
        }
    }

    private void soeffects(File file) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            System.out.print(ex);
        }
    }

    private double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }
        return result;
    }

    private double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }
}
