package fi.myRayTracer;

import static fi.myRayTracer.TestUtils.assertDouble;

public class ColoredImageRendererTest {

    public static void main(String[] args) {
        should_return_int_value_for_rgb();
    }

    private static void should_return_int_value_for_rgb() {
        int rgbInt = Pixel.toRGBInt(255, 255, 255);

        assertDouble(16777215, rgbInt);
    }
}