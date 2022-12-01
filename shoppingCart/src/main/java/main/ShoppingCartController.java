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

    /**
     * The current shopping cart item selected by the user.
     */
    private ShoppingCartItem currentShoppingItem = new ShoppingCartItem();

    /**
     * An observable list of shopping car items so that we can keep track of the selected item, and also
     * have out of the box functionality for sorting by columns.
     */
    private ObservableList<ShoppingCartItem> shoppingCartItems = FXCollections.observableArrayList();

    /**
     * The actual shopping cart item list.
     */
    private List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();

    /**
     * The shopping cart table binding.
     */
    @FXML
    private TableView<ShoppingCartItem> shoppingCartItemTable;

    @FXML
    private TableColumn<ShoppingCartItem, String> nameColumn;

    @FXML
    private TableColumn<ShoppingCartItem, Double> priceColumn;

    @FXML
    private TableColumn<ShoppingCartItem, Integer> quantityColumn;

    private ShoppingCartService shoppingCartService;

    /**
     * Instantiates a new ShoppingCartController with the provided shopping cart service.
     *
     * @param shoppingCartService
     */
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public String getTargetFxml() {
        return TARGET_FXML;
    }

    /**
     * Initializes this controller. This method is automatically called after the fxml file has been
     * loaded. This method is responsible for setting up the table columns and populating the table
     * with data. A listener is also added to the table so that we can keep track of the selected
     * product.
     *
     * @param url
     * @param resourceBundle
     */
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

    /**
     * Sets the current shopping cart item.
     * @param shoppingCartItem
     */
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
