package fi.myRayTracer;

import static fi.myRayTracer.TestUtils.assertDouble;

public class VectorTest {

    public static void main(String[] args) {
        System.out.println("Vector tests");
        should_return_correct_length();
        should_return_correct_unit_vector();
        should_minus_plus_vector_to_other_vector();
        should_minus_vector_from_other_vector();
        should_multiple_vector_by_scalar();
        should_return_cross_product_of_two_vectors();
        should_return_dot_product_of_two_vectors();
    }

    public static void should_return_correct_length() {
        System.out.println("Should return correct length");
        Vector vector = new Vector(3, 2, 1);

        assertDouble(3.742, vector.getLength());
    }

    public static void should_return_correct_unit_vector() {
        System.out.println("Should return correct unit vector");
        Vector vector = new Vector(3, 1, 2);

        Vector unitVector = vector.unitVector();

        assertDouble(0.802, unitVector.i);
        assertDouble(0.267, unitVector.j);
        assertDouble(0.534, unitVector.k);
    }

    public static void should_minus_vector_from_other_vector() {
        System.out.println("Should minus vector from other vector");
        Vector vector = new Vector(3, 1, 2);

        Vector minus = vector.minus(new Vector(1, 1, 1));

        assertDouble(2, minus.i);
        assertDouble(0, minus.j);
        assertDouble(1, minus.k);
    }

    public static void should_minus_plus_vector_to_other_vector() {
        System.out.println("Should plus vector to other vector");
        Vector vector = new Vector(-1, 1, 2);

        Vector plus = vector.plus(new Vector(1, 1, 1));

        assertDouble(0, plus.i);
        assertDouble(2, plus.j);
        assertDouble(3, plus.k);
    }

    public static void should_multiple_vector_by_scalar() {
        System.out.println("Should multiple vector by scalar");
        Vector vector = new Vector(-1, 1, 2);

        Vector plus = vector.multiply(2);

        assertDouble(-2, plus.i);
        assertDouble(2, plus.j);
        assertDouble(4, plus.k);
    }

    public static void should_return_cross_product_of_two_vectors() {
        System.out.println("Should return cross product of two vectors.");
        Vector vector = new Vector(2, -5, 7);
        Vector vector2 = new Vector(6, -1, -5);

        Vector crossProduct = Vector.crossProduct(vector, vector2);

        assertDouble(32, crossProduct.i);
        assertDouble(52, crossProduct.j);
        assertDouble(28, crossProduct.k);
    }

    public static void should_return_dot_product_of_two_vectors(){
        System.out.println("Should return dot product of two vectors.");
        Vector vector = new Vector(2, -5, 7);
        Vector vector2 = new Vector(6, -1, -5);

        double dotProduct = Vector.dotProduct(vector, vector2);

        assertDouble(-18, dotProduct);
    }

}