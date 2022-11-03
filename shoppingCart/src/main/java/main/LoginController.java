package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    void signin(ActionEvent event) {
        System.out.println("Username: " + username.getText());
        System.out.println("Password: " + password.getText());

    }

}
