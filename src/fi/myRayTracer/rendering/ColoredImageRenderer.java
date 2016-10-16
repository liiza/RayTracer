package fi.myRayTracer.rendering;

import fi.myRayTracer.PointLight;
import fi.myRayTracer.geometry.Vector;
import fi.myRayTracer.rayTracer.Camera;
import fi.myRayTracer.rayTracer.Color;
import fi.myRayTracer.rayTracer.RayTracer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static fi.myRayTracer.rayTracer.Color.BLACK;
import static fi.myRayTracer.rayTracer.Color.toRGBInt;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class ColoredImageRenderer {
    public static final int HEIGHT = 250;
    public static final int WIDTH = 250;
    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);
    public static final Color LIGHT_BLUE = new Color(0, 0, 100);
    public static final int DISTANCE = 15;

    public static void main(String[] args) {
        ArrayList<PointLight> lights = new ArrayList<>();
        PointLight pointLight = TestDataGenerator.getRandomPointLight();
        lights.add(pointLight);
        RayTracer tracer = new RayTracer(TestDataGenerator.getTriangles(), lights);
        Vector position = new Vector(0, 0, 0);
        Vector up = new Vector(0, 1, 0);
        Vector towards = new Vector(0, 0, -1);
        Camera camera = new Camera(tracer, position, up, towards);
        Color[][] pixels = camera.takePicture(DISTANCE, HEIGHT, WIDTH);
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, TYPE_INT_RGB);
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                image.setRGB(i, j, toRGBInt(pixels[j][i].r, pixels[j][i].g, pixels[j][i].b));
            }
        }
        try {
            File file = new File("Output.png");
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
