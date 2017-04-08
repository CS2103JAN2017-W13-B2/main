//@@author A0144885R
package seedu.address.ui;

import java.util.Random;


/**
 * Represents a color in RGB format with some utility methods
 * for adjusting color code.
 */
public class UiColor {

    public static final String HEXCODE_FORMAT = "#%s%s%s";
    public static final int HEXCODE_NUM_CHAR = 2;

    public static final int COLOR_RANGE = 256;

    public static final UiColor WHITE = new UiColor(COLOR_RANGE - 1, COLOR_RANGE - 1, COLOR_RANGE - 1);
    public static final UiColor BLACK = new UiColor(0, 0, 0);

    // Constants for darkness calculation
    public static final double DARKNESS_OF_RED = 0.299;
    public static final double DARKNESS_OF_GREEN = 0.587;
    public static final double DARKNESS_OF_BLUE = 0.114;
    public static final double DARKNESS_THRESHOLD = 0.3;

    // A flat ui colors palette
    public static final UiColor[] palette = {
        new UiColor(230, 126, 34), new UiColor(246, 36, 89), new UiColor(155, 89, 182),
        new UiColor(46, 204, 113), new UiColor(26, 188, 156), new UiColor(231, 76, 60),
        new UiColor(68, 108, 179), new UiColor(219, 10, 91), new UiColor(174, 168, 211),
        new UiColor(52, 152, 219), new UiColor(241, 196, 15), new UiColor(102, 51, 153),
        new UiColor(210, 82, 127), new UiColor(51, 110, 123), new UiColor(78, 205, 196),
        new UiColor(52, 73, 94), new UiColor(30, 130, 76)
    };
    public static int currentColorIndex = 0;

    private int red;
    private int green;
    private int blue;

    public UiColor() {
        this.red = this.green = this.blue = 0;
    }

    public UiColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Returns a color from the palette or a random one if the list is exhausted.
     **/
    public static UiColor getRandomColor() {
        if (currentColorIndex >= palette.length) {
            Random random = new Random();
            UiColor color = new UiColor(random.nextInt(COLOR_RANGE),
                    random.nextInt(COLOR_RANGE),
                    random.nextInt(COLOR_RANGE));
            return (color.isDarkColor() ? color : color.mixWith(BLACK));
        } else {
            return palette[currentColorIndex++];
        }
    }

    public UiColor mixWith(UiColor other) {
        return new UiColor((this.red + other.red) / 2,
                            (this.green + other.green) / 2,
                            (this.blue + other.blue) / 2);
    }

    public boolean isDarkColor() {
        double darkness = 1 - (DARKNESS_OF_RED * this.red
                                + DARKNESS_OF_GREEN * this.green
                                + DARKNESS_OF_BLUE * this.blue) / (COLOR_RANGE - 1);
        return darkness > DARKNESS_THRESHOLD;
    }

    public String getHexCode(int value) {
        String hex = Integer.toHexString(value);
        // Add zero until enough
        while (hex.length() < HEXCODE_NUM_CHAR) {
            hex = '0' + hex;
        }
        return hex;
    }

    @Override
    public String toString() {
        return String.format(HEXCODE_FORMAT,
                getHexCode(red),
                getHexCode(green),
                getHexCode(blue));
    }
}
