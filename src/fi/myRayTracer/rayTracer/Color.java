package fi.myRayTracer.rayTracer;

import static java.lang.String.join;
import static java.lang.String.valueOf;

public class Color {
    public static final int WHITE_INT = 16777215;
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color BLACK = new Color(0, 0, 0);

    public int r;
    public int g;
    public int b;

    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public boolean isWhite() {
        return toRGBInt(r, g, b) == WHITE_INT;
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

    public Color multiple(double n) {
        return new Color((new Double(r * n)).intValue(), (new Double(g * n)).intValue(), (new Double(b * n)).intValue());
    }

    public int toRGBInt() {
        return toRGBInt(this.r, this.g, this.b);
    }
}
