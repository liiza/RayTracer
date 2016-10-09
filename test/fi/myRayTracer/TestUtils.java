package fi.myRayTracer;

import java.text.MessageFormat;

public class TestUtils {

    private static final String OK = "OK";
    private static MessageFormat ERROR_MSG = new MessageFormat("ERROR, excepted {0} but was {1}");
    private static final double ACCEPTED_DIFF = 0.001;

    static void assertDouble(double excepted, double actual) {
        double diff = Math.abs(excepted - actual);
        if (diff > ACCEPTED_DIFF) {
            double[] args = {excepted, actual};
            System.out.println(ERROR_MSG.format(args));
        } else {
            System.out.println("OK");
        }
    }

    static void assertTrue(boolean hit) {
        boolean excepted = true;
        if (hit != excepted) {
            Object[] args = {excepted, hit};
            System.out.println(ERROR_MSG.format(args));
        } else {
            System.out.println(OK);
        }

    }

    public static void assertFalse(boolean hit) {
        boolean excepted = false;
        if (hit != excepted) {
            Object[] args = {excepted, hit};
            System.out.println(ERROR_MSG.format(args));
        } else {
            System.out.println(OK);
        }
    }
}
