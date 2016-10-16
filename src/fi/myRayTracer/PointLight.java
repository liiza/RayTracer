package fi.myRayTracer;

import fi.myRayTracer.geometry.Vector;

public class PointLight {
    public final Vector position;
    public final double intensity;

    public PointLight(Vector position, double intensity) {
        this.position = position;
        this.intensity = intensity;
    }
}
