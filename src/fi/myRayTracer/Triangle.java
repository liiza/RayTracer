package fi.myRayTracer;

public class Triangle {
    public Vector e1;
    public Vector e2;
    public Vector e3;

    public Triangle(Vector e1, Vector e2, Vector e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public Hit interects(Ray ray) {
        pointOnThePlane(ray);
        return new Hit(2, false);
    }

    Vector pointOnThePlane(Ray ray) {
        Vector N = getPlaneNormal();
        double d = -Vector.dotProduct(N, e1);
        double t = -(Vector.dotProduct(ray.P0, N) + d) / (Vector.dotProduct(ray.V, N));
        Vector pointOnThePlane = ray.P0.plus(ray.V.multiply(t));
        return pointOnThePlane;
    }

    Vector getPlaneNormal() {
        Vector v1 = e1.minus(e2);
        Vector v2 = e1.minus(e3);
        return Vector.crossProduct(v1, v2).unitVector();
    }
}
