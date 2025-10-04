package level5;

import java.awt.geom.Rectangle2D;

public final class AABB3D extends Rectangle3D {

    public double x;
    public double y;
    public double z;
    public double sizeX;
    public double sizeY;
    public double sizeZ;

    public AABB3D(double translateX, double translateY, double translateZ, double sizeX, double sizeY, double sizeZ) {
        this.setRect(translateX, translateY, translateZ, sizeX, sizeY, sizeZ);
    }

    @Override
    public Rectangle3D createIntersection(Rectangle3D r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle3D createUnion(Rectangle3D r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRect(double x, double y, double z, double sizeX, double sizeY, double sizeZ) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getZ() {
        return this.z;
    }

    @Override
    public double getSizeX() {
        return this.sizeX;
    }

    @Override
    public double getSizeY() {
        return this.sizeY;
    }

    @Override
    public double getSizeZ() {
        return this.sizeZ;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public void setSizeX(double value) {
        this.sizeX = value;
    }

    @Override
    public void setSizeY(double value) {
        this.sizeY = value;
    }

    @Override
    public void setSizeZ(double value) {
        this.sizeZ = value;
    }

    @Override
    public Rectangle2D toRectangle2D() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
