package fi.myRayTracer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RayTracer {

    private List<Triangle> triangles = new ArrayList<>();

    public Pixel traceRay(Ray ray) {
        triangles.stream()
//                .filter(triangle -> triangle.interects(ray))
                .collect(Collectors.toList());
        return new Pixel();
    }
}
