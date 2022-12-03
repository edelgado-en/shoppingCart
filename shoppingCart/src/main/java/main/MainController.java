package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.SceneLoaderService;

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

    /**
     * this value shows the current number of items in the shopping cart. IT is static so that the value
     * is correctly updated when the user adds or removes items from the cart between different instances of the
     * controller
     */
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

    /**
     * Redirects the user to the login screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneLoaderService.loadScene(stage, LoginController.build());
    }

     /**
     * Increases the cart counter label by 1.
     *
     * @param root
     */
    public void increaseCartCounter(Parent root) {
        cartItemsCounter++;
        cartCounter.setText(cartItemsCounter.toString());

        // this is needed to re-render the view and show the updated values
        ShoppingCartApplication.staticStage.setScene(new Scene(root));
        ShoppingCartApplication.staticStage.show();
    }

    /**
     * Decreases the cart counter label by 1.
     *
     * @param root
     */
    public void decreaseCartCounter(Parent root) {
        cartItemsCounter--;
        cartCounter.setText(cartItemsCounter.toString());

        // this is needed to re-render the view and show the updated values
        ShoppingCartApplication.staticStage.setScene(new Scene(root));
        ShoppingCartApplication.staticStage.show();
    }

    /**
     * When the MainController is initialized, the product list view is loaded into the center view of the pane.
     *
     * @param url
     * @param resourceBundle
     */
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

    /**
     * OnViewCart will redirect the user to the shopping cart view.
     *
     * @param event
     */
    @FXML
    void onViewCart(ActionEvent event) {
        ShoppingCartController shoppingCartControllerController = ShoppingCartController.build();
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingCartApplication.class.getResource(shoppingCartControllerController.getTargetFxml()));
        fxmlLoader.setController(shoppingCartControllerController);

        try {
            Pane centerView = fxmlLoader.load();

            staticMainPane = mainPane;
            staticMainPane.setCenter(centerView);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method will load the provided pane object and set it as center view of the main pane.
     *
     * @param centerView
     * @throws IOException
     */
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
