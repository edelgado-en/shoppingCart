package main;

import dao.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.Product;
import services.UserService;

import java.io.IOException;
import java.util.HashMap;

/**
 * Main class in charge of starting the application.. The default view is the login view.
 * @author Enrique Delgado
 */
public class ShoppingCartApplication extends Application {

    public static final String APP_NAME = "Shopping Cart App";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(LoginController.TARGET_FXML));
        LoginController loginController = new LoginController(new UserService(new UserDAO()));
        fxmlLoader.setController(loginController);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(APP_NAME);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.setOnCloseRequest(this::onClose);

        stage.show();
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