package fi.myRayTracer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class ColoredImageRenderer {
    public static final int HEIGHT = 10;
    public static final int WIDTH = 10;

    public static void main(String[] args) {
        RayTracer tracer = new RayTracer(getTriangles());
        Camera camera = new Camera(tracer);
        Pixel[][] pixels = camera.takePicture(10, 10, 10);
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, TYPE_INT_RGB);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                image.setRGB(j, i, toRGBInt(pixels[i][j].r, pixels[i][j].g, pixels[i][j].b));
            }
        }
    }

    private static List<Triangle> getTriangles() {
        Vector v1 = new Vector(0, 5, 5);
        Vector v2 = new Vector(5, 5, 5);
        Vector v3 = new Vector(0, 0, 5);
        Triangle triangle = new Triangle(v1, v2, v3);
        List<Triangle> triangleList = new ArrayList<>();
        triangleList.add(triangle);
        return triangleList;
    }

    static int toRGBInt(int r, int g, int b) {
        int rgb = r;
        rgb = (rgb << 8) + g;
        rgb = (rgb << 8) + b;
        return rgb;
    }
}
