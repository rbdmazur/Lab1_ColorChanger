public class TransformFunctions {
    public static void rgbToCmyk(int[] rgb, double[] cmyk) {
        double red = (double) rgb[0] / 255;
        double green = (double) rgb[1] / 255;
        double blue = (double) rgb[2] / 255;
        double black = (1 - Math.max(red, Math.max(green, blue)));
        double cyan = ((1 - red - black)/(1 - black));
        double magenta = ((1 - green - black) / (1 - black));
        double yellow = ((1 - blue - black) / (1 - black));
        cmyk[0] = cyan * 100;
        cmyk[1] = magenta * 100;
        cmyk[2] = yellow * 100;
        cmyk[3] = black * 100;
    }
    public static void rgbToHsl(int[] rgb, double[] hsl) {
        double red = (double) rgb[0] / 255;
        double green = (double) rgb[1] / 255;
        double blue = (double) rgb[2] / 255;
        double cMax = Math.max(red, Math.max(green, blue));
        double cMin = Math.min(red, Math.min(green, blue));
        double delta = cMax - cMin;
        double hue;
        if (delta == 0) {
            hue = 0;
        }
        else if (cMax == red && green >= blue) {
            hue = 60 * (((green - blue) / delta) % 6);
        }
        else if (cMax == green && blue >= red) {
            hue = 60 * ((blue - red) / delta + 2);
        }
        else {
            hue = 60 * ((red - green) / delta + 4);
        }
        double lightness = (cMax + cMin) / 2;
        double saturation;
        if (delta == 0) {
            saturation = 0;
        }
        else {
            saturation = delta / (1 - Math.abs(2 * lightness - 1));
        }
        hsl[0] = hue;
        hsl[1] = saturation * 100;
        hsl[2] = lightness * 100;
    }
    public static void cmykToRgb(double[] cmyk, int[] rgb) {
        double black = cmyk[3] / 100;
        for (int i = 0; i < 3; i++) {
            double value = cmyk[i] / 100;
            rgb[i] = (int) (255 * (1 - value) * (1 - black));
        }
    }
    public static void hslToRgb(double[] hsl, int[] rgb) {
        double h = hsl[0];
        double s = hsl[1] / 100;
        double l = hsl[2] / 100;
        double c = (1 - Math.abs(2 * l - 1)) * s;
        double x = c * (1 - Math.abs((h / 60) % 2 - 1));
        double m = l - c / 2;
        double r;
        double g;
        double b;
        if (h >= 0 && h < 60) {
            r = c; g = x; b = 0;
        }
        else if (h >= 60 && h < 120) {
            r = x; g = c; b = 0;
        }
        else if (h >= 120 && h < 180) {
            r = 0; g = c; b = x;
        }
        else if (h >= 180 && h < 240) {
            r = 0; g = x; b = c;
        }
        else if (h >= 240 && h < 300) {
            r = x; g = 0; b = c;
        }
        else {
            r = c; g = 0; b = x;
        }

        rgb[0] = (int)((r + m) * 255);
        rgb[1] = (int)((g + m) * 255);
        rgb[2] = (int)((b + m) * 255);
    }
}
