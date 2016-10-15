package fi.myRayTracer.rayTracer;

import fi.myRayTracer.PointLight;
import fi.myRayTracer.TestUtils;
import fi.myRayTracer.geometry.Triangle;
import fi.myRayTracer.geometry.Vector;

import java.util.ArrayList;
import java.util.List;

import static fi.myRayTracer.geometry.Vector.vertex;
import static fi.myRayTracer.rendering.ColoredImageRenderer.RED;

public class RayTracerTest {

    public static void main(String[] args) {
        should_return_intensity_from_the_light();
        should_return_no_intensity_when_light_is_blocked();
        should_return_no_intensity_when_light_is_behind_triangle();
    }

    private static void should_return_intensity_from_the_light() {
        List<PointLight> lights = new ArrayList<>();
        lights.add(new PointLight(new Vector(0, 0, 10), 1000));
        RayTracer tracer = new RayTracer(new ArrayList<>(), lights);

        double intensity = tracer.getLighting(new Vector(0, 0, 20));

        TestUtils.assertDouble(10.0, intensity);
    }

    private static void should_return_no_intensity_when_light_is_blocked() {
        System.out.println("Should return no intensity when light is blocked");
        List<PointLight> lights = new ArrayList<>();
        lights.add(new PointLight(new Vector(0, 0, 10), 1000));
        ArrayList<Triangle> triangles = new ArrayList<>();
        triangles.add(new Triangle(vertex(-5, 5, 15), vertex(0, -5, 15), vertex(5, 5, 15), RED));
        RayTracer tracer = new RayTracer(triangles, lights);

        double intensity = tracer.getLighting(new Vector(0, 0, 20));

        TestUtils.assertDouble(0.0, intensity);
    }

    private static void should_return_no_intensity_when_light_is_behind_triangle() {
        System.out.println("Should return no intensity when light is behind triangle");
        ArrayList<Triangle> triangles = new ArrayList<>();
        triangles.add(new Triangle(vertex(-5, 5, 15), vertex(5, 5, 15), vertex(0, -5, 15), RED));
        List<PointLight> lights = new ArrayList<>();
        lights.add(new PointLight(new Vector(0, 0, 10), 1000));
        RayTracer tracer = new RayTracer(triangles, lights);

        double intensity = tracer.getLighting(new Vector(0, 0, 15));

        TestUtils.assertDouble(0.0, intensity);
    }
}
