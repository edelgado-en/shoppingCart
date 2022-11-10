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
import services.UserService;

import java.io.IOException;

/**
 * LoginController class in charge of authenticating users.
 * @author Enrique Delgado
 */
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
    void signin(ActionEvent event) throws IOException {
        //check if user exists
        if (userService.loadUser(username.getText(), password.getText()) != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productList.fxml"));
            fxmlLoader.setController(new ProductListController(new ProductService(new ProductDAO())));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();

        } else {
            //show error message in the login screen. TODO: Add a label to fxml to show error messages
            System.out.println("User does not exist");
        }
    }

    @FXML
    void signup(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));
        fxmlLoader.setController(new SignupController(new UserService(new UserDAO())));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
