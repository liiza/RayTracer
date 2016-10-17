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
import java.util.List;

import static fi.myRayTracer.rayTracer.Color.BLACK;
import static fi.myRayTracer.rayTracer.Color.toRGBInt;
import static fi.myRayTracer.rendering.TestDataGenerator.getRandomPointLights;
import static fi.myRayTracer.rendering.TestDataGenerator.getTriangles;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class ColoredImageRenderer {
    static final int HEIGHT = 250;
    static final int WIDTH = 250;
    static final int DISTANCE = 15;
    private static boolean DRAW_LIGHT_POINT = true;

    public static void main(String[] args) {
        List<PointLight> randomPointLights = getRandomPointLights(1);
        Camera camera = initCamera(new RayTracer(getTriangles(), randomPointLights));
        Color[][] pixels = camera.takePicture(DISTANCE, HEIGHT, WIDTH);
        if (DRAW_LIGHT_POINT) {
            for (PointLight light: randomPointLights) {
                Vector vector = Camera.toSurfaceCoordinates(light.position, WIDTH, HEIGHT);
                pixels[new Double(vector.j).intValue()][new Double(vector.i).intValue()] = BLACK;
            }
        }
        renderImage(pixels);
    }

    private static Camera initCamera(RayTracer tracer) {
        Vector position = new Vector(0, 0, 0);
        Vector up = new Vector(0, 1, 0);
        Vector towards = new Vector(0, 0, -1);
        return new Camera(tracer, position, up, towards);
    }

    private static void renderImage(Color[][] pixels) {
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
