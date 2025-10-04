package level5;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Rectangle3D implements Cloneable {

    public static Rectangle3D intersect(Rectangle3D src1, Rectangle3D src2, Rectangle3D dest) {
        final Rectangle3D result;
        if (dest == null) {
            result = new Rectangle3D.Double();
        } else {
            result = dest;
        }
        final double x1 = Math.max(src1.getMinX(), src2.getMinX());
        final double y1 = Math.max(src1.getMinY(), src2.getMinY());
        final double z1 = Math.max(src1.getMinZ(), src2.getMinZ());
        final double x2 = Math.min(src1.getMaxX(), src2.getMaxX());
        final double y2 = Math.min(src1.getMaxY(), src2.getMaxY());
        final double z2 = Math.min(src1.getMaxZ(), src2.getMaxZ());
        double dx;
        double dy;
        double dz;
        if (x2 == java.lang.Double.POSITIVE_INFINITY) {
            dx = java.lang.Double.POSITIVE_INFINITY;
        } else {
            dx = x2 - x1;
        }
        if (y2 == java.lang.Double.POSITIVE_INFINITY) {
            dy = java.lang.Double.POSITIVE_INFINITY;
        } else {
            dy = y2 - y1;
        }
        if (z2 == java.lang.Double.POSITIVE_INFINITY) {
            dz = java.lang.Double.POSITIVE_INFINITY;
        } else {
            dz = z2 - z1;
        }
        result.setRect(x1, y1, z1, dx, dy, dz);
        return result;
    }

    public abstract Rectangle3D createIntersection(Rectangle3D r);

    public static Rectangle3D union(Rectangle3D src1, Rectangle3D src2, Rectangle3D dest) {
        final Rectangle3D result;
        if (dest == null) {
            result = new Rectangle3D.Double();
        } else {
            result = dest;
        }
        double x1 = Math.min(src1.getMinX(), src2.getMinX());
        double y1 = Math.min(src1.getMinY(), src2.getMinY());
        double z1 = Math.min(src1.getMinZ(), src2.getMinZ());
        double x2 = Math.max(src1.getMaxX(), src2.getMaxX());
        double y2 = Math.max(src1.getMaxY(), src2.getMaxY());
        double z2 = Math.max(src1.getMaxZ(), src2.getMaxZ());
        double dx;
        double dy;
        double dz;
        if (x2 == java.lang.Double.POSITIVE_INFINITY) {
            dx = java.lang.Double.POSITIVE_INFINITY;
        } else {
            dx = x2 - x1;
        }
        if (y2 == java.lang.Double.POSITIVE_INFINITY) {
            dy = java.lang.Double.POSITIVE_INFINITY;
        } else {
            dy = y2 - y1;
        }
        if (z2 == java.lang.Double.POSITIVE_INFINITY) {
            dz = java.lang.Double.POSITIVE_INFINITY;
        } else {
            dz = z2 - z1;
        }
        result.setRect(x1, y1, z1, dx, dy, dz);
        return result;
    }

    public abstract Rectangle3D createUnion(Rectangle3D r);

    public abstract void setRect(double x, double y, double z, double sizeX, double sizeY, double sizeZ);

    public abstract double getX();

    public abstract double getY();

    public abstract double getZ();

    public abstract double getSizeX();

    public abstract double getSizeY();

    public abstract double getSizeZ();

    public Rectangle3D.Integer toInteger() {
        double sx = getSizeX();
        double sy = getSizeY();
        double sz = getSizeZ();
        double x = getX();
        double y = getY();
        double z = getZ();
        int ix = (int) Math.floor(x);
        int iy = (int) Math.floor(y);
        int iz = (int) Math.floor(z);
        int isx;
        int isy;
        int isz;
        if (sx < 0d) {
            isx = 0;
        } else if (sx >= java.lang.Integer.MAX_VALUE) {
            isx = java.lang.Integer.MAX_VALUE;
        } else {
            isx = ((int) Math.ceil(x + sx)) - ix;
        }
        if (sy < 0d) {
            isy = 0;
        } else if (sy >= java.lang.Integer.MAX_VALUE) {
            isy = java.lang.Integer.MAX_VALUE;
        } else {
            isy = ((int) Math.ceil(y + sy)) - iy;
        }
        if (sz < 0d) {
            isz = 0;
        } else if (sz >= java.lang.Integer.MAX_VALUE) {
            isz = java.lang.Integer.MAX_VALUE;
        } else {
            isz = ((int) Math.ceil(z + sz)) - iz;
        }
        return new Rectangle3D.Integer(ix, iy, iz, isx, isy, isz);
    }

    public abstract void setX(double x);

    public abstract void setY(double y);

    public abstract void setZ(double z);

    public abstract void setSizeX(double value);

    public abstract void setSizeY(double value);

    public abstract void setSizeZ(double value);

    public double getMinX() {
        return getX();
    }

    public double getMinY() {
        return getY();
    }

    public double getMinZ() {
        return getZ();
    }

    public double getMaxX() {
        if (getSizeX() == java.lang.Double.POSITIVE_INFINITY) {
            return java.lang.Double.POSITIVE_INFINITY;
        }
        return getX() + getSizeX();
    }

    public double getMaxY() {
        if (getSizeY() == java.lang.Double.POSITIVE_INFINITY) {
            return java.lang.Double.POSITIVE_INFINITY;
        }
        return getY() + getSizeY();
    }

    public double getMaxZ() {
        if (getSizeZ() == java.lang.Double.POSITIVE_INFINITY) {
            return java.lang.Double.POSITIVE_INFINITY;
        }
        return getZ() + getSizeZ();
    }

    public double getCenterX() {
        if (isInfiniteX()) {
            return 0d;
        }
        return getX() + (getSizeX() / 2d);
    }

    public double getCenterY() {
        if (isInfiniteY()) {
            return 0d;
        }
        return getY() + (getSizeY() / 2d);
    }

    public double getCenterZ() {
        if (isInfiniteZ()) {
            return 0d;
        }
        return getZ() + (getSizeZ() / 2d);
    }

    public boolean isEmpty() {
        return (getSizeX() <= 0d) || (getSizeY() <= 0d) || (getSizeZ() <= 0d);
    }

    public boolean isInfiniteX() {
        return (getX() == java.lang.Double.NEGATIVE_INFINITY) && (getSizeX() == java.lang.Double.POSITIVE_INFINITY);
    }

    public boolean isInfiniteY() {
        return (getY() == java.lang.Double.NEGATIVE_INFINITY) && (getSizeY() == java.lang.Double.POSITIVE_INFINITY);
    }

    public boolean isInfiniteZ() {
        return (getZ() == java.lang.Double.NEGATIVE_INFINITY) && (getSizeZ() == java.lang.Double.POSITIVE_INFINITY);
    }

    public void setInfiniteX() {
        setX(java.lang.Double.NEGATIVE_INFINITY);
        setSizeX(java.lang.Double.POSITIVE_INFINITY);
    }

    public void setInfiniteY() {
        setY(java.lang.Double.NEGATIVE_INFINITY);
        setSizeY(java.lang.Double.POSITIVE_INFINITY);
    }

    public void setInfiniteZ() {
        setZ(java.lang.Double.NEGATIVE_INFINITY);
        setSizeZ(java.lang.Double.POSITIVE_INFINITY);
    }

    public Rectangle3D getBounds() {
        return new Rectangle3D.Double(this);
    }

    public boolean contains(double x, double y, double z) {
        return (x >= getMinX()) && (y >= getMinY()) && (z >= getMinZ()) && (x < getMaxX()) && (y < getMaxY())
                && (z < getMaxZ());
    }

    public boolean contains(double x, double y, double z, double sizeX, double sizeY, double sizeZ) {
        final double maxX;
        final double maxY;
        final double maxZ;
        if (sizeX == java.lang.Double.POSITIVE_INFINITY) {
            maxX = java.lang.Double.POSITIVE_INFINITY;
        } else {
            maxX = x + sizeX;
        }
        if (sizeY == java.lang.Double.POSITIVE_INFINITY) {
            maxY = java.lang.Double.POSITIVE_INFINITY;
        } else {
            maxY = y + sizeY;
        }
        if (sizeZ == java.lang.Double.POSITIVE_INFINITY) {
            maxZ = java.lang.Double.POSITIVE_INFINITY;
        } else {
            maxZ = z + sizeZ;
        }
        return (x >= getMinX()) && (y >= getMinY()) && (z >= getMinZ()) && (maxX <= getMaxX()) && (maxY <= getMaxY())
                && (maxZ <= getMaxZ());
    }

    public boolean contains(Rectangle3D rect) {
        return contains(rect.getX(), rect.getY(), rect.getZ(), rect.getSizeX(), rect.getSizeY(), rect.getSizeZ());
    }

    public boolean intersects(double x, double y, double z, double sizeX, double sizeY, double sizeZ) {
        final double maxX;
        final double maxY;
        final double maxZ;
        if (sizeX == java.lang.Double.POSITIVE_INFINITY) {
            maxX = java.lang.Double.POSITIVE_INFINITY;
        } else {
            maxX = x + sizeX;
        }
        if (sizeY == java.lang.Double.POSITIVE_INFINITY) {
            maxY = java.lang.Double.POSITIVE_INFINITY;
        } else {
            maxY = y + sizeY;
        }
        if (sizeZ == java.lang.Double.POSITIVE_INFINITY) {
            maxZ = java.lang.Double.POSITIVE_INFINITY;
        } else {
            maxZ = z + sizeZ;
        }
        /*System.out.println("maxX = " + maxX + ", " + "getMinX = " + getMinX() + ", " +
                "maxY = " + maxY + ", " + "getMinY = " + getMinY() + ", " +
                "maxZ = " + maxZ + ", " + "getMinZ = " + getMinZ() + ", " +
                "x = " + x + ", " + "getMaxX = " + getMaxX() + ", " +
                "y = " + y + ", " + "getMaxY = " + getMaxY() + ", " +
                "z = " + z + ", " + "getMaxZ = " + getMaxZ());
        System.out.println();*/
        return (maxX > getMinX()) && (maxY > getMinY()) && (maxZ > getMinZ()) && (x < getMaxX()) && (y < getMaxY())
                && (z < getMaxZ());
    }

    public boolean intersects(Rectangle3D rect) {
        return intersects(rect.getX(), rect.getY(), rect.getZ(), rect.getSizeX(), rect.getSizeY(), rect.getSizeZ());
    }

    public void add(double newx, double newy, double newz) {
        double x1 = Math.min(getMinX(), newx);
        double x2 = Math.max(getMaxX(), newx);
        double y1 = Math.min(getMinY(), newy);
        double y2 = Math.max(getMaxY(), newy);
        double z1 = Math.min(getMinZ(), newz);
        double z2 = Math.max(getMaxZ(), newz);
        double dx;
        double dy;
        double dz;
        if (x2 == java.lang.Double.POSITIVE_INFINITY) {
            dx = java.lang.Double.POSITIVE_INFINITY;
        } else {
            dx = x2 - x1;
        }
        if (y2 == java.lang.Double.POSITIVE_INFINITY) {
            dy = java.lang.Double.POSITIVE_INFINITY;
        } else {
            dy = y2 - y1;
        }
        if (z2 == java.lang.Double.POSITIVE_INFINITY) {
            dz = java.lang.Double.POSITIVE_INFINITY;
        } else {
            dz = z2 - z1;
        }
        setRect(x1, y1, z1, dx, dy, dz);
    }

    public void add(Rectangle3D r) {
        union(this, r, this);
    }

    public abstract Rectangle2D toRectangle2D();

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Rectangle3D) {
            final Rectangle3D rect = (Rectangle3D) obj;
            return (getX() == rect.getX()) && (getY() == rect.getY()) && (getZ() == rect.getZ())
                    && (getSizeX() == rect.getSizeX()) && (getSizeY() == rect.getSizeY())
                    && (getSizeZ() == rect.getSizeZ());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        long bits = java.lang.Double.doubleToLongBits(getX());
        bits ^= java.lang.Double.doubleToLongBits(getY());
        bits ^= java.lang.Double.doubleToLongBits(getZ());
        bits ^= java.lang.Double.doubleToLongBits(getSizeX());
        bits ^= java.lang.Double.doubleToLongBits(getSizeY());
        bits ^= java.lang.Double.doubleToLongBits(getSizeZ());
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + "[" + getX() + "," + getY() + "," + getZ() + " - " + getSizeX() + ","
                + getSizeY() + "," + getSizeZ() + "]";
    }

    public static class Double extends Rectangle3D {

        public double x;
        public double y;
        public double z;
        public double sizeX;
        public double sizeY;
        public double sizeZ;

        public Double(double x, double y, double z, double sizeX, double sizeY, double sizeZ) {
            super();
            this.x = x;
            this.y = y;
            this.z = z;
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.sizeZ = sizeZ;
        }

        public Double(Rectangle3D r) {
            this(r.getX(), r.getY(), r.getZ(), r.getSizeX(), r.getSizeY(), r.getSizeZ());
        }

        public Double() {
            this(0, 0, 0, 0, 0, 0);
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
            return x;
        }

        @Override
        public void setX(double value) {
            x = value;
        }

        @Override
        public double getY() {
            return y;
        }

        @Override
        public void setY(double value) {
            y = value;
        }

        @Override
        public double getZ() {
            return z;
        }

        @Override
        public void setZ(double value) {
            z = value;
        }

        @Override
        public double getSizeX() {
            return sizeX;
        }

        @Override
        public void setSizeX(double value) {
            sizeX = value;
        }

        @Override
        public double getSizeY() {
            return sizeY;
        }

        @Override
        public void setSizeY(double value) {
            sizeY = value;
        }

        @Override
        public double getSizeZ() {
            return sizeZ;
        }

        @Override
        public void setSizeZ(double value) {
            sizeZ = value;
        }

        @Override
        public Rectangle3D createIntersection(Rectangle3D r) {
            final Rectangle3D.Double result = new Rectangle3D.Double();
            intersect(this, r, result);
            return result;
        }

        @Override
        public Rectangle3D createUnion(Rectangle3D r) {
            final Rectangle3D.Double result = new Rectangle3D.Double();

            union(this, r, result);

            return result;
        }

        @Override
        public Rectangle2D toRectangle2D() {
            return new Rectangle2D.Double(x, y, sizeX, sizeY);
        }
    }

    public static class Float extends Rectangle3D {

        public float x;
        public float y;
        public float z;
        public float sizeX;
        public float sizeY;
        public float sizeZ;

        public Float(float x, float y, float z, float sizeX, float sizeY, float sizeZ) {
            super();
            this.x = x;
            this.y = y;
            this.z = z;
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.sizeZ = sizeZ;
        }

        public Float(Rectangle3D r) {
            this((float) r.getX(), (float) r.getY(), (float) r.getZ(), (float) r.getSizeX(), (float) r.getSizeY(),
                    (float) r.getSizeZ());
        }

        public Float() {
            this(0, 0, 0, 0, 0, 0);
        }

        @Override
        public void setRect(double x, double y, double z, double sizeX, double sizeY, double sizeZ) {
            this.x = (float) x;
            this.y = (float) y;
            this.z = (float) z;
            this.sizeX = (float) sizeX;
            this.sizeY = (float) sizeY;
            this.sizeZ = (float) sizeZ;
        }

        @Override
        public double getX() {
            if (x == java.lang.Float.NEGATIVE_INFINITY) {
                return java.lang.Double.NEGATIVE_INFINITY;
            }
            if (x == java.lang.Float.POSITIVE_INFINITY) {
                return java.lang.Double.POSITIVE_INFINITY;
            }
            return x;
        }

        @Override
        public void setX(double value) {
            x = (float) value;
        }

        @Override
        public double getY() {
            if (y == java.lang.Float.NEGATIVE_INFINITY) {
                return java.lang.Double.NEGATIVE_INFINITY;
            }
            if (y == java.lang.Float.POSITIVE_INFINITY) {
                return java.lang.Double.POSITIVE_INFINITY;
            }
            return y;
        }

        @Override
        public void setY(double value) {
            y = (float) value;
        }

        @Override
        public double getZ() {
            if (z == java.lang.Float.NEGATIVE_INFINITY) {
                return java.lang.Double.NEGATIVE_INFINITY;
            }
            if (z == java.lang.Float.POSITIVE_INFINITY) {
                return java.lang.Double.POSITIVE_INFINITY;
            }
            return z;
        }

        @Override
        public void setZ(double value) {
            z = (float) value;
        }

        @Override
        public double getSizeX() {
            if (sizeX == java.lang.Float.POSITIVE_INFINITY) {
                return java.lang.Double.POSITIVE_INFINITY;
            }
            return sizeX;
        }

        @Override
        public void setSizeX(double value) {
            sizeX = (float) value;
        }

        @Override
        public double getSizeY() {
            if (sizeY == java.lang.Float.POSITIVE_INFINITY) {
                return java.lang.Double.POSITIVE_INFINITY;
            }
            return sizeY;
        }

        @Override
        public void setSizeY(double value) {
            sizeY = (float) value;
        }

        @Override
        public double getSizeZ() {
            if (sizeZ == java.lang.Float.POSITIVE_INFINITY) {
                return java.lang.Double.POSITIVE_INFINITY;
            }
            return sizeZ;
        }

        @Override
        public void setSizeZ(double value) {
            sizeZ = (float) value;
        }

        @Override
        public Rectangle3D createIntersection(Rectangle3D r) {
            final Rectangle3D.Float result = new Rectangle3D.Float();
            intersect(this, r, result);
            return result;
        }

        @Override
        public Rectangle3D createUnion(Rectangle3D r) {
            final Rectangle3D.Float result = new Rectangle3D.Float();
            union(this, r, result);
            return result;
        }

        @Override
        public Rectangle2D toRectangle2D() {
            return new Rectangle2D.Float(x, y, sizeX, sizeY);
        }
    }

    public static class Integer extends Rectangle3D {

        public int x;
        public int y;
        public int z;
        public int sizeX;
        public int sizeY;
        public int sizeZ;

        public Integer(int x, int y, int z, int sizeX, int sizeY, int sizeZ) {
            super();
            this.x = x;
            this.y = y;
            this.z = z;
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.sizeZ = sizeZ;
        }

        public Integer(Rectangle3D.Integer r) {
            this(r.x, r.y, r.z, r.sizeX, r.sizeY, r.sizeZ);
        }

        public Integer(Rectangle3D r) {
            this(r.toInteger());
        }

        public Integer() {
            this(0, 0, 0, 0, 0, 0);
        }

        @Override
        public void setRect(double x, double y, double z, double sizeX, double sizeY, double sizeZ) {
            final Rectangle3D.Integer r = new Rectangle3D.Double(x, y, z, sizeX, sizeY, sizeZ).toInteger();
            setRect(r.x, r.y, r.z, r.sizeX, r.sizeY, r.sizeZ);
        }

        public void setRect(int x, int y, int z, int sizeX, int sizeY, int sizeZ) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.sizeZ = sizeZ;
        }

        @Override
        public double getX() {
            if (x == java.lang.Integer.MIN_VALUE) {
                return java.lang.Double.NEGATIVE_INFINITY;
            }
            if (x == java.lang.Integer.MAX_VALUE) {
                return java.lang.Double.POSITIVE_INFINITY;
            }
            return x;
        }

        @Override
        public void setX(double value) {
            x = (int) value;
        }

        @Override
        public double getY() {
            if (y == java.lang.Integer.MIN_VALUE) {
                return java.lang.Double.NEGATIVE_INFINITY;
            }
            if (y == java.lang.Integer.MAX_VALUE) {
                return java.lang.Double.POSITIVE_INFINITY;
            }
            return y;
        }

        @Override
        public void setY(double value) {
            y = (int) value;
        }

        @Override
        public double getZ() {
            if (z == java.lang.Integer.MIN_VALUE) {
                return java.lang.Double.NEGATIVE_INFINITY;
            }
            if (z == java.lang.Integer.MAX_VALUE) {
                return java.lang.Double.POSITIVE_INFINITY;
            }
            return z;
        }

        @Override
        public void setZ(double value) {
            z = (int) value;
        }

        @Override
        public double getSizeX() {
            if (sizeX == java.lang.Integer.MAX_VALUE) {
                return java.lang.Double.POSITIVE_INFINITY;
            }
            return sizeX;
        }

        @Override
        public void setSizeX(double value) {
            sizeX = (int) value;
        }

        @Override
        public double getSizeY() {
            if (sizeY == java.lang.Integer.MAX_VALUE) {
                return java.lang.Double.POSITIVE_INFINITY;
            }
            return sizeY;
        }

        @Override
        public void setSizeY(double value) {
            sizeY = (int) value;
        }

        @Override
        public double getSizeZ() {
            if (sizeZ == java.lang.Integer.MAX_VALUE) {
                return java.lang.Double.POSITIVE_INFINITY;
            }
            return sizeZ;
        }

        @Override
        public void setSizeZ(double value) {
            sizeZ = (int) value;
        }

        @Override
        public Rectangle3D.Integer toInteger() {
            try {
                return (Integer) clone();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(Rectangle3D.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        @Override
        public Rectangle3D createIntersection(Rectangle3D r) {
            final Rectangle3D.Integer result = new Rectangle3D.Integer();
            intersect(this, r, result);
            return result;
        }

        @Override
        public Rectangle3D createUnion(Rectangle3D r) {
            final Rectangle3D.Integer result = new Rectangle3D.Integer();
            union(this, r, result);
            return result;
        }

        @Override
        public Rectangle2D toRectangle2D() {
            return new Rectangle(x, y, sizeX, sizeY);
        }
    }

}
