package main;

import dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    /**
     * Target FXML file for this controller.
     */
    private static final String TARGET_FXML = "signup.fxml";

    private UserService userService;

    /**
     * SignupController constructor. It receives a UserService instance.
     * @param userService
     */
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    /**
     * username text field binding.
     */
    @FXML
    private TextField username;

    /**
     * password text field binding.
     */
    @FXML
    private TextField password;

    /**
     * Shows the error message when user already exists.
     */
    @FXML
    private Label errorMessage;

    /**
     * Redirects the user to the login screen.
     * @param event
     */
    @FXML
    void backToLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneLoaderService.loadScene(stage, LoginController.build());
    }

    /**
     * Creates a new user if the username is not taken. Upon creation, the user will be redirected
     * to the product list view.
     *
     * @param event the button action event
     * @throws IOException
     */
    @FXML
    void signUp(ActionEvent event) throws IOException {
        if (userService.loadUser(username.getText(), password.getText()) == null) {

            userService.saveUser(username.getText(), password.getText());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            SceneLoaderService.loadScene(stage, ProductListController.build());
        }

        // Show an error message if a user with the same username already exists.
        errorMessage.setVisible(true);
        errorMessage.setText("User already exists");
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
