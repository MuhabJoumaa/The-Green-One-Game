package level5;

import java.util.Random;

public class PerlinNoise {

    private final int[] p = new int[512];

    public PerlinNoise() {
        int[] permutation = new int[256];
        Random random = new Random();
        for (int i = 0; i < 256; i++) {
            permutation[i] = i;
        }
        for (int i = 0; i < 256; i++) {
            int j = random.nextInt(256 - i) + i;
            int temp = permutation[i];
            permutation[i] = permutation[j];
            permutation[j] = temp;
        }
        System.arraycopy(permutation, 0, this.p, 0, 256);
        System.arraycopy(permutation, 0, this.p, 256, 256);
    }

    public double noise(double x, double y, double t) {
        int X = (int) Math.floor(x) & 255;
        int Y = (int) Math.floor(y) & 255;
        int Z = (int) Math.floor(t) & 255;
        x -= Math.floor(x);
        y -= Math.floor(y);
        t -= Math.floor(t);
        double u = fade(x);
        double v = fade(y);
        double w = fade(t);
        int A = this.p[X] + Y;
        int AA = this.p[A] + Z;
        int AB = this.p[A + 1] + Z;
        int B = this.p[X + 1] + Y;
        int BA = this.p[B] + Z;
        int BB = this.p[B + 1] + Z;
        return lerp(w, lerp(v, lerp(u, grad(this.p[AA], x, y, t),
                grad(this.p[BA], x - 1, y, t)),
                lerp(u, grad(this.p[AB], x, y - 1, t),
                        grad(this.p[BB], x - 1, y - 1, t))),
                lerp(v, lerp(u, grad(this.p[AA + 1], x, y, t - 1),
                        grad(this.p[BA + 1], x - 1, y, t - 1)),
                        lerp(u, grad(this.p[AB + 1], x, y - 1, t - 1),
                                grad(this.p[BB + 1], x - 1, y - 1, t - 1))));
    }

    private static double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private static double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    private static double grad(int hash, double x, double y, double t) {
        int h = hash & 15;
        double u = h < 8 ? x : y;
        double v = h < 4 ? y : h == 12 || h == 14 ? x : t;
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }
}
