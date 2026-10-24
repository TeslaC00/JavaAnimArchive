module graphics.animation {
    requires javafx.controls;
    requires javafx.fxml;


    opens graphics.animation to javafx.fxml;
    exports graphics.animation;
}