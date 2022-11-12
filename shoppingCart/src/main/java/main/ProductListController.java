package main;
import dao.ProductDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Product;
import services.ProductService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * ProductListController class in charge of handling actions related to products.
 *
 * @author Enrique Delgado
 */
public class ProductListController extends AbstractController implements Initializable {

    private static final String TARGET_FXML = "productList.fxml";

    private Product currentProduct = new Product();

    private ObservableList<Product> products = FXCollections.observableArrayList();

    private ArrayList<Product> productList = new ArrayList<>();

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableColumn<Product, Integer> quantityColumn;

    @FXML
    private Button shoppingCart;

    @FXML
    private Button addToCartButton;

    @FXML
    private Button viewDetailsButton;
    @FXML
    void addToCart(ActionEvent event) {

    }

    @FXML
    void viewDetails(ActionEvent event) {
        // if currentProject is not null, then open the product details page using SceneLoaderService
        if (currentProduct != null) {
            System.out.println("open the product details page");
        }
    }

    private ProductService productService;

    public ProductListController(ProductService productService) {
        this.productService = productService;
    }

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
        return new ProductListController(new ProductService(new ProductDAO()));
    }
}
