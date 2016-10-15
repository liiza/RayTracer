package fi.myRayTracer;

import fi.myRayTracer.geometry.Vector;

public class PointLight {
    public final Vector position;
    final int intensity;

    public PointLight(Vector position, int intensity) {
        this.position = position;
        this.intensity = intensity;
    }

    public double getIntensity(double distance) {
        return (1/Math.pow(distance, 2)) * intensity;
    }
}
