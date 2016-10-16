package fi.myRayTracer.geometry;

import fi.myRayTracer.rayTracer.Hit;
import fi.myRayTracer.rayTracer.Color;
import fi.myRayTracer.rayTracer.Ray;

import java.util.ArrayList;
import java.util.List;

public class Triangle {
    public final Vector v1;
    public final Vector v2;
    public final Vector v3;
    public final Color color;

    public Triangle(Vector v1, Vector e2, Vector e3, Color color) {
        this.v1 = v1;
        this.v2 = e2;
        this.v3 = e3;
        this.color = color;
    }

    public Hit intersects(Ray ray) {
        double t = distanceToPlane(ray);
        if (t < 0) {
            return new Hit(t, false, null);
        } else {
            Vector pointOnThePlane = pointOnThePlane(ray, t);
            boolean insideTriangle = isInsideTriangle(pointOnThePlane, ray.P0);
            return new Hit(t, insideTriangle, insideTriangle ? pointOnThePlane : null);
        }
    }

    double distanceToPlane(Ray ray) {
        Vector N = getPlaneNormal();
        double d = -Vector.dotProduct(N, v1);
        return -(Vector.dotProduct(ray.P0, N) + d) / (Vector.dotProduct(ray.V, N));
    }

    Vector pointOnThePlane(Ray ray, double t) {
        return ray.P0.plus(ray.V.multiply(t));
    }

    boolean isInsideTriangle(Vector point, Vector P0) {
        return isAboveSurface(v1, v2, point, P0) &&
                isAboveSurface(v2, v3, point, P0) &&
                isAboveSurface(v3, v1, point, P0);
    }

    static boolean isAboveSurface(Vector v1, Vector v2, Vector point, Vector P0) {
        Vector N = Vector.crossProduct(v2.minus(P0), v1.minus(P0)).unitVector();
        double d = -Vector.dotProduct(P0, N);
        return (Vector.dotProduct(point, N) + d) >= 0;
    }

    Vector getPlaneNormal() {
        Vector v1 = this.v1.minus(v2);
        Vector v2 = this.v1.minus(v3);
        return Vector.crossProduct(v1, v2).unitVector();
    }

    public List<Vector> vertices() {
        List<Vector> vertices = new ArrayList<>();
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        return vertices;
    }

    public Vector getNormal() {
        return Vector.crossProduct(v1.minus(v2), v2.minus(v3)).unitVector();
    }
}
