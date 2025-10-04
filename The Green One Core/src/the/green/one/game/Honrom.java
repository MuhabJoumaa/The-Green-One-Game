package the.green.one.game;

public class Honrom {

    private int x, y, dx, dy;
    private int circleRadius;
    private final int X_CENTER, Y_CENTER, WIDTH, HEIGHT;

    public Honrom(final int circleRadius, final int X_CENTER, final int Y_CENTER, final int WIDTH, final int HEIGHT) {
        this.setCircleRadius(circleRadius);
        this.X_CENTER = X_CENTER;
        this.Y_CENTER = Y_CENTER;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        double r = Math.random() * this.circleRadius,
                theta = Math.random() * 2 * Math.PI;
        this.x = (int) (r * Math.cos(theta)) + X_CENTER - this.WIDTH / 2;
        this.y = (int) (r * Math.sin(theta)) + Y_CENTER - this.HEIGHT / 2;
        this.dx = (int) (Math.random() * 50 - 20);
        this.dy = (int) (Math.random() * 50 - 20);
    }

    public void move() {
        this.x += this.dx;
        this.y += this.dy;
        double relativeX = (this.x + this.WIDTH / 2) - X_CENTER, relativeY = (this.y + this.HEIGHT / 2) - Y_CENTER;
        double distance = (float) Math.sqrt(relativeX * relativeX + relativeY * relativeY);
        if (distance > this.circleRadius) {
            double scale = this.circleRadius / distance;
            this.x = (int) (relativeX * scale + X_CENTER) - this.WIDTH / 2;
            this.y = (int) (relativeY * scale + Y_CENTER) - this.HEIGHT / 2;
            double nx = relativeX / distance, ny = relativeY / distance, dot = this.dx * nx + this.dy * ny;
            this.dx = (int) (this.dx - 2 * dot * nx);
            this.dy = (int) (this.dy - 2 * dot * ny);
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public int getWidth() {
        return this.WIDTH;
    }

    public int getHeight() {
        return this.HEIGHT;
    }

    public final void setCircleRadius(final int circleRadius) {
        this.circleRadius = circleRadius;
    }
}
