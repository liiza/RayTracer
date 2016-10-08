package fi.myRayTracer;

public class TestUtils {
    static void assertDouble(double excepted, double actual) {
        double diff = Math.abs(excepted - actual);
        if (diff > 0.001) {
            System.out.println("ERROR, excepted " + excepted + " but was " + actual);
        } else {
            System.out.println("OK");
        }
    }
}
