package graphics.animation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * The {@code Ball} class represents a circular object with animations.
 * It extends the JavaFX {@code Circle} class and includes various animation features
 * such as bouncing, colliding, and rolling.
 */
public class Ball extends Circle {

    // Default speeds on the X and Y axes
    private static final int DEFAULT_X_SPEED = 3;
    private static final int DEFAULT_Y_SPEED = 3;
    // Scale by how much the ball will zoom in or out
    private static final float SCALE_FACTOR = 0.99f;
    // Duration of each frame in millis
    private final Duration duration = new Duration(Constants.TIME_FRAME_MILLIS);
    // Parent component bounds
    private final double parentMaxWidth, parentMaxHeight, parentMinX, parentMinY;
    // Speed on the X and Y axes
    private double deltaY = DEFAULT_Y_SPEED, deltaX = DEFAULT_X_SPEED;
    // Timeline to run the animation
    private Timeline timeline;

    /**
     * Creates a ball object with animations.
     *
     * @param radius Radius of the ball.
     * @param fill   Color of the ball.
     * @param parent Parent component in which the ball will be placed.
     */
    public Ball(double radius, Paint fill, Pane parent) {
        super(radius, fill);

        // Setting up the initial position of the ball
        setTranslateX(0+getRadius());
        setTranslateY(300-getRadius());

        // Parent component bounds
        this.parentMinX = parent.getMinWidth();
        this.parentMinY = parent.getMinHeight();
        this.parentMaxWidth = parent.getWidth();
        this.parentMaxHeight = parent.getHeight();

        // Create a new timeline
        createTimeline();

        // Add a mouse listener for controlling the timeline
        setOnMouseClicked(mouseEvent -> toggleTimeline());
    }

    public Timeline getTimeline() {
        return timeline;
    }

    /**
     * Starts or stops the timeline based on its current state.
     */
    private void toggleTimeline() {
        if (isTimelineRunning()) stopTimeline();
        else startTimeline();
    }

    /**
     * Starts the timeline.
     */
    public void startTimeline() {
        timeline.play();
    }

    /**
     * Stops the timeline.
     */
    public void stopTimeline() {
        timeline.stop();
    }

    /**
     * Checks if the timeline is running.
     *
     * @return True if the timeline is running, false otherwise.
     */
    private boolean isTimelineRunning() {
        return timeline.getStatus() == Animation.Status.RUNNING;
    }

    /**
     * Resets the speed to their initial values.
     */
    private void resetDeltas() {
        deltaY = DEFAULT_Y_SPEED;
        deltaX = DEFAULT_X_SPEED;
    }

    /**
     * Creates a new timeline that runs indefinitely.
     */
    private void createTimeline() {
        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * Animates the bouncing of the ball.
     */
    private void bounce() {
        // Stops the timeline if necessary
        if (shouldStopBouncing()) {
            stopTimeline();
            return;
        }

        // Increase the downward speed of the ball with gravity
        deltaY += Constants.Gravity;
        setTranslateY(getTranslateY() + deltaY);

        // Reset the ball at the bottom of the screen if it falls below it
        if (checkBottomBorderCollision()) {
            setTranslateY(parentMaxHeight - getRadius());
            deltaY *= Constants.BALL_ELASTICITY;
        }
    }

    /**
     * Checks if the ball should stop bouncing.
     *
     * @return True if the ball should stop bouncing, false otherwise.
     */
    private boolean shouldStopBouncing() {
        // Note: deltaY goes to -0.40 and then the last numbers get smaller but not -0.40
        boolean isNearBottom = Math.abs(deltaY) < 0.45;
        boolean isAtBottom = getTranslateY() >= parentMaxHeight - getRadius();
        return isNearBottom && isAtBottom;
    }

    /**
     * Checks ball border collision with left, right, top, and bottom borders of the parent component and updates the
     * direction of the ball.
     */
    private void checkBorderCollision() {
        setTranslateY(getTranslateY() + deltaY);
        setTranslateX(getTranslateX() + deltaX);
        if (checkRightBorderCollision() || checkLeftBorderCollision()) deltaX *= -1;
        if (checkBottomBorderCollision() || checkTopBorderCollision()) deltaY *= -1;
    }

    /**
     * Checks the border collision with the bottom border of the parent component.
     *
     * @return True if the ball collides with the bottom border of the parent component.
     */
    private boolean checkBottomBorderCollision() {
        return getTranslateY() >= (parentMaxHeight - getRadius());
    }

    /**
     * Checks the border collision with the right border of the parent component.
     *
     * @return True if the ball collides with the right border of the parent component.
     */
    private boolean checkRightBorderCollision() {
        return getTranslateX() >= (parentMaxWidth - getRadius());
    }

    /**
     * Checks the border collision with the left border of the parent component.
     *
     * @return True if the ball collides with the left border of the parent component.
     */
    private boolean checkLeftBorderCollision() {
        return getTranslateX() <= (parentMinX + getRadius());
    }

    /**
     * Checks the border collision with the top border of the parent component.
     *
     * @return True if the ball collides with the top border of the parent component.
     */
    private boolean checkTopBorderCollision() {
        return getTranslateY() <= (parentMinY + getRadius());
    }

    /**
     * Resets the speed of the ball on the x and y axes.
     * Clears the timeline and adds a new frame for bouncing.
     */
    public void setBouncing() {
        resetDeltas();
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(new KeyFrame(duration, event -> bounce()));
    }

    /**
     * Resets the speed of the ball on the x and y axes.
     * Clears the timeline and adds a new frame for colliding with the border.
     */
    public void setColliding() {
        resetDeltas();
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(new KeyFrame(duration, event -> checkBorderCollision()));
    }

    /**
     * Resets the speed of the ball on the x and y axes.
     * Clears the timeline and adds a new frame for rolling.
     */
    public void setRolling() {
        resetDeltas();
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(new KeyFrame(duration, event -> roll()));
    }

    /**
     * Rolls the ball until the edge of the parent component and then stops.
     */
    private void roll() {
        if (getTranslateX() >= parentMaxWidth - getRadius()) {
            stopTimeline();
            return;
        }
        setTranslateX(getTranslateX() + deltaX);
        setRotate(getRotate() + 5);
    }

    /**
     * Zooms the ball out
     */
    public void setZoomingX() {
        resetDeltas();
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(new KeyFrame(duration, event -> zoomX()));
    }

    public void setZoomingY() {
        resetDeltas();
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(new KeyFrame(duration, event -> zoomY()));
    }

    public void setZooming() {
        resetDeltas();
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(new KeyFrame(duration, event -> zoom()));
    }

    private void zoomX() {
        if (getScaleX() > 0.001) {
            setScaleX(getScaleX() * SCALE_FACTOR);
        }
    }

    private void zoomY() {
        if (getScaleY() > 0.001) {
            setScaleY(getScaleY() * SCALE_FACTOR);
        }
    }
/* removal */
    private void zoom() {
        zoomX();
        zoomY();
    }
}
