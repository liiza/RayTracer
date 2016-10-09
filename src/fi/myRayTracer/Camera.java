package fi.myRayTracer;

public class Camera {

    private Vector position = new Vector(0, 0, 0);

    private Vector up = new Vector(0, 1, 0);

    private Vector direction = new Vector(0, 0, 1);

    private RayTracer rayTracer;

    public Camera(RayTracer rayTracer) {
        this.rayTracer = rayTracer;
    }

    public Pixel[][] takePicture(double distance, int height, int width) {
        Pixel[][] pixels = new Pixel[width][height];
        for (int y = 0; y < height; y++){
            for(int x = 0; x < width; x++) {
                Ray ray = new Ray(position, getRayVector(distance, x, y));
                pixels[y][x] = rayTracer.traceRay(ray);
            }
        }
        return pixels;
    }

    private Vector getRayVector(double distance, int x, int y) {
        Vector direction = this.direction.multiply(distance);
        Vector xyOffSet = new Vector(x, y, 0);
        Vector relational = direction.plus(xyOffSet);
        return relational.plus(position);
    }
}
