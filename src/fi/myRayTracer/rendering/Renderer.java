package fi.myRayTracer.rendering;

import fi.myRayTracer.geometry.Triangle;
import fi.myRayTracer.geometry.Vector;
import fi.myRayTracer.rayTracing.Camera;
import fi.myRayTracer.rayTracing.Pixel;
import fi.myRayTracer.rayTracing.RayTracer;

import java.util.ArrayList;
import java.util.List;

public class Renderer {

    public static void main(String[] args) {
        Vector v1 = new Vector(0, 5, 5);
        Vector v2 = new Vector(5, 5, 5);
        Vector v3 = new Vector(0, 0, 5);
        Triangle triangle = new Triangle(v1, v2, v3, new Pixel(225, 0, 0));
        List<Triangle> triangleList = new ArrayList<>();
        triangleList.add(triangle);
        RayTracer tracer = new RayTracer(triangleList);
        Camera camera = new Camera(tracer);
        Pixel[][] pixels = camera.takePicture(5, 10, 10);
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                System.out.printf(pixels[i][j].isWhite() ? "0" : "X");
            }
            System.out.printf("\n");
        }
    }
}