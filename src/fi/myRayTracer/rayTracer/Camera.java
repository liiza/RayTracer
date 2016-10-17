package fi.myRayTracer.rayTracer;

import fi.myRayTracer.geometry.Vector;

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

    public Color[][] takePicture(double distance, int height, int width) {
        Color[][] pixels = new Color[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Ray ray = new Ray(position, getRayVector(distance, to3DCoordinate(x, width), to3DCoordinate(y, height)));
                pixels[y][x] = rayTracer.traceRay(ray);
            }
        }
        return pixels;
    }

    public static int to3DCoordinate(int i, int surface) {
        return i - surface/2;
    }

    public static Vector toSurfaceCoordinates(Vector point, int width, int height) {
        return new Vector(point.i + width/2, point.j + height/2, 0);
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
