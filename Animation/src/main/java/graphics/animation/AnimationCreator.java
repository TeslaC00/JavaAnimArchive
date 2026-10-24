package graphics.animation;

import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * The {@code AnimationCreator} class is responsible for creating various animations
 * and displaying them on a specified {@code Pane}.
 */
public class AnimationCreator {

    /**
     * The {@code Pane} where animations will be displayed.
     */
    private final Pane pane;
    private Timeline controllerTimeline;

    /**
     * Creates an {@code AnimationCreator} instance with the specified {@code Pane}.
     *
     * @param pane The {@code Pane} where animations will be displayed.
     */
    public AnimationCreator(Pane pane) {
        this.pane = pane;
    }

    /**
     * Creates the specified animation type and displays it on the associated {@code Pane}.
     *
     * @param animationType The type of animation to create.
     */
    public void createAnimation(AnimationType animationType) {
        switch (animationType) {
            case BOUNCING_BALL -> createBouncingBall();
            case ROLLING_BALL -> createRollingBall();
            case COLLIDING_BALL -> createCollidingBall();
            case DRAGGABLE_BALL -> createDraggableBall();
            case FILLING_BUCKET -> createFillingBucket();
            case ZOOMING_BALL_X -> createZoomingBallX();
            case ZOOMING_BALL_Y -> createZoomingBallY();
            case ZOOMING_BALL -> createZoomingBall();
            default -> handleUnimplementedAnimation();
        }
    }

    /**
     * Clears the associated {@code Pane}.
     */
    private void clearPane() {
        pane.getChildren().clear();
    }

    /**
     * Adds a {@code Node} to the associated {@code Pane}.
     *
     * @param node The {@code Node} to add.
     */
    private void addNode(Node node) {
        pane.getChildren().add(node);
    }

    /**
     * Handles the case when an animation type is not implemented.
     * Clears the {@code Pane} and displays a message indicating an unimplemented animation.
     */
    private void handleUnimplementedAnimation() {
        clearPane();
        addNode(new Label("Default case -> Unimplemented Animation :("));
    }

    public void playTimeline() {
        controllerTimeline.play();
    }

    public void pauseTimeline() {
        controllerTimeline.pause();
    }

    public void stopTimeline() {
        controllerTimeline.stop();
    }

    /**
     * Creates and displays a rolling ball animation on the associated {@code Pane}.
     */
    private void createRollingBall() {
        Ball ball = new Ball(20, LinearGradientStorage.blueGreenYellow, pane);
        ball.setRolling();
        controllerTimeline = ball.getTimeline();
        clearPane();
        addNode(ball);
    }

    /**
     * Creates and displays a colliding ball animation on the associated {@code Pane}.
     */
    private void createCollidingBall() {
        Ball ball = new Ball(20, LinearGradientStorage.blueGreenYellow, pane);
        ball.setColliding();
        controllerTimeline = ball.getTimeline();
        clearPane();
        addNode(ball);
    }

    /**
     * Creates and displays a bouncing ball animation on the associated {@code Pane}.
     */
    private void createBouncingBall() {
        Ball ball = new Ball(20, LinearGradientStorage.blueGreenYellow, pane);
        ball.setBouncing();
        controllerTimeline = ball.getTimeline();
        clearPane();
        addNode(ball);
    }

    /**
     * Creates and displays a draggable ball animation on the associated {@code Pane}.
     */
    private void createDraggableBall() {
        Ball ball = new Ball(20, LinearGradientStorage.blueGreenYellow, pane);
        DraggableMaker draggableMaker = new DraggableMaker();
        draggableMaker.makeDraggable(ball);
        controllerTimeline = ball.getTimeline();
        clearPane();
        addNode(ball);
    }

    /**
     * Creates and displays a zooming ball animation in the X Axis on the associated {@code Pane}.
     */
    private void createZoomingBallX() {
        Ball ball = new Ball(20, LinearGradientStorage.blueGreenYellow, pane);
        ball.setZoomingX();
        controllerTimeline = ball.getTimeline();
        clearPane();
        addNode(ball);
    }

    /**
     * Creates and displays a zooming ball animation in the Y Axis on the associated {@code Pane}.
     */
    private void createZoomingBallY() {
        Ball ball = new Ball(20, LinearGradientStorage.blueGreenYellow, pane);
        ball.setZoomingY();
        controllerTimeline = ball.getTimeline();
        clearPane();
        addNode(ball);
    }

    /**
     * Creates and displays a zooming ball animation in the Z Axis on the associated {@code Pane}.
     */
    private void createZoomingBall() {
        Ball ball = new Ball(20, LinearGradientStorage.blueGreenYellow, pane);
        ball.setZooming();
        controllerTimeline = ball.getTimeline();
        clearPane();
        addNode(ball);
    }

    /**
     * Creates and displays a filling bucket animation on the associated {@code Pane}.
     * TODO: Implement the code for bucket filling.
     */
    private void createFillingBucket() {
        clearPane();
        addNode(new Label("Not Yet implemented"));
    }
}
