package fi.myRayTracer.rayTracer;

import fi.myRayTracer.geometry.Vector;

public class Hit {
    public final boolean hit;
    public final double distance;
    public final Vector pointOnThePlane;

    public Hit(double t, boolean b, Vector point) {
        distance = t;
        hit = b;
        pointOnThePlane = point;
    }
}
