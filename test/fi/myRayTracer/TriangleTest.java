package fi.myRayTracer;

import static fi.myRayTracer.TestUtils.assertDouble;

public class TriangleTest {

    public static void main(String[] args) {
        System.out.println("Triangle tests");
        should_return_correct_normal_vector();
    }

    private static void should_return_correct_normal_vector() {
        Vector e1 = new Vector(1, -2, 0);
        Vector e2 = new Vector(3, 1, 4);
        Vector e3 = new Vector(0, -1, 2);
        Triangle triangle = new Triangle(e1, e2, e3);

        Vector planeNormal = triangle.getPlaneNormal();
        assertDouble(2/Math.sqrt(93), planeNormal.i);
        assertDouble(-8/Math.sqrt(93), planeNormal.j);
        assertDouble(5/Math.sqrt(93), planeNormal.k);
    }
}