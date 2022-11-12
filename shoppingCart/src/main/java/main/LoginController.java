package main;

import dao.ProductDAO;
import dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.ProductService;
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

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    void signin(ActionEvent event) throws IOException {
        //check if user exists
        if (userService.loadUser(username.getText(), password.getText()) != null) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            SceneLoaderService.loadScene(stage, ProductListController.build());

        }

        //show error message in the login screen. TODO: Add a label to fxml to show error messages
        System.out.println("User does not exist");

    }

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
