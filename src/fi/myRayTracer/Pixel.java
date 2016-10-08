package fi.myRayTracer;

public class Pixel {
    public double r;
    public double g;
    public double b;

    public Pixel(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public String toString() {
        return String.valueOf(r);
    }
}
