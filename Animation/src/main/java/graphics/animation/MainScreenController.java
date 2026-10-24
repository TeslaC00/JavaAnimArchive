package graphics.animation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The {@code MainScreenController} class controls the main screen of the application,
 * initializing animations based on user interactions.
 */
public class MainScreenController implements Initializable {

    /**
     * The main {@code Pane} where animations will be displayed.
     */
    public Pane mainPane;

    /**
     * Background pane used for showing background image
     */
    public Pane backgroundPane;

    /**
     * An image view on the background pane used for displaying image on the upper area of the
     * background pane
     */
    public ImageView upperImageView;

    /**
     * A CheckBox used to switch background image from day to night and vide-versa
     */
    public CheckBox dayNightCheckbox;
    int index = 0;  // index used for changeImage method
    /**
     * The {@code AnimationCreator} responsible for creating and displaying animations.
     */
    private AnimationCreator animationCreator;

    /**
     * Initializes the controller, creating an instance of {@code AnimationCreator}.
     *
     * @param location  The location used to resolve relative paths for the root object.
     * @param resources A {@code ResourceBundle} that contains locale-specific objects.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addBackground();
        dayNightCheckbox.setOnAction(event -> addBackground());
        animationCreator = new AnimationCreator(mainPane);
    }

    /**
     * Adds background on the main screen.
     */
    private void addBackground() {
        if (dayNightCheckbox.isSelected()) {
            upperImageView.setImage(new Image(Constants.SCENERY_BG_PATH));
        } else {
            upperImageView.setImage(new Image(Constants.NIGHT_ROAD_BG_PATH));
        }
    }

    public void playAnimation() {
        animationCreator.playTimeline();
    }

    public void stopAnimation() {
        animationCreator.stopTimeline();
    }

    public void pauseAnimation() {
        animationCreator.pauseTimeline();
    }

    /**
     * Displays a rolling ball animation on the main screen.
     */
    public void showBallRolling() {
        animationCreator.createAnimation(AnimationType.ROLLING_BALL);
    }

    /**
     * Displays a colliding ball animation on the main screen.
     */
    public void showBallColliding() {
        animationCreator.createAnimation(AnimationType.COLLIDING_BALL);
    }

    /**
     * Displays a bouncing ball animation on the main screen.
     */
    public void showBallBouncing() {
        animationCreator.createAnimation(AnimationType.BOUNCING_BALL);
    }

    /**
     * Displays a draggable ball animation on the main screen.
     */
    public void showBallDragging() {
        animationCreator.createAnimation(AnimationType.DRAGGABLE_BALL);
    }

    /**
     * Displays a bucket filling animation on the main screen.
     */
    public void showBucketFilling() {
        animationCreator.createAnimation(AnimationType.FILLING_BUCKET);
    }

    /**
     * Displays a zooming ball animation in X Axis on the main screen.
     */
    public void showBallZoomingX() {
        animationCreator.createAnimation(AnimationType.ZOOMING_BALL_X);
    }

    /**
     * Displays a zooming ball animation in Y Axis on the main screen.
     */
    public void showBallZoomingY() {
        animationCreator.createAnimation(AnimationType.ZOOMING_BALL_Y);
    }

    /**
     * Displays a zooming ball animation in Z Axis on the main screen.
     */
    public void showBallZooming() {
        animationCreator.createAnimation(AnimationType.ZOOMING_BALL);
    }

    public void showDaySky() {
//        TODO: write code for this that shows different sky background that different clouds
    }

    public void showNightSky() {
        Timeline timeline = new Timeline(new KeyFrame(new Duration(500), event -> changeImage()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void changeImage() {
//        TODO: image path is not working
        upperImageView.setImage(new Image("/frames/frame_0.png"));
        index = (index + 1) % 9;
    }
}
