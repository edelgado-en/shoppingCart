package main;

import dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.UserService;

import java.io.IOException;

public class SignupController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    void backToLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            fxmlLoader.setController(new LoginController(new UserService(new UserDAO())));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signUp(ActionEvent event) {
        System.out.println("Username: " + username.getText());
        System.out.println("Password: " + password.getText());

        // if there is no user with the same username and password
        if (userService.loadUser(username.getText(), password.getText()) == null) {
            // save the user
            userService.saveUser(username.getText(), password.getText());
        }

    }

}
