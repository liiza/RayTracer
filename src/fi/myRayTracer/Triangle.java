package fi.myRayTracer;

import static fi.myRayTracer.Vector.crossProduct;
import static fi.myRayTracer.Vector.dotProduct;

public class Triangle {
    public Vector v1;
    public Vector v2;
    public Vector v3;
    private Pixel color;

    public Triangle(Vector v1, Vector e2, Vector e3) {
        this.v1 = v1;
        this.v2 = e2;
        this.v3 = e3;
    }

    public Hit intersects(Ray ray) {
        double t = distanceToPlane(ray);
        if (t < 0) {
            return new Hit(t, false);
        } else {
            return new Hit(t, isInsideTriangle(pointOnThePlane(ray, t), ray.P0));
        }
    }

    double distanceToPlane(Ray ray) {
        Vector N = getPlaneNormal();
        double d = -dotProduct(N, v1);
        return -(dotProduct(ray.P0, N) + d) / (dotProduct(ray.V, N));
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
        Vector N = crossProduct(v2.minus(P0), v1.minus(P0)).unitVector();
        double d = -dotProduct(P0, N);
        return (dotProduct(point, N) + d) >= 0;
    }

    Vector getPlaneNormal() {
        Vector v1 = this.v1.minus(v2);
        Vector v2 = this.v1.minus(v3);
        return crossProduct(v1, v2).unitVector();
    }

    public Pixel getColor() {
        return color;
    }

    public void setColor(Pixel color) {
        this.color = color;
    }
}
