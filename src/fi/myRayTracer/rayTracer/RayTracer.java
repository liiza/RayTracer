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

    private Color getColor(Color color, double v) {
        return new Color((int)Math.ceil(color.r*v), (int)Math.ceil(color.g*v), (int)Math.ceil(color.b*v));
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
//        Color intensity = new Color(0, 0, 0);
        //        for (PointLight light : lights) {
        //            intensity.plus(intensityFromLight(triangle.getNormal(), pointOnThePlane, light));
        if (lights.size() > 0) {
            return intensityFromLight(triangle.getNormal(), pointOnThePlane, lights.get(0));
        }
        //        }
        return 0.0;
    }

    private double intensityFromLight(Vector surfaceNormal, Vector pointOnThePlane, PointLight light) {
        Vector L = light.position.minus(pointOnThePlane).unitVector();
        // Lift point slightly from the surface
        Vector P = pointOnThePlane.plus(L.multiply(0.0001));
        Ray ray = new Ray(P, L);
        Optional<Map.Entry<Triangle, Hit>> hit = getHit(ray);
        if (hit.isPresent()) {
            return 0;
        } else {
            return clamp(light.intensity * (Math.max(Vector.dotProduct(L, surfaceNormal), 0)), 0, 1);
        }
    }
}
