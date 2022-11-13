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

    private static final String APP_NAME = "Shopping Cart App";

    public static Stage staticStage;

    @Override
    public void start(Stage stage) throws IOException {
        staticStage = stage;
        staticStage.setTitle(APP_NAME);
        staticStage.setOnCloseRequest(this::onClose);
        SceneLoaderService.loadScene(staticStage, LoginController.build());
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