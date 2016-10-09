package fi.myRayTracer;

import static fi.myRayTracer.TestUtils.assertDouble;
import static fi.myRayTracer.TestUtils.assertTrue;
import static fi.myRayTracer.Vector.vertix;

public class TriangleTest {

    public static void main(String[] args) {
        System.out.println("Triangle tests");
        should_return_correct_normal_vector();
        should_return_correct_distance_to_plane();
        should_return_correct_point_on_the_plane();
        should_return_true_if_point_inside_triangle();
        should_return_false_if_point_is_not_inside_triangle();
        should_return_true_if_above_the_surface();
        should_return_false_if_not_above_the_surface();
        should_return_true_for_ray_that_intersects_triangle();
        should_return_false_for_ray_that_does_not_intersect_triangle();
    }

    private static void should_return_correct_normal_vector() {
        System.out.println("Should return correct normal vector");
        Triangle t = new Triangle(vertix(1, -2, 0), vertix(3, 1, 4), vertix(0, -1, 2));

        Vector planeNormal = t.getPlaneNormal();

        assertDouble(2/Math.sqrt(93), planeNormal.i);
        assertDouble(-8/Math.sqrt(93), planeNormal.j);
        assertDouble(5/Math.sqrt(93), planeNormal.k);
    }

    private static void should_return_correct_distance_to_plane() {
        System.out.println("Should return correct distance to plane");
        Triangle t = new Triangle(vertix(-5, 0, 1), vertix(0, 5, 1), vertix(5, 0, 1));
        Vector r = new Vector(0, 0, 1);
        Vector P0 = new Vector(0, 1, -1);

        double distanceToPlane = t.distanceToPlane(new Ray(P0, r));

        assertDouble(2, distanceToPlane);
    }

    private static void should_return_correct_point_on_the_plane(){
        System.out.println("Should return correct point on the plane");
        Triangle t = new Triangle(vertix(-5, 0, 1), vertix(0, 5, 1), vertix(5, 0, 1));
        Vector r = new Vector(0, 0, 1);
        Vector P0 = new Vector(0, 1, -1);
        double d = 2.0;

        Vector point = t.pointOnThePlane(new Ray(P0, r), 2.0);

        assertDouble(0, point.i);
        assertDouble(1, point.j);
        assertDouble(1, point.k);
    }

    private static void should_return_true_if_point_inside_triangle() {
        System.out.println("Should return true if point inside triangle");
        Triangle triangle = new Triangle(vertix(-5, 0, 1), vertix(0, 5, 1), vertix(5, 0, 1));
        Vector P = new Vector(0, 1, 1);
        Vector P0 = new Vector(0, 1, -1);

        TestUtils.assertTrue(triangle.isInsideTriangle(P, P0));
    }

    private static void should_return_false_if_point_is_not_inside_triangle() {
        System.out.println("Should return false if point is not inside triangle");
        Triangle triangle = new Triangle(vertix(-5, 0, 1), vertix(0, 5, 1), vertix(5, 0, 1));
        Vector P = new Vector(0, 10, 1);
        Vector P0 = new Vector(0, 1, -1);

        TestUtils.assertFalse(triangle.isInsideTriangle(P, P0));
    }

    private static void should_return_true_if_above_the_surface(){
        System.out.println("Should return true if above the surface");
        Vector v1 = vertix(-5, 0, 1);
        Vector v2 = vertix(0, 5, 1);
        Vector P = new Vector(0, 1, 1);
        Vector P0 = new Vector(0, 1, -1);

        assertTrue(Triangle.isAboveSurface(v1, v2, P, P0));
    }

    private static void should_return_false_if_not_above_the_surface(){
        System.out.println("Should return false if not above the surface");
        Vector v1 = vertix(-5, 0, 1);
        Vector v2 = vertix(0, 5, 1);
        Vector P = new Vector(0, -10, 1);
        Vector P0 = new Vector(0, 1, -1);

        assertTrue(Triangle.isAboveSurface(v1, v2, P, P0));
    }

    private static void should_return_true_for_ray_that_intersects_triangle() {
        System.out.println("Should return true for ray that intersects triangle");
        Triangle triangle = new Triangle(vertix(-5, 0, 1), vertix(0, 5, 1), vertix(5, 0, 1));
        Vector r =  new Vector(0, 0, 1);
        Vector P0 = new Vector(0, 1, 0);
        Ray ray = new Ray(P0, r);

        Hit hit = triangle.intersects(ray);

        TestUtils.assertTrue(hit.hit);
    }

    private static void should_return_false_for_ray_that_does_not_intersect_triangle() {
        System.out.println("Should return false for ray that does not intersect triangle");
        Triangle t = new Triangle(vertix(-5, 0, 1), vertix(0, 5, 1), vertix(5, 0, 1));
        Vector r =  new Vector(0, 0, 1);
        Vector P0 = new Vector(0, -1, 100);
        Ray ray = new Ray(P0, r);

        Hit hit = t.intersects(ray);

        TestUtils.assertFalse(hit.hit);
    }

}