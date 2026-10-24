package graphics.animation;

import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/**
 * The {@code LinearGradientStorage} class provides pre-defined linear gradients for animations.
 */
public class LinearGradientStorage {

    /**
     * A pre-defined linear gradient transitioning from blue to green to yellow.
     */
    public static final LinearGradient blueGreenYellow = new LinearGradient(
            0, 0, 1, 1, true, null,
            new Stop(0, Color.BLUE),
            new Stop(0.5, Color.GREEN),
            new Stop(1, Color.YELLOW)
    );
}
