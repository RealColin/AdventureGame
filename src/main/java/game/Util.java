package game;

public class Util {

    // because the one in PApplet is only for PApplet instances for whatever reason
    // I also can't just convert a hex color code to a decimal number externally because the color
    // function in PApplet is defined weirdly. I just copied the way they defined it into this function
    // so that my colors work.
    public static int rgbToInt(int r, int g, int b) {
        if (r > 255) {
            r = 255;
        } else if (r < 0) {
            r = 0;
        }

        if (g > 255) {
            g = 255;
        } else if (g < 0) {
            g = 0;
        }

        if (b > 255) {
            b = 255;
        } else if (b < 0) {
            b = 0;
        }

        return -16777216 | r << 16 | g << 8 | b;
    }
}
