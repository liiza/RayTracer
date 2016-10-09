package fi.myRayTracer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class ColoredImageRenderer {
    public static final int HEIGHT = 20;
    public static final int WIDTH = 20;

    public static void main(String[] args) {
        RayTracer tracer = new RayTracer(getTriangles());
        Camera camera = new Camera(tracer);
        Pixel[][] pixels = camera.takePicture(10, HEIGHT, WIDTH);
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, TYPE_INT_RGB);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                image.setRGB(j, i, toRGBInt(pixels[i][j].r, pixels[i][j].g, pixels[i][j].b));
            }
        }
        try {
            File file = new File("Output.jpg");
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static List<Triangle> getTriangles() {
        List<Triangle> triangleList = new ArrayList<>();

        Vector v1 = new Vector(3, 2, 5);
        Vector v2 = new Vector(7, 2, 5);
        Vector v3 = new Vector(3, 0, 5);
        Triangle triangle = new Triangle(v1, v2, v3);
        triangle.setColor(new Pixel(0,0,200));

        Vector v11 = new Vector(1, 6, 5);
        Vector v12 = new Vector(5, 2, 5);
        Vector v13 = new Vector(1, 2, 5);
        Triangle triangle2 = new Triangle(v11, v12, v13);
        triangle2.setColor(new Pixel(200,0,0));

        triangleList.add(triangle);
        triangleList.add(triangle2);
        return triangleList;
    }

    static int toRGBInt(int r, int g, int b) {
        int rgb = r;
        rgb = (rgb << 8) + g;
        rgb = (rgb << 8) + b;
        return rgb;
    }
}
