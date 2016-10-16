package fi.myRayTracer.rendering;

import fi.myRayTracer.PointLight;
import fi.myRayTracer.geometry.Triangle;
import fi.myRayTracer.geometry.Vector;
import fi.myRayTracer.rayTracer.Camera;
import fi.myRayTracer.rayTracer.Color;
import fi.myRayTracer.rayTracer.RayTracer;

import java.util.ArrayList;
import java.util.List;

public class Renderer {

    public static void main(String[] args) {
        Vector v1 = new Vector(0, 5, 5);
        Vector v2 = new Vector(5, 5, 5);
        Vector v3 = new Vector(0, 0, 5);
        Triangle triangle = new Triangle(v1, v2, v3, new Color(225, 0, 0));
        List<Triangle> triangleList = new ArrayList<>();
        triangleList.add(triangle);
        ArrayList<PointLight> lights = new ArrayList<>();
        RayTracer tracer = new RayTracer(triangleList, lights);
        Camera camera = new Camera(tracer);
        Color[][] pixels = camera.takePicture(5, 10, 10);
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                System.out.printf(pixels[i][j].isWhite() ? "0" : "X");
            }
            System.out.printf("\n");
        }
    }
}
