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

/**
 * Signup Controller class in charge of creating new users.
 *
 * @author Enrique Delgado
 */
public class SignupController {
    public static final String TARGET_FXML = "signup.fxml";

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

    /**
     * Redirects the user to the login screen.
     * @param event
     */
    @FXML
    void backToLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        fxmlLoader.setController(new LoginController(new UserService(new UserDAO())));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void signUp(ActionEvent event) {
        // if there is no user with the same username and password
        if (userService.loadUser(username.getText(), password.getText()) == null) {
            // save the user
            userService.saveUser(username.getText(), password.getText());

            //TODO: redirect the user to the product list view


        } else {
            // show error message in the signup screen. TODO: Add a label to fxml to show error messages
            System.out.println("User already exists");
        }
    }

}
