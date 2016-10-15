package fi.myRayTracer;

import fi.myRayTracer.geometry.Triangle;
import fi.myRayTracer.geometry.Vector;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestUtils {

    private static final String OK = "OK";

    private static final Comparator<Vector> VECTOR_COMPARATOR = (v1, v2) -> {
        for (int i = 0; i < 3; i++) {
            int compare = Double.compare(v1.components().get(i), v2.components().get(i));
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    };

    private static final MessageFormat ERROR_MSG = new MessageFormat("ERROR, excepted {0} but was {1}");
    private static final double ACCEPTED_DIFF = 0.001;

    public static void assertTriangle(Triangle actual, Triangle excepted) {
        assertTriangle(null, actual, excepted);
    }

    public static void assertTriangle(String msg, Triangle actual, Triangle excepted) {
        if (msg !=null) {
            System.out.println(msg);
        }
        List<Vector> vertices = actual.vertices();
        List<Vector> vertices2 = excepted.vertices();
        Collections.sort(vertices, VECTOR_COMPARATOR);
        Collections.sort(vertices2, VECTOR_COMPARATOR);
        assertVector(vertices.get(0), vertices2.get(0));
        assertVector(vertices.get(1), vertices2.get(1));
        assertVector(vertices.get(2), vertices2.get(2));
    }

    public static void assertVector(Vector actual, Vector excepted) {
        assertVector(actual, excepted.i, excepted.j, excepted.k);
    }

    public static void assertVector(Vector vector, double i, double j, double k) {
        assertDouble(vector.i, i);
        assertDouble(vector.j, j);
        assertDouble(vector.k, k);
    }

    public static void assertDouble(Double excepted, Double actual) {
        double diff = Math.abs(excepted - actual);
        if (diff > ACCEPTED_DIFF) {
            Double[] args = {excepted, actual};
            System.out.println(ERROR_MSG.format(args));
        } else {
            System.out.println("OK");
        }
    }

    public static void assertInt(int excepted, int actual) {
        if (excepted != actual) {
            Object[] args = {excepted, actual};
            System.out.println(ERROR_MSG.format(args));
        } else  {
            System.out.println(OK);
        }
    }

    public static void assertTrue(boolean actual) {
        boolean excepted = true;
        if (actual != excepted) {
            Object[] args = {excepted, actual};
            System.out.println(ERROR_MSG.format(args));
        } else {
            System.out.println(OK);
        }
    }

    public static void assertFalse(boolean actual) {
        boolean excepted = false;
        if (actual != excepted) {
            Object[] args = {excepted, actual};
            System.out.println(ERROR_MSG.format(args));
        } else {
            System.out.println(OK);
        }
    }
}
