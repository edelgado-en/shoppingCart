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

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private UserService userService;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    void signin(ActionEvent event) {
        System.out.println("Username: " + username.getText());
        System.out.println("Password: " + password.getText());

        //check if user exists
        if (userService.loadUser(username.getText(), password.getText()) != null) {
            try {
                root = FXMLLoader.load(getClass().getResource("productList.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //show error message in the login screen. TODO: Add a label to fxml to show error messages
            System.out.println("User does not exist");
        }

    }

    @FXML
    void signup(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));
            fxmlLoader.setController(new SignupController(new UserService(new UserDAO())));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
