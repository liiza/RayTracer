package fi.myRayTracer;

public class Camera {

    private final Vector position;

    private final Vector up;

    private final Vector towards;

    private final RayTracer rayTracer;

    public Camera(RayTracer rayTracer, Vector position, Vector up, Vector towards) {
        this.rayTracer = rayTracer;
        this.position = position;
        this.up = up;
        this.towards = towards;
    }

    public Camera(RayTracer rayTracer) {
        this(rayTracer, new Vector(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
    }

    public Pixel[][] takePicture(double distance, int height, int width) {
        Pixel[][] pixels = new Pixel[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Ray ray = new Ray(position, getRayVector(distance, toSurfaceCoordinate(x, width), toSurfaceCoordinate(y, height)));
                pixels[y][x] = rayTracer.traceRay(ray);
            }
        }
        return pixels;
    }

    private int toSurfaceCoordinate(int i, int surface) {
        return i - surface/2;
    }

    private Vector getRayVector(double distance, int x, int y) {
        Vector relative = getCameraRelativeVector(distance, x, y);
        Vector global = relative.plus(position);
        return global;
    }

    private Vector getCameraRelativeVector(double distance, int x, int y) {
        Vector direction = this.towards.multiply(distance);
        Vector xyOffSet = getRight().multiply(x).plus(up.multiply(y));
        return direction.plus(xyOffSet);
    }

    private Vector getRight() {
        return Vector.crossProduct(towards, up);
    }
}
