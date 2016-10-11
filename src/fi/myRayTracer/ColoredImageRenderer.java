package fi.myRayTracer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static fi.myRayTracer.Pixel.toRGBInt;
import static fi.myRayTracer.Vector.vertex;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class ColoredImageRenderer {
    public static final int HEIGHT = 20;
    public static final int WIDTH = 20;
    public static final Pixel RED = new Pixel(255, 0, 0);
    public static final Pixel GREEN = new Pixel(0, 255, 0);
    public static final Pixel BLUE = new Pixel(0, 0, 255);

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
            File file = new File("Output.png");
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Triangle> getTriangles() {
        List<Triangle> triangleList = new ArrayList<>();
        triangleList.add(new Triangle(vertex(3, 2, 5), vertex(7, 2, 5), vertex(3, 0, 5), RED));
        triangleList.add(new Triangle(vertex(1, 6, 5), vertex(5, 2, 5), vertex(1, 2, 5), GREEN));
        triangleList.add(new Triangle(vertex(1, -6, 5), vertex(1, -2, 5), vertex(5, -2, 5), BLUE));
        return triangleList;
    }
}
