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
import services.SceneLoaderService;
import services.UserService;

import java.io.IOException;

/**
 * Signup Controller class in charge of creating new users.
 *
 * @author Enrique Delgado
 */
public class SignupController extends AbstractController {
    public static final String TARGET_FXML = "signup.fxml";

    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    /**
     * Redirects the user to the login screen.
     * @param event
     */
    @FXML
    void backToLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        SceneLoaderService.loadScene(stage, LoginController.build());
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        if (userService.loadUser(username.getText(), password.getText()) == null) {

            userService.saveUser(username.getText(), password.getText());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            SceneLoaderService.loadScene(stage, ProductListController.build());
        }

        // show error message in the signup screen. TODO: Add a label to fxml to show error messages
        System.out.println("User already exists");

    }

    @Override
    public String getTargetFxml() {
        return TARGET_FXML;
    }

    /**
     * Factory method to create a new SignupController. Abstraction to hide the implementation details.
     * This method knows how to build a SignupController.
     *
     * @return an instance of SignupController
     */
    public static SignupController build() {
        return new SignupController(new UserService(new UserDAO()));
    }
}
