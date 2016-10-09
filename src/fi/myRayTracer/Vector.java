package fi.myRayTracer;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Vector {
    public double i;
    public double j;
    public double k;

    public Vector(double i, double j, double k) {
        this.i = i;
        this.j = j;
        this.k = k;
    }

    public Vector minus(Vector position) {
        double i1 = this.i - position.i;
        double j1 = this.j - position.j;
        double k1 = this.k - position.k;
        return new Vector(i1, j1, k1);
    }

    public Vector plus(Vector position) {
        double i = this.i + position.i;
        double j = this.j + position.j;
        double k = this.k + position.k;
        return new Vector(i, j, k);
    }

    public Vector multiply(double t) {
        double i = this.i * t;
        double j = this.j * t;
        double k = this.k * t;
        return new Vector(i, j, k);
    }

    public Vector unitVector() {
        double length = getLength();
        return new Vector(i/length, j/length, k/length);
    }

    double getLength() {
        return sqrt(pow(i, 2) + pow(j, 2) + pow(k, 2));
    }

    public static Vector crossProduct(Vector u, Vector v) {
        return new Vector((u.j * v.k - u.k * v.j), (u.k * v.i - u.i * v.k), (u.i * v.j - u.j * v.i));
    }

    public static double dotProduct(Vector u, Vector v) {
        return u.i * v.i + u.j * v.j + u.k * v.k;
    }

    // This is purely to make test data generation nicer.
    public static Vector vertix(int i, int j, int k) {
        return new Vector(i, j, k);
    }
}
