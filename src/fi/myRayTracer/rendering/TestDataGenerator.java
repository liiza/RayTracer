package fi.myRayTracer.rendering;

import fi.myRayTracer.PointLight;
import fi.myRayTracer.geometry.Triangle;
import fi.myRayTracer.geometry.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static fi.myRayTracer.geometry.Vector.vertex;
import static fi.myRayTracer.rendering.ColoredImageRenderer.HEIGHT;
import static fi.myRayTracer.rendering.ColoredImageRenderer.WIDTH;

public class TestDataGenerator {
    static List<Triangle> getTriangles() {
        List<Triangle> triangleList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            triangleList.addAll(randomCube(WIDTH, HEIGHT));
        }
        return triangleList;
    }

    static Collection<? extends Triangle> randomCube(int width, int height) {
        Random random = new Random();
        Vector position = randomVectorWithinImageArea(width, height, random);
        return getCube(position, random.nextInt(50), random.nextInt(50), -random.nextInt(20));
    }

    static PointLight getRandomPointLight() {
        return new PointLight(randomVectorWithinImageArea(WIDTH, HEIGHT, new Random()), 1000);
    }

    private static Vector randomVectorWithinImageArea(int width, int height, Random random) {
        int i = (width/2) - random.nextInt(width);
        int j = (height/2) - random.nextInt(height);
        int k = -(ColoredImageRenderer.DISTANCE +random.nextInt(10));
        return new Vector(i, j, k);
    }

    static List<Triangle> getCube(Vector vertex, int i, int j, int k) {
        List<Triangle> triangles = new ArrayList<>();
        Vector x = new Vector(i, 0, 0);
        Vector y = new Vector(0, j, 0);
        Vector z = new Vector(0, 0, k);
        triangles.addAll(getRectangle(vertex, y, x));
        triangles.addAll(getRectangle(vertex, z, y));
        triangles.addAll(getRectangle(vertex, x, z));
        Vector vertex2 = vertex.plus(new Vector(i, j, k));
        Vector x1 = x.multiply(-1);
        Vector y1 = y.multiply(-1);
        Vector z1 = z.multiply(-1);
        triangles.addAll(getRectangle(vertex2, x1, y1));
        triangles.addAll(getRectangle(vertex2, y1, z1));
        triangles.addAll(getRectangle(vertex2, z1, x1));
        return triangles;
    }

    static List<Triangle> getRectangle(Vector v0, Vector y, Vector x) {
        List<Triangle> triangles = new ArrayList<>();
        triangles.add(new Triangle(v0, v0.plus(x).plus(y), v0.plus(y), ColoredImageRenderer.RED));
        triangles.add(new Triangle(v0, v0.plus(x), v0.plus(x).plus(y), ColoredImageRenderer.BLUE));
        return triangles;
    }
}
