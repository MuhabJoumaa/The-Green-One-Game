package the.green.one.game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Projectile {

    private final File file = new File(new File("pics/projectile.png").getAbsolutePath());
    private BufferedImage image, rotatedImage;
    private final double THETA = 45.0;
    private double mass = 0.0, velocity = 0.0, A = 0.0, rotationAngle = 0.0;
    private List<List<Double>> positions;
    private List<Double> evenIndexList = new ArrayList<>(), oddIndexList = new ArrayList<>();
    private int x = 0, y = 0;
    private final Rectangle rectangle = new Rectangle(0, 0, 0, 0);
    private boolean isMoving = false;
    private int currentIndexOfPositions = 0;

    public Projectile() {
        try {
            this.image = ImageIO.read(this.file);
            this.rotatedImage = this.image;
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
    }

    public double getTHETA() {
        return this.THETA;
    }

    public double getMass() {
        return this.mass;
    }

    public double getVelocity() {
        return this.velocity;
    }

    public double getA() {
        return this.A;
    }

    public double getRotationAngle() {
        return this.rotationAngle;
    }

    public List<List<Double>> getPositions() {
        return this.positions;
    }

    public List<Double> getEvenIndexList() {
        return this.evenIndexList;
    }

    public List<Double> getOddIndexList() {
        return this.oddIndexList;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public boolean isMoving() {
        return this.isMoving;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public BufferedImage getRotatedImage() {
        return this.rotatedImage;
    }

    public int getCurrentIndexOfPositions() {
        return this.currentIndexOfPositions;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void setA(double A) {
        this.A = A;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public void setPositions(List<List<Double>> positions) {
        this.positions = positions;
    }

    public void setEvenIndexList(List<Double> evenIndexList) {
        this.evenIndexList = evenIndexList;
    }

    public void setOddIndexList(List<Double> oddIndexList) {
        this.oddIndexList = oddIndexList;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMoving(boolean moving) {
        this.isMoving = moving;
    }

    public void setRotatedImage(BufferedImage rotatedImage) {
        this.rotatedImage = rotatedImage;
    }

    public void setCurrentIndexOfPositions(int currentIndexOfPositions) {
        this.currentIndexOfPositions = currentIndexOfPositions;
    }
}
