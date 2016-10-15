package fi.myRayTracer.rayTracer;

import fi.myRayTracer.PointLight;
import fi.myRayTracer.geometry.Triangle;
import fi.myRayTracer.geometry.Vector;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;

public class RayTracer {

    private static final Pixel WHITE = new Pixel(255, 255, 255);

    private final List<Triangle> triangles;

    private final List<PointLight> lights;

    public RayTracer(List<Triangle> triangles, List<PointLight> lights) {
        this.triangles = triangles;
        this.lights = lights;
    }

    Pixel traceRay(Ray ray) {
        Optional<Map.Entry<Triangle, Hit>> hit = getHit(ray);
        if (hit.isPresent()) {
            double light = getLighting(hit.get().getValue().pointOnThePlane);
            return hit.get().getKey().color;
        } else {
            return WHITE;
        }
    }

    private Optional<Map.Entry<Triangle, Hit>> getHit(Ray ray) {
        return triangles.stream()
                        .collect(toMap(t -> t, t -> t.intersects(ray)))
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().hit)
                        .min((entry, entry2) -> Double.compare(entry.getValue().distance, entry2.getValue().distance));
    }

    double getLighting(Vector pointOnThePlane) {
        double intensity = 0;
        for (PointLight light : lights) {
            intensity += intensityFromLight(pointOnThePlane, light);
        }
        return intensity;
    }

    private double intensityFromLight(Vector pointOnThePlane, PointLight light) {
        Ray ray = new Ray(pointOnThePlane, light.position.minus(pointOnThePlane).unitVector());
        Optional<Map.Entry<Triangle, Hit>> hit = getHit(ray);
        if (hit.isPresent()) {
            return 0;
        } else {
            return light.getIntensity(Vector.distance(pointOnThePlane, light.position));
        }
    }
}
