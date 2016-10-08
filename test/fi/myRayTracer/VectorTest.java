package fi.myRayTracer;

public class VectorTest {

    public static void main(String[] args) {
        System.out.println("Vector tests");
        should_return_correct_length();
        should_return_correct_unit_vector();
    }

    public static void should_return_correct_length() {
        System.out.println("Should return correct length");
        Vector vector = new Vector(3, 2, 1);

        TestUtils.assertDouble(3.742, vector.getLength());
    }

    public static void should_return_correct_unit_vector() {
        System.out.println("Should return correct unit vector");
        Vector vector = new Vector(3, 1, 2);

        Vector unitVector = vector.unitVector();

        TestUtils.assertDouble(0.802, unitVector.i);
        TestUtils.assertDouble(0.267, unitVector.j);
        TestUtils.assertDouble(0.534, unitVector.k);
    }

}