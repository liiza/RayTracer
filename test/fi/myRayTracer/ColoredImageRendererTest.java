package fi.myRayTracer;

import java.util.List;

import static fi.myRayTracer.ColoredImageRenderer.*;
import static fi.myRayTracer.TestUtils.assertInt;
import static fi.myRayTracer.TestUtils.assertTriangle;
import static fi.myRayTracer.Vector.vertex;

public class ColoredImageRendererTest {

    public static void main(String[] args) {
        should_return_int_value_for_rgb();
        should_return_square_from_vertex_and_width_and_height();
        should_get_cube_from_vertex_width_height_and_depth();
        should_get_cube_from_vertex_which_is_not_in_origon();
    }

    private static void should_return_int_value_for_rgb() {
        int rgbInt = Pixel.toRGBInt(255, 255, 255);

        TestUtils.assertInt(16777215, rgbInt);
    }


    private static void should_return_square_from_vertex_and_width_and_height() {
        System.out.println("Should return square from vertex and width and height");
        List<Triangle> square = ColoredImageRenderer.getRectangle(new Vector(0, 0, 0), new Vector(5, 0, 0), new Vector(0, 5, 0));

        Triangle triangle = square.get(0);
        assertTriangle(triangle, new Triangle(vertex(0, 0, 0), vertex(0, 5, 0), vertex(5, 5, 0), RED));
        Triangle triangle2 = square.get(1);
        assertTriangle(triangle2, new Triangle(vertex(0, 0, 0), vertex(5, 0, 0), vertex(5, 5, 0), RED));
    }

    private static void should_get_cube_from_vertex_width_height_and_depth(){
        System.out.println("Should get cube from vertex width height and depth.");
        List<Triangle> cube = getCube(vertex(0, 0, 0), 10, 10, 10);

        assertInt(cube.size(), 12);
        assertTriangle(cube.get(0), new Triangle(vertex(0, 0, 0), vertex(0, 10, 0), vertex(10, 10, 0), BLUE));
        assertTriangle(cube.get(1), new Triangle(vertex(0, 0, 0), vertex(10, 0, 0), vertex(10, 10, 0), RED));

        assertTriangle(cube.get(2), new Triangle(vertex(0, 0, 0), vertex(0, 0, 10), vertex(0, 10, 10), BLUE));
        assertTriangle(cube.get(3), new Triangle(vertex(0, 0, 0), vertex(0, 10, 0), vertex(0, 10, 10), RED));

        assertTriangle(cube.get(4), new Triangle(vertex(0, 0, 0), vertex(0, 0, 10), vertex(10, 0, 10), BLUE));
        assertTriangle(cube.get(5), new Triangle(vertex(0, 0, 0), vertex(10, 0, 0), vertex(10, 0, 10), RED));

        assertTriangle(cube.get(6), new Triangle(vertex(10, 10, 10), vertex(10, 0, 10), vertex(0, 0, 10), BLUE));
        assertTriangle(cube.get(7), new Triangle(vertex(10, 10, 10), vertex(0, 10, 10), vertex(0, 0, 10), RED));

        assertTriangle(cube.get(8), new Triangle(vertex(10, 10, 10), vertex(10, 10, 0), vertex(10, 0, 0), BLUE));
        assertTriangle(cube.get(9), new Triangle(vertex(10, 10, 10), vertex(10, 0, 10), vertex(10, 0, 0), RED));

        assertTriangle(cube.get(10), new Triangle(vertex(10, 10, 10), vertex(10, 10, 0), vertex(0, 10, 0), BLUE));
        assertTriangle(cube.get(11), new Triangle(vertex(10, 10, 10), vertex(0, 10, 10), vertex(0, 10, 0), RED));
    }

    private static void should_get_cube_from_vertex_which_is_not_in_origon() {
//        System.out.println("Should get cube from vertex which is not in origon");
//        List<Triangle> cube = getCube(vertex(-10, 10, -5), 20, 20, -20);
//
//        assertTriangle(cube.get(0), new Triangle(vertex(-10, 10, -5), vertex(10, 10, -25), vertex(10, 10, -5), RED));
//        assertTriangle(cube.get(1), new Triangle(vertex(-10, 10, -5), vertex(-10, 10, -25), vertex(10, 10, -25), BLUE));
//
//        assertTriangle(cube.get(2), new Triangle(vertex(-10, 10, -5), vertex(10, 10, -5), vertex(-10, 30, -5), BLUE));
//        assertTriangle(cube.get(3), new Triangle(vertex(10, 30, -5), vertex(-10, 30, -5), vertex(10, 10, -5), RED));
//
//        assertTriangle(cube.get(4), new Triangle(vertex(-10, 10, -25), vertex(-10, 30, -25), vertex(10, 10, -25), BLUE));
//        assertTriangle(cube.get(5), new Triangle(vertex(10, 30, -25), vertex(10, 10, -25), vertex(-10, 30, -25), RED));

//                triangleList.add(new Triangle(vertex(-10, 10, -5), vertex(10, 10, -25), vertex(-10, 30, -5), BLUE));
//                triangleList.add(new Triangle(vertex(10, 10, -25), vertex(-10, 30, -5), vertex(-10, 30, -25), RED));
    }
}