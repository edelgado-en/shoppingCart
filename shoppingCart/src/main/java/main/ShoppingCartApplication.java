package main;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import services.SceneLoaderService;

import java.io.IOException;

/**
 * Main class in charge of starting the application. The default view is the login view.
 *
 * @author Enrique Delgado
 */
public class ShoppingCartApplication extends Application {

    public static final String APP_NAME = "Shopping Cart App";

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle(APP_NAME);
        stage.setOnCloseRequest(this::onClose);
        SceneLoaderService.loadScene(stage, LoginController.build());
    }

    private void onClose(WindowEvent event) {
        new Alert(Alert.AlertType.INFORMATION,
                "Thank you for using the Shopping Cart App!")
                .showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}