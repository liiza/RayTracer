package fi.myRayTracer.rayTracing;

import fi.myRayTracer.geometry.Vector;

public class Ray {

    public Vector P0;

    public Vector V;

    public Ray(Vector p0, Vector v) {
        P0 = p0;
        V = v;
    }
}
