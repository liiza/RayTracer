package fi.myRayTracer.rayTracing;

public class Hit {
    public boolean hit;
    public double distance;

    public Hit(double t, boolean b) {
        distance = t;
        hit = b;
    }
}
