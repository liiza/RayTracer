package fi.myRayTracer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RayTracer {

    private static final Pixel BACKGROUND_COLOR = new Pixel(0, 0, 0);

    private List<Triangle> triangles = new ArrayList<>();

    public RayTracer(List<Triangle> triangles) {
        this.triangles = triangles;
    }

    public Pixel traceRay(Ray ray) {
        Optional<Map.Entry<Triangle, Hit>> min = triangles.stream()
                .collect(Collectors.toMap(t -> t, t -> t.intersects(ray)))
                .entrySet().stream()
                .filter(entry -> entry.getValue().hit)
                .min((entry, entry2) -> Double.compare(entry.getValue().distance, entry2.getValue().distance));
        if (min.isPresent()) {
            return min.get().getKey().getColor();
        } else {
            return BACKGROUND_COLOR;
        }
    }
}
