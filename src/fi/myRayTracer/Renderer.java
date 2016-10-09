package fi.myRayTracer;

import java.util.ArrayList;
import java.util.List;

public class Renderer {

    public static void main(String[] args) {
        Vector v1 = new Vector(1, 2, 5);
        Vector v2 = new Vector(5, 2, 5);
        Vector v3 = new Vector(1, 0, 5);
        Triangle triangle = new Triangle(v1, v2, v3);
        List<Triangle> triangleList = new ArrayList<>();
        triangleList.add(triangle);
        RayTracer tracer = new RayTracer(triangleList);
        Camera camera = new Camera(tracer);
        Pixel[][] pixels = camera.takePicture(10, 10, 10);
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                System.out.printf(pixels[i][j].toString());
            }
            System.out.printf("\n");
        }
    }
}
