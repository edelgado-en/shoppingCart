package main;

import dao.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.UserService;

import java.io.IOException;

public class ShoppingCartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingCartApplication.class.getResource("login.fxml"));
        LoginController loginController = new LoginController(new UserService(new UserDAO()));
        fxmlLoader.setController(loginController);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Shopping Cart App");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}