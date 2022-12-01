package main;

import dao.ProductDAO;
import dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.SceneLoaderService;
import services.UserService;

import java.io.IOException;

/**
 * LoginController class in charge of authenticating users.
 * @author Enrique Delgado
 */
public class LoginController extends AbstractController{

    private static final String TARGET_FXML = "login.fxml";

    private Stage stage;

    private UserService userService;

    /**
     * password text field binding.
     */
    @FXML
    private TextField password;

    /**
     * username text field binding.
     */
    @FXML
    private TextField username;

    /**
     * Shows the error message when user provides wrong credentials.
     */
    @FXML
    private Label errorMessage;

    /**
     * Instantiates a new Login controller with the provided userService object.
     * @param userService
     */
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles user signin. If the user provides valid credentials, it redirects the user to the
     * product list screen. Otherwise, it shows an error message.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void signin(ActionEvent event) throws IOException {
        //check if user exists
        if (userService.loadUser(username.getText(), password.getText()) != null) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingCartApplication.class.getResource("main.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }

        // Show an error message if a user with the user provides wrong credentials.
        errorMessage.setVisible(true);
        errorMessage.setText("Wrong username or password");
    }

    /**
     * Redirects the user to the signup screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void signup(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneLoaderService.loadScene(stage, SignupController.build());
    }

    @Override
    public String getTargetFxml() {
        return TARGET_FXML;
    }

    /**
     * Factory method to create a new LoginController. Abstraction to hide the implementation details.
     * This method knows how to build a LoginController.
     *
     * @return an instance of LoginController
     */
    public static LoginController build() {
        return new LoginController(new UserService(new UserDAO()));
    }

}
