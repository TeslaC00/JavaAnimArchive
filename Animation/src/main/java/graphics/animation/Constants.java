package graphics.animation;

/**
 * Constants class containing various constants used in animations.
 */
public class Constants {
    // Frames per second for the animation
    public static final float FPS = 30;
    // Duration of each frame in milliseconds
    public static final double TIME_FRAME_MILLIS = 1000 / FPS;
    // Acceleration due to gravity
    public static final int Gravity = 1;
    // Elasticity coefficient for ball bouncing
    public static final float BALL_ELASTICITY = -0.8f;
    // day_road_bg background image path
    public static final String DAY_ROAD_BG_PATH = "/day_road_bg.png";
    // night_road_bg background image path
    public static final String NIGHT_ROAD_BG_PATH = "/night_road_bg.png";
    // scenery_bg background image path
    public static final String SCENERY_BG_PATH = "/scenery_bg.png";
    // night sky path #1
    public static final String NIGHT_SKY_PATH_1 = "/night_sky_1.png";
    // night sky path #2
    public static final String NIGHT_SKY_PATH_2 = "/night_sky_2.png";
    // night sky path #3
    public static final String NIGHT_SKY_PATH_3 = "/night_sky_3.png";
    public static final String SKY_SPRITE_SHEET_PATH = "/sky_sprite_sheet.png";

    /**
     * Private constructor to prevent instantiation of the Constants class.
     * This class should only be used to access its static constants.
     */
    private Constants() {
        // Empty private constructor to enforce non-instantiable
    }
}
