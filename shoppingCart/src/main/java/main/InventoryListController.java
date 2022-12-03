package main;

import dao.ProductDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import models.Product;
import services.ProductService;
import services.SceneLoaderService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * InventoryListController class in charge of handling actions related to products in the inventory.
 *
 * @author Enrique Delgado
 */
public class InventoryListController extends AbstractController implements Initializable {

    /**
     * Target FXML file.
     */
    private static final String TARGET_FXML = "inventoryList.fxml";

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

    private ProductService productService;

    /**
     * InventoryListController constructor. It receives a ProductService instance.
     * @param productService
     */
    public InventoryListController(ProductService productService) {
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

    @FXML
    void addNewProduct(ActionEvent event) throws IOException {
        Pane centerView = SceneLoaderService.loadPane(AddProductController.build());
        MainController.loadCenterView(centerView);
    }

    @FXML
    void addQuantity(ActionEvent event) {

    }

    @FXML
    void subtractQuantity(ActionEvent event) {

    }

    @Override
    public String getTargetFxml() {
        return TARGET_FXML;
    }

    /**
     * Factory method to create a new InventoryListController. Abstraction to hide the implementation details.
     * This method knows how to build a InventoryListController.
     *
     * @return an instance of InventoryListController
     */
    public static InventoryListController build() {
        return new InventoryListController(new ProductService(new ProductDAO()));
    }
}
