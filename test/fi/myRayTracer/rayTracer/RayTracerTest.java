package fi.myRayTracer.rayTracer;

import fi.myRayTracer.PointLight;
import fi.myRayTracer.TestUtils;
import fi.myRayTracer.geometry.Vector;

import java.util.ArrayList;
import java.util.List;

public class RayTracerTest {

    public static void main(String[] args) {
        should_return_true_when_hits_the_light();
    }

    private static void should_return_true_when_hits_the_light() {
        List<PointLight> lights = new ArrayList<>();
        lights.add(new PointLight(new Vector(0, 0, 10), 1000));
        RayTracer tracer = new RayTracer(new ArrayList<>(), lights);

        double intensity = tracer.getLighting(new Vector(0, 0, 20));

        TestUtils.assertDouble(intensity, 0.1235);
    }
}
