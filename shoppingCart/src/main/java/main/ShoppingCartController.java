package main;

import dao.ShoppingCartDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Product;
import models.ShoppingCart;
import models.ShoppingCartItem;
import services.ShoppingCartService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * ShoppingCartController class in charge of handling actions related to shopping cart.
 *
 * @author Enrique Delgado
 */
public class ShoppingCartController extends AbstractController implements Initializable {

    private static final String TARGET_FXML = "shoppingCart.fxml";

    private ShoppingCartItem currentShoppingItem = new ShoppingCartItem();

    private ObservableList<ShoppingCartItem> shoppingCartItems = FXCollections.observableArrayList();

    private List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();

    @FXML
    private TableView<ShoppingCartItem> shoppingCartItemTable;

    @FXML
    private TableColumn<ShoppingCartItem, String> nameColumn;

    @FXML
    private TableColumn<ShoppingCartItem, Double> priceColumn;

    @FXML
    private TableColumn<ShoppingCartItem, Integer> quantityColumn;

    private ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public String getTargetFxml() {
        return TARGET_FXML;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        shoppingCartItemTable.setItems(shoppingCartItems);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart();

        //get the list of shopping cart items
        shoppingCartItemList = shoppingCart.getShoppingCartItemList();


        // add the products to the observable list
        shoppingCartItems.addAll(shoppingCartItemList);

        //Add a listener to the table for when a row is selected
        shoppingCartItemTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setCurrentShoppingItem(newValue);
        });
    }

    @FXML
    void onAddItemQuantity(ActionEvent event) {
        //TODO: implement this method
    }

    @FXML
    void onCheckout(ActionEvent event) {
        //TODO: this should take you to the checkout view where you can enter your credit card information and pay
    }

    @FXML
    void onSubtractItemQuantity(ActionEvent event) {
        //TODO: implement this method
    }

    private void setCurrentShoppingItem(ShoppingCartItem shoppingCartItem) {
        if (shoppingCartItem != null) {
            currentShoppingItem.setProduct(shoppingCartItem.getProduct());

        } else {
            currentShoppingItem.setProduct(null);
        }
    }

    /**
     * Factory method to create a new ShoppingCartController. Abstraction to hide the implementation details.
     * This method knows how to build a ShoppingCartController.
     *
     * @return an instance of ShoppingCartController
     */
    public static ShoppingCartController build() {
        return new ShoppingCartController(new ShoppingCartService(new ShoppingCartDAO()));
    }
}
