package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    void signin(ActionEvent event) {
        System.out.println("Username: " + username.getText());
        System.out.println("Password: " + password.getText());
    }

    @FXML
    void signup(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("signup.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
