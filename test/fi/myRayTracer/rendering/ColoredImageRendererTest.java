package fi.myRayTracer.rendering;

import fi.myRayTracer.rayTracer.Color;
import fi.myRayTracer.TestUtils;

public class ColoredImageRendererTest {

    public static void main(String[] args) {
        should_return_int_value_for_rgb();
    }

    private static void should_return_int_value_for_rgb() {
        int rgbInt = Color.toRGBInt(255, 255, 255);

        TestUtils.assertInt(16777215, rgbInt);
    }
}