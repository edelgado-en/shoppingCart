package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * MainController class acts as a skeleton for the project. It holds the top bar
 * and it loads the differents view in the center part of a BorderPane.
 * It uses a static BorderPane to facilitate updating the center view from different
 * controllers.
 *
 * @author Enrique Delgado
 */
public class MainController extends AbstractController implements Initializable {

    private static final String TARGET_FXML = "main.fxml";

    @FXML
    private Label appTitle;

    @FXML
    private Button cartButton;

    @FXML
    private Label cartCounter;

    @FXML
    private BorderPane mainPane;

    public static BorderPane staticMainPane;

    public static Integer cartItemsCounter = 0;

    public Label getCartCounter() {
        return cartCounter;
    }

    public void setCartCounter(Label cartCounter) {
        this.cartCounter = cartCounter;
    }

    public BorderPane getMainPane() {
        return mainPane;
    }

    @Override
    public String getTargetFxml() {
        return TARGET_FXML;
    }

    public void setCenterView(Pane pane) {
        mainPane.setCenter(pane);
    }

    // updateCartCounter
    public void updateCartCounter(Parent root) {
        cartItemsCounter++;
        cartCounter.setText(cartItemsCounter.toString());

        // this is needed to re-render the view and show the updated values
        ShoppingCartApplication.staticStage.setScene(new Scene(root));
        ShoppingCartApplication.staticStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProductListController productListController = ProductListController.build();
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingCartApplication.class.getResource(productListController.getTargetFxml()));
        fxmlLoader.setController(productListController);

        cartCounter.setText(String.valueOf(cartItemsCounter));

        try {
            Pane centerView = fxmlLoader.load();

            staticMainPane = mainPane;
            staticMainPane.setCenter(centerView);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadCenterView(Pane centerView) throws IOException {
        FXMLLoader loader = new FXMLLoader(ShoppingCartApplication.class.getResource("main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        mainController.staticMainPane.setCenter(centerView);

        ShoppingCartApplication.staticStage.setScene(new Scene(root));
        ShoppingCartApplication.staticStage.show();
    }

    public static MainController build() {
        return new MainController();
    }
}
