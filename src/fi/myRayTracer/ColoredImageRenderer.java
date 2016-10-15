package fi.myRayTracer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static fi.myRayTracer.Pixel.toRGBInt;
import static fi.myRayTracer.Vector.vertex;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class ColoredImageRenderer {
    public static final int HEIGHT = 250;
    public static final int WIDTH = 250;
    public static final Pixel RED = new Pixel(255, 0, 0);
    public static final Pixel GREEN = new Pixel(0, 255, 0);
    public static final Pixel BLUE = new Pixel(0, 0, 255);
    public static final Pixel LIGHT_BLUE = new Pixel(0, 0, 100);
    public static final int DISTANCE = 15;

    public static void main(String[] args) {
        RayTracer tracer = new RayTracer(getTriangles());
        Vector position = new Vector(0, 0, 0);
        Vector up = new Vector(0, 1, 0);
        Vector towards = new Vector(0, 0, -1);
        Camera camera = new Camera(tracer, position, up, towards);
        Pixel[][] pixels = camera.takePicture(DISTANCE, HEIGHT, WIDTH);
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, TYPE_INT_RGB);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                image.setRGB(j, i, toRGBInt(pixels[i][j].r, pixels[i][j].g, pixels[i][j].b));
            }
        }
        try {
            File file = new File("Output.png");
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Triangle> getTriangles() {
        List<Triangle> triangleList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            triangleList.addAll(randomCube(WIDTH, HEIGHT));
        }

//        triangleList.add(new Triangle(vertex(-10, 10, -5), vertex(10, 10, -25), vertex(10, 10, -5), RED));
//        triangleList.add(new Triangle(vertex(-10, 10, -5), vertex(-10, 10, -25), vertex(10, 10, -25), BLUE));
//
//        triangleList.add(new Triangle(vertex(-10, 10, -5), vertex(10, 10, -5), vertex(-10, 30, -5), BLUE));
//        triangleList.add(new Triangle(vertex(10, 30, -5), vertex(-10, 30, -5), vertex(10, 10, -5), RED));
//
//        triangleList.add(new Triangle(vertex(-10, 10, -25), vertex(-10, 30, -25), vertex(10, 10, -25), BLUE));
//        triangleList.add(new Triangle(vertex(10, 30, -25), vertex(10, 10, -25), vertex(-10, 30, -25), RED));
//
//        triangleList.add(new Triangle(vertex(-10, 10, -5), vertex(10, 10, -25), vertex(-10, 30, -5), BLUE));
//        triangleList.add(new Triangle(vertex(10, 10, -25), vertex(-10, 30, -5), vertex(-10, 30, -25), RED));
//
//        //        triangleList.add(new Triangle(vertex(1, 6, -5), vertex(1, 2, -5), vertex(5, 2, -5), GREEN));
        return triangleList;
    }

    private static Collection<? extends Triangle> randomCube(int width, int height) {
        Random random = new Random();
        int i = (width/2) - random.nextInt(width);
        int j = (height/2) - random.nextInt(height);
        int k = -(DISTANCE +random.nextInt(10));
        Vector position = new Vector(i, j, k);
        return getCube(position, random.nextInt(50), random.nextInt(50), -random.nextInt(20));
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
        triangles.add(new Triangle(v0, v0.plus(x).plus(y), v0.plus(y), RED));
        triangles.add(new Triangle(v0, v0.plus(x), v0.plus(x).plus(y), BLUE));
        return triangles;
    }
}
