package introfx;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
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
import javafx.scene.shape.Sphere;
import javafx.scene.text.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class IntroFX extends Application {

    public File WalkingEffect = null;
    public File MeteorEffect = null;
    String title = "The Green One Game";
    double angelX = 0;
    double angelY = 0;
    double angelZ = 0;
    int time = 540;
    private final Timer t = new Timer();
    int delay = 50;
    int per = 50;
    soeffects cl = new soeffects();
    public Rectangle2D RectOfTgo;
    public Rectangle2D RectOfStone;
    static IntroFX fx = new IntroFX();
    static IntroFX.timer timing = fx.new timer();
    static final double DepthOfTgo = 4000;
    static final double DepthOfStone = 7000;
    public TranslateTransition SunMoving;
    public ScaleTransition SunDecreasing;
    public FillTransition SunColoring;
    public FadeTransition SunHiding;
    public ParallelTransition parallelChanging;
    public KeyFrame frame;
    public Timeline HideBG;
    DropShadow bright;
    Color SunColor = Color.rgb(255, 224, 0, 0.91);
    Color SunHide = Color.rgb(255, 62, 0, 0.91);
    static double Y = 900.0;

    public IntroFX() {
        this.WalkingEffect = new File("sounds/WalkingEffect.wav");
        this.MeteorEffect = new File("sounds/MeteorEffect.wav");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("");
        stage.setWidth(400);
        stage.setHeight(400);
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/sources/bg.png")));
        stage.centerOnScreen();
        Sphere tgo3D = new Sphere(130);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        group root = new group();
        Camera camera = new PerspectiveCamera();
        Image image = new Image(getClass().getResourceAsStream("/sources/bg1.jpg"));
        ImageView bg = new ImageView(image);
        Image tgo = new Image(getClass().getResourceAsStream("/sources/bg.png"));
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(tgo);
        tgo3D.setMaterial(material);
        tgo3D.setId("Background");
        tgo3D.translateXProperty().set(100.0);
        tgo3D.translateYProperty().set(Y);
        tgo3D.translateZProperty().set(DepthOfTgo);
        timing.takeTGO3D(tgo3D, DepthOfTgo, Y, this.WalkingEffect);
        DropShadow shadow = new DropShadow();
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.rgb(97, 239, 53, 1.0));
        shadow.setHeight(30);
        shadow.setWidth(15);
        shadow.setOffsetX(5);
        shadow.setOffsetY(6);
        shadow.setSpread(0.5);
        shadow.setRadius(20);
        Text text = new Text();
        text.setText(title);
        text.setTranslateX(100.0);
        text.setTranslateY(380.0);
        text.setId("title");
        text.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
        text.setFill(Color.GREEN);
        text.setEffect(shadow);
        Circle Sun = (Circle) Sun();
        // animation effects
        SunMoving = new TranslateTransition();
        SunMoving.setDuration(Duration.seconds(92));
        SunMoving.setToX(2000);
        SunMoving.setToY(600);
        SunMoving.setNode(Sun);
        SunDecreasing = new ScaleTransition();
        SunDecreasing.setDuration(Duration.seconds(92));
        SunDecreasing.setToX(-2);
        SunDecreasing.setToY(-2);
        SunDecreasing.setNode(Sun);
        SunHiding = new FadeTransition();
        SunHiding.setDuration(Duration.seconds(40));
        SunHiding.setFromValue(9);
        SunHiding.setToValue(0.2);
        SunHiding.setNode(Sun);
        SunColoring = new FillTransition(Duration.seconds(45), Sun, SunColor, SunHide);
        parallelChanging = new ParallelTransition(Sun, SunMoving, SunDecreasing, SunColoring, SunHiding);
        EventHandler ChangeOtherEffects = new EventHandler() {
            double CurrentOpacity = bg.getOpacity();
            double CurrentSpreadOfSun = bright.getSpread();

            @Override
            public void handle(Event evt) {
                bg.setOpacity(CurrentOpacity);
                bright.setSpread(CurrentSpreadOfSun);
                CurrentSpreadOfSun -= 0.0577;
                CurrentOpacity -= 0.07;
                System.out.println("t");
            }
        };
        root.getChildren().add(bg);
        root.getChildren().add(tgo3D);
        root.getChildren().add(text);
        root.getChildren().add(Stone());
        root.getChildren().add(Sun);
        PointLight SunLight = new PointLight();
        SunLight.setColor(Color.LIGHTYELLOW);
        SunLight.getTransforms().setAll(Sun.getTransforms());
        root.getChildren().add(SunLight);
        Scene s = new Scene(root, 400, 400, false);
        s.setCamera(camera);
        // move camera
        stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            Rotate rotateCamera;
            Transform cameraTransform = new Rotate();

            @Override
            public void handle(KeyEvent event) {
                double AngelOfY = 0;
                double AngelOfX = 0;
                KeyCode KeyCode = event.getCode();
                switch (KeyCode) {
                    case W:
                        AngelOfY -= 5.0;
                        rotateCamera = new Rotate(AngelOfY, Rotate.Y_AXIS);
                        cameraTransform = cameraTransform.createConcatenation(rotateCamera);
                        root.getTransforms().clear();
                        root.getTransforms().addAll(cameraTransform);
                        break;
                    case S:
                        AngelOfY += 5.0;
                        rotateCamera = new Rotate(AngelOfY, Rotate.Y_AXIS);
                        cameraTransform = cameraTransform.createConcatenation(rotateCamera);
                        root.getTransforms().clear();
                        root.getTransforms().addAll(cameraTransform);
                        break;
                    case D:
                        AngelOfX -= 5.0;
                        rotateCamera = new Rotate(AngelOfX, Rotate.X_AXIS);
                        cameraTransform = cameraTransform.createConcatenation(rotateCamera);
                        root.getTransforms().clear();
                        root.getTransforms().addAll(cameraTransform);
                        break;
                    case A:
                        AngelOfX += 5.0;
                        rotateCamera = new Rotate(AngelOfX, Rotate.X_AXIS);
                        cameraTransform = cameraTransform.createConcatenation(rotateCamera);
                        root.getTransforms().clear();
                        root.getTransforms().addAll(cameraTransform);
                        break;
                }
            }
        });
        stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            Rotate rotateTgo;
            Transform tgoTransform = new Rotate();

            @Override
            public void handle(KeyEvent evt) {
                int AngelOfY = 0;
                int AngelOfX = 0;
                KeyCode KeyCode = evt.getCode();
                switch (KeyCode) {
                    case W:
                        AngelOfX -= 10;
                        rotateTgo = new Rotate(AngelOfX, Rotate.X_AXIS);
                        tgoTransform = tgoTransform.createConcatenation(rotateTgo);
                        tgo3D.getTransforms().clear();
                        tgo3D.getTransforms().addAll(tgoTransform);
                        break;
                    case S:
                        AngelOfX += 10;
                        rotateTgo = new Rotate(AngelOfX, Rotate.X_AXIS);
                        tgoTransform = tgoTransform.createConcatenation(rotateTgo);
                        tgo3D.getTransforms().clear();
                        tgo3D.getTransforms().addAll(tgoTransform);
                        break;
                    case D:
                        AngelOfY -= 10;
                        rotateTgo = new Rotate(AngelOfY, Rotate.Y_AXIS);
                        tgoTransform = tgoTransform.createConcatenation(rotateTgo);
                        tgo3D.getTransforms().clear();
                        tgo3D.getTransforms().addAll(tgoTransform);
                        break;
                    case A:
                        AngelOfY += 10;
                        rotateTgo = new Rotate(AngelOfY, Rotate.Y_AXIS);
                        tgoTransform = tgoTransform.createConcatenation(rotateTgo);
                        tgo3D.getTransforms().clear();
                        tgo3D.getTransforms().addAll(tgoTransform);
                        break;
                    case UP:
                        tgo3D.translateYProperty().set(tgo3D.getTranslateY() - 12);
                        break;
                    case DOWN:
                        tgo3D.translateYProperty().set(tgo3D.getTranslateY() + 12);
                        break;
                    case RIGHT:
                        tgo3D.translateXProperty().set(tgo3D.getTranslateX() + 12);
                        break;
                    case LEFT:
                        tgo3D.translateXProperty().set(tgo3D.getTranslateX() - 12);
                        break;
                }
            }
        });
        s.setFill(Color.rgb(50, 50, 45));
        parallelChanging.play();
        this.Frames(ChangeOtherEffects);
        tgo3D.setOnMousePressed((MouseEvent evt) -> {
            double currentX = evt.getSceneX();
            double currentY = evt.getSceneY();
            double currentZ = evt.getZ();
            this.angelX = currentX;
            this.angelY = currentY;
            this.angelZ = currentZ;
        });
        tgo3D.setOnMouseDragged((MouseEvent evt) -> {
            double x = evt.getSceneX();
            double y = evt.getSceneY();
            double z = evt.getZ();
            root.rotateX(x - this.angelX, s, tgo3D);
            root.rotateY(y - this.angelY, s, tgo3D);
            root.rotateY(z - this.angelZ, s, tgo3D);
            root.all(tgo3D);
        });
        s.getStylesheets().add(getClass().getResource("/sources/begFX.css").toString());
        stage.setScene(s);
        stage.show();
        timing.time();
    }

    public Rectangle2D[] CreatRects(Sphere tgo, Node stn) {
        Sphere stone = (Sphere) stn;
        this.RectOfTgo = new Rectangle2D(tgo.getTranslateX(), tgo.getTranslateY(), tgo.getRadius(), tgo.getRadius());
        this.RectOfStone = new Rectangle2D(stone.getTranslateX(), stone.getTranslateY(), stone.getRadius(), stone.getRadius());
        Rectangle2D[] RectsArray = new Rectangle2D[]{RectOfTgo, RectOfStone};
        return RectsArray;
    }

    public Node Stone() {
        double stoneX = 2100.0;
        double stoneY = -1300.0;
        double stoneZ = 7000.0;
        Sphere stone = new Sphere(50);
        PhongMaterial materialOfStone = new PhongMaterial();
        Image ImageOfStone = new Image(getClass().getResourceAsStream("/sources/ston.png"));
        materialOfStone.setDiffuseMap(ImageOfStone);
        stone.setMaterial(materialOfStone);
        stone.translateXProperty().set(stoneX);
        stone.translateYProperty().set(stoneY);
        stone.translateZProperty().set(stoneZ);
        timing.takeStone3D(stone, stoneZ, stoneY, stoneX);
        return stone;
    }

    public Effect Brightness() {
        bright = new DropShadow();
        bright.setBlurType(BlurType.GAUSSIAN);
        bright.setColor(Color.rgb(255, 224, 0, 0.75));
        bright.setHeight(55);
        bright.setWidth(55);
        bright.setOffsetX(5);
        bright.setOffsetY(5);
        bright.setSpread(0.6);
        bright.setRadius(40);
        return bright;
    }

    public Node Sun() {
        Circle sun = new Circle();
        sun.setFill(SunColor);
        sun.setRadius(40);
        sun.setLayoutX(40);
        sun.setLayoutY(40);
        sun.setFill(Color.BROWN);
        sun.setEffect(Brightness());
        return sun;
    }

    public KeyFrame Frames(EventHandler ChangeOtherEffects) {
        this.frame = new KeyFrame(Duration.seconds(1.5), ChangeOtherEffects);
        this.TimelineOfBG(frame);
        return null;
    }

    public void TimelineOfBG(KeyFrame frame) {
        this.HideBG = new Timeline(frame);
        this.HideBG.setCycleCount(Animation.INDEFINITE);
        this.HideBG.play();
    }

    protected class timer {

        Sphere tgo3D;
        double Zforwards;
        double Y;
        File Effect;
        Sphere stone;
        double StoneZ;
        double StoneY;
        double StoneX;

        public void takeTGO3D(Sphere sphere, double Depth, double YAxis, File effect) {
            tgo3D = sphere;
            Zforwards = Depth;
            Y = YAxis;
            Effect = effect;
        }

        public void takeStone3D(Sphere stone, double DepthOfStone, double YAxis, double XAxis) {
            this.stone = stone;
            StoneZ = DepthOfStone;
            StoneY = YAxis;
            StoneX = XAxis;
        }

        public strictfp void time() {
            t.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    time--;
                    // Changes Coords
                    Y -= ((Y / 540) + 1.15) - 0.555;
                    Zforwards -= ((DepthOfTgo / 540) + 1.3) - 0.555;
                    StoneX -= 3.7411;
                    StoneY += 3.0211;
                    StoneZ -= ((DepthOfStone / 537) + 0.7);
                    // stone
                    stone.translateXProperty().set(StoneX);
                    stone.translateYProperty().set(StoneY);
                    stone.translateZProperty().set(StoneZ);
                    // tgo
                    tgo3D.translateYProperty().set(Y);
                    tgo3D.translateZProperty().set(Zforwards);
                    if (time % 10 == 0) {
                        soeffects(Effect);
                    }
                    Rectangle2D[] Rects = CreatRects(tgo3D, stone);
                    Check(Rects);
                }
            }, delay, per);
        }

        public void Check(Rectangle2D[] RectsOf3D) {
            if (RectsOf3D[0].intersects(RectsOf3D[1]) || time == 0) {
                soeffects(IntroFX.this.MeteorEffect);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(IntroFX.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        }
    }

    protected class group extends Group {

        Rotate rotateX;
        Rotate rotateY;
        Rotate rotateZ;
        Transform transform = new Rotate();

        public void rotateX(double Angel, Scene s, Sphere tgo) {
            Point3D point3d = tgo.getRotationAxis();
            rotateX = new Rotate(Angel - point3d.getX(), Rotate.X_AXIS);
            DoubleProperty angelX = new SimpleDoubleProperty(Angel);
            rotateX.angleProperty().bind(angelX);
            transform = transform.createConcatenation(rotateX);
        }

        public void rotateY(double Angel, Scene s, Sphere tgo) {
            Point3D point3d = tgo.getRotationAxis();
            rotateY = new Rotate(Angel - point3d.getY(), Rotate.Y_AXIS);
            DoubleProperty angelY = new SimpleDoubleProperty(Angel);
            rotateY.angleProperty().bind(angelY);
            transform = transform.createConcatenation(rotateY);
        }

        public void rotateZ(double Angel, Scene s, Sphere tgo) {
            Point3D point3d = tgo.getRotationAxis();
            rotateZ = new Rotate(Angel - point3d.getZ(), Rotate.Z_AXIS);
            DoubleProperty angelZ = new SimpleDoubleProperty(Angel);
            rotateZ.angleProperty().bind(angelZ);
            transform = transform.createConcatenation(rotateZ);
        }

        public void all(Sphere tgo) {
            tgo.getTransforms().clear();
            tgo.getTransforms().add(transform);
        }
    }

    public final void soeffects(File file) {
        try {
            this.cl.clip = AudioSystem.getClip();
            this.cl.clip.open(AudioSystem.getAudioInputStream(file));
            this.cl.clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            System.out.print(ex);
        }
    }
}
