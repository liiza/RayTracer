package fi.myRayTracer;

public class Camera {

    private Vector position;

    private Vector up;

    private Vector direction;

    private RayTracer rayTracer;

    public Pixel[][] takePicture(double distance, int height, int width) {
        Pixel[][] pixels = new Pixel[width][height];
        for (int x = 0; x < width; x++){
            for(int y = 0; y < height; y++) {
                Vector vector = (new Vector(x, y, distance)).minus(position);
                Ray ray = new Ray(position, vector.unitVector());
                pixels[x][y] = rayTracer.traceRay(ray);
            }
        }
        return pixels;
    }
}
