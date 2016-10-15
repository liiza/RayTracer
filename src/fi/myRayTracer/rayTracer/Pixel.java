package fi.myRayTracer.rayTracer;

import static java.lang.String.join;
import static java.lang.String.valueOf;

public class Pixel {
    public static final int WHITE = 16777215;

    public int r;
    public int g;
    public int b;

    public Pixel(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public boolean isWhite() {
        return toRGBInt(r, g, b) == WHITE;
    }

    public static int toRGBInt(int r, int g, int b) {
        int rgb = r;
        rgb = (rgb << 8) + g;
        rgb = (rgb << 8) + b;
        return rgb;
    }

    @Override
    public String toString() {
        return valueOf(join(" ", valueOf(r), valueOf(g), valueOf(b)));
    }
}
