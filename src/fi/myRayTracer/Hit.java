package fi.myRayTracer;

public class Hit {
    boolean hit;
    double distance;

    public Hit(double t, boolean b) {
        distance = t;
        hit = b;
    }
}
