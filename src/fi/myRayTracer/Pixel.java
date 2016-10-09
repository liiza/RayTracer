package fi.myRayTracer;

import static java.lang.String.join;
import static java.lang.String.valueOf;

public class Pixel {
    public int r;
    public int g;
    public int b;

    public Pixel(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public String toString() {
        return valueOf(join(" ", valueOf(r), valueOf(g), valueOf(b)));
    }
}
