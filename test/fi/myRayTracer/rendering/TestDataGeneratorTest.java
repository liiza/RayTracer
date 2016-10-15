package fi.myRayTracer.rendering;

import fi.myRayTracer.geometry.Triangle;
import fi.myRayTracer.geometry.Vector;

import java.util.List;

import static fi.myRayTracer.rendering.ColoredImageRenderer.BLUE;
import static fi.myRayTracer.rendering.ColoredImageRenderer.RED;
import static fi.myRayTracer.TestUtils.assertInt;
import static fi.myRayTracer.TestUtils.assertTriangle;
import static fi.myRayTracer.geometry.Vector.vertex;

public class TestDataGeneratorTest {

    public static void main(String[] args) {
        should_return_square_from_vertex_and_width_and_height();
        should_get_cube_from_vertex_width_height_and_depth();
    }

    private static void should_return_square_from_vertex_and_width_and_height() {
        System.out.println("Should return square from vertex and width and height");
        List<Triangle> square = TestDataGenerator.getRectangle(new Vector(0, 0, 0), new Vector(5, 0, 0), new Vector(0, 5, 0));

        Triangle triangle1 = square.get(0);
        assertTriangle(triangle1, new Triangle(vertex(0, 0, 0), vertex(5, 0, 0), vertex(5, 5, 0), RED));
        Triangle triangle2 = square.get(1);
        assertTriangle(triangle2, new Triangle(vertex(0, 0, 0), vertex(0, 5, 0), vertex(5, 5, 0), RED));
    }

    private static void should_get_cube_from_vertex_width_height_and_depth(){
        System.out.println("Should get cube from vertex width height and depth.");
        List<Triangle> cube = TestDataGenerator.getCube(vertex(0, 0, 0), 10, 10, 10);

        assertInt(cube.size(), 12);

        assertTriangle("Triangle 0", cube.get(0), new Triangle(vertex(0, 0, 0), vertex(0, 10, 0), vertex(10, 10, 0), RED));
        assertTriangle("Triangle 1", cube.get(1), new Triangle(vertex(0, 0, 0), vertex(10, 0, 0), vertex(10, 10, 0), BLUE));

        assertTriangle("Triangle 2", cube.get(2), new Triangle(vertex(0, 0, 0), vertex(0, 0, 10), vertex(0, 10, 10), RED));
        assertTriangle("Triangle 3", cube.get(3), new Triangle(vertex(0, 0, 0), vertex(0, 10, 0), vertex(0, 10, 10), BLUE));

        assertTriangle("Triangle 4", cube.get(4), new Triangle(vertex(0, 0, 0), vertex(10, 0, 0), vertex(10, 0, 10), RED));
        assertTriangle("Triangle 5", cube.get(5), new Triangle(vertex(0, 0, 0), vertex(0, 0, 10), vertex(10, 0, 10), BLUE));

        assertTriangle("Triangle 6", cube.get(6), new Triangle(vertex(10, 10, 10), vertex(0, 10, 10), vertex(0, 0, 10), RED));
        assertTriangle("Triangle 7", cube.get(7), new Triangle(vertex(10, 10, 10), vertex(10, 0, 10), vertex(0, 0, 10), BLUE));

        assertTriangle("Triangle 8", cube.get(8), new Triangle(vertex(10, 10, 10), vertex(10, 0, 10), vertex(10, 0, 0), RED));
        assertTriangle("Triangle 9", cube.get(9), new Triangle(vertex(10, 10, 10), vertex(10, 10, 0), vertex(10, 0, 0), BLUE));

        assertTriangle("Triangle 10", cube.get(10), new Triangle(vertex(10, 10, 10), vertex(10, 10, 0), vertex(0, 10, 0), RED));
        assertTriangle("Triangle 11", cube.get(11), new Triangle(vertex(10, 10, 10), vertex(0, 10, 10), vertex(0, 10, 0), BLUE));
    }

}
