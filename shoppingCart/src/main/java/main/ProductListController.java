package main;

import dao.ProductDAO;
import dao.ShoppingCartDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import models.Product;
import services.ProductService;
import services.SceneLoaderService;
import services.ShoppingCartService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * ProductListController class in charge of handling actions related to products.
 *
 * @author Enrique Delgado
 */
public class ProductListController extends AbstractController implements Initializable {

    /**
     * Target FXML file.
     */
    private static final String TARGET_FXML = "productList.fxml";

    /**
     * The current product selected by the user.
     */
    private Product currentProduct = new Product();

    /**
     * An observable list of products so that we can keep track of the selected product, and also
     * have out of the box functionality for sorting by columns.
     */
    private ObservableList<Product> products = FXCollections.observableArrayList();

    /**
     * The actual product list.
     */
    private ArrayList<Product> productList = new ArrayList<>();

    /**
     * The product table binding.
     */
    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableColumn<Product, Integer> quantityColumn;

    @FXML
    private Button addToCartButton;

    @FXML
    private Button viewDetailsButton;

    private ProductService productService;

    private ShoppingCartService shoppingCartService;

    /**
     * Instantiates a new ProductListController with the provided product service and shopping cart service.
     * @param productService in charge of handling product interactions
     * @param shoppingCartService in charge of handling shopping cart interactions
     */
    public ProductListController(ProductService productService,
                                 ShoppingCartService shoppingCartService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * Initializes this controller. It sets the product table columns and loads the products.
     * The products are loaded from the productService class. A listener is added to the product table
     * so that we can keep track of which product is selected by the user.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productTable.setItems(products);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        productList = productService.getProductList();

        // add the products to the observable list
        products.addAll(productList);

        //Add a listener to the table for when a row is selected
        productTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setCurrentProduct(newValue);
        });
    }

    /**
     * Adds the current product to the shopping cart using the shoppingCartService. The cart counter in the main
     * controller gets updated.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void addToCart(ActionEvent event) throws IOException {
        if (currentProduct != null && currentProduct.getName() != null) {
            shoppingCartService.addToCart(currentProduct);

            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingCartApplication.class.getResource("main.fxml"));
            Parent root = fxmlLoader.load();

            MainController mainController = fxmlLoader.getController();
            mainController.updateCartCounter(root);
        }
    }

    /**
     * Redirect the user to the product details view if the current product is not null.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void viewDetails(ActionEvent event) throws IOException {
        if (currentProduct != null && currentProduct.getName() != null) {
            Pane centerView = SceneLoaderService.loadPane(ProductDetailsController.build(currentProduct));
            MainController.loadCenterView(centerView);
        }
    }

    /**
     * Sets the current product with the values of the provided product.
     * @param product
     */
    private void setCurrentProduct(Product product) {
        if (product != null) {
            currentProduct.setId(product.getId());
            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setQuantity(product.getQuantity());

        } else {
            currentProduct.setId(null);
            currentProduct.setName(null);
            currentProduct.setPrice(0);
            currentProduct.setQuantity(0);
        }
    }

    @Override
    public String getTargetFxml() {
        return TARGET_FXML;
    }

    /**
     * Factory method to create a new ProductListController. Abstraction to hide the implementation details.
     * This method knows how to build a ProductListController.
     *
     * @return an instance of ProductListController
     */
    public static ProductListController build() {
        return new ProductListController(new ProductService(new ProductDAO()),
                                         new ShoppingCartService(new ShoppingCartDAO()));
    }
}
