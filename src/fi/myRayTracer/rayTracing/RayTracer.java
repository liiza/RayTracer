package fi.myRayTracer.rayTracing;

import fi.myRayTracer.geometry.Triangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;

public class RayTracer {

    private static final Pixel WHITE = new Pixel(255, 255, 255);

    private List<Triangle> triangles = new ArrayList<>();

    public RayTracer(List<Triangle> triangles) {
        this.triangles = triangles;
    }

    public Pixel traceRay(Ray ray) {
        Optional<Map.Entry<Triangle, Hit>> min = triangles.stream()
                                                          .collect(toMap(t -> t, t -> t.intersects(ray)))
                                                          .entrySet().stream()
                                                          .filter(entry -> entry.getValue().hit)
                                                          .min((entry, entry2) -> Double.compare(entry.getValue().distance, entry2.getValue().distance));
        if (min.isPresent()) {
            return min.get().getKey().getColor();
        } else {
            return WHITE;
        }
    }
}
