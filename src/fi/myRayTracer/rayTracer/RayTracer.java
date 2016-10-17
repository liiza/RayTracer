package fi.myRayTracer.rayTracer;

import fi.myRayTracer.PointLight;
import fi.myRayTracer.geometry.Triangle;
import fi.myRayTracer.geometry.Vector;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.sun.javafx.util.Utils.clamp;
import static java.util.stream.Collectors.toMap;

public class RayTracer {

    private static final double AMBIENT_LIGHT = 0.25;
    private final List<Triangle> triangles;

    private final List<PointLight> lights;

    public RayTracer(List<Triangle> triangles, List<PointLight> lights) {
        this.triangles = triangles;
        this.lights = lights;
    }

    Color traceRay(Ray ray) {
        Optional<Map.Entry<Triangle, Hit>> hit = getHit(ray);
        if (hit.isPresent()) {
            double lighting = getLighting(hit.get().getKey(), hit.get().getValue().pointOnThePlane);
            return hit.get().getKey().color.multiple(lighting);
        } else {
            return Color.WHITE;
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

    double getLighting(Triangle triangle, Vector pointOnThePlane) {
        double diffusion = 0;
        for (PointLight light : lights) {
            diffusion += diffusion(triangle.getNormal(), pointOnThePlane, light);
        }
        return clamp(0, diffusion + AMBIENT_LIGHT, 1);
    }

    private double diffusion(Vector surfaceNormal, Vector pointOnThePlane, PointLight light) {
        Vector L = light.position.minus(pointOnThePlane).unitVector();
        // Lift point slightly from the surface
        Vector P = pointOnThePlane.plus(L.multiply(0.0001));
        Ray ray = new Ray(P, L);
        Optional<Map.Entry<Triangle, Hit>> hit = getHit(ray);
        if (hit.isPresent()) {
            return 0;
        } else {
            return light.intensity * (Math.max(Vector.dotProduct(L, surfaceNormal), 0));
        }
    }
}
