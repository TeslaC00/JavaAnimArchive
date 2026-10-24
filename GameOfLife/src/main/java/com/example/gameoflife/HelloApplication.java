package com.example.gameoflife;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        int Rows = 50;
        int Cols = 50;
        int cellSize = 10;
        GridPane root = new GridPane();
        Scene scene = new Scene(root,cellSize*Cols,cellSize*Rows);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        Game game = new Game(root);
        game.start();
    }

    public static void main(String[] args) {
        launch();
    }
}