package graphics.animation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The {@code Main} class is the entry point of the JavaFX animation application.
 * It launches the application, loads the main screen, and sets up the primary stage.
 */
public class Main extends Application {

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * The overridden method from {@code Application} that initializes and starts the application.
     *
     * @param stage The primary stage of the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Animation!! :O");
        stage.setScene(scene);
        stage.show();
    }
}
