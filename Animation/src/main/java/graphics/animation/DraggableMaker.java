package graphics.animation;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * The {@code DraggableMaker} class enables making a JavaFX {@code Node} draggable within its parent container.
 */
public class DraggableMaker {

    // Variables to store the original scene coordinates and translation values
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;

    /**
     * Makes the provided {@code Node} draggable by setting up mouse press and drag event handlers.
     *
     * @param node The {@code Node} to make draggable.
     */
    public void makeDraggable(Node node) {
        node.setOnMousePressed(this::onMousePressed);
        node.setOnMouseDragged(this::onMouseDragged);
    }

    /**
     * Handles the mouse press event to record the initial scene coordinates and translation values.
     *
     * @param event The mouse press event.
     */
    private void onMousePressed(MouseEvent event) {
        Node node = (Node) event.getSource();
        orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
        orgTranslateX = node.getTranslateX();
        orgTranslateY = node.getTranslateY();
    }

    /**
     * Handles the mouse drag event to update the translation values and move the {@code Node}.
     *
     * @param event The mouse drag event.
     */
    private void onMouseDragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        double offsetX = event.getSceneX() - orgSceneX;
        double offsetY = event.getSceneY() - orgSceneY;
        double newTranslateX = orgTranslateX + offsetX;
        double newTranslateY = orgTranslateY + offsetY;

        // Set the new translation values to move the Node
        node.setTranslateX(newTranslateX);
        node.setTranslateY(newTranslateY);
    }
}
