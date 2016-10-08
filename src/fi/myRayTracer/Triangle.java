package fi.myRayTracer;

public class Triangle {
    public Vector v1;
    public Vector v2;
    public Vector v3;

    public Triangle(Vector v1, Vector e2, Vector e3) {
        this.v1 = v1;
        this.v2 = e2;
        this.v3 = e3;
    }

    public Hit intersects(Ray ray) {
        double t = distanceToPlane(ray);
        Vector point = pointOnThePlane(ray, t);
        return new Hit(t, isInsideTriangle(point, ray));
    }

    Vector pointOnThePlane(Ray ray, double t) {
        Vector pointOnThePlane = ray.P0.plus(ray.V.multiply(t));
        return pointOnThePlane;
    }

    private double distanceToPlane(Ray ray) {
        Vector N = getPlaneNormal();
        double d = -Vector.dotProduct(N, v1);
        return -(Vector.dotProduct(ray.P0, N) + d) / (Vector.dotProduct(ray.V, N));
    }

    private boolean isInsideTriangle(Vector point, Ray ray) {
        return isAboveEdge(v1, v2, point, ray) &&
                isAboveEdge(v2, v3, point, ray) &&
                isAboveEdge(v3, v1, point, ray);
    }

    private boolean isAboveEdge(Vector v1, Vector v2, Vector point, Ray ray) {
        Vector N = Vector.crossProduct(v1.minus(ray.P0), v2.minus(ray.P0)).unitVector();
        double d = -Vector.dotProduct(ray.P0, N);
        if ((Vector.dotProduct(point, N) + d) < 0) {
            return false;
        }
        return true;
    }

    Vector getPlaneNormal() {
        Vector v1 = this.v1.minus(v2);
        Vector v2 = this.v1.minus(v3);
        return Vector.crossProduct(v1, v2).unitVector();
    }

    public Pixel getColor() {
        return new Pixel(1, 1, 1);
    }
}
