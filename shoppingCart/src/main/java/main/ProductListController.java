package main;
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
import java.util.HashMap;
import java.util.ResourceBundle;

public class ProductListController implements Initializable {

    private Product currentProduct = new Product();

    private ObservableList<Product> products = FXCollections.observableArrayList();

    /**
     * We use a map to make it very easy to fetch products by id.
     */
    private HashMap<Integer, Product> productMap = new HashMap<>();

    private ArrayList<Product> productList = new ArrayList<>();

    public HashMap<Integer, Product> getProductMap() {
        return productMap;
    }

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
        // if currentProject is not null, then open the product details page
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

        // get the product list from products.xml
        productList = productService.getProductList();

        // add the products to the observable list
        products.addAll(productList);

        //write products to products.xml
        //productService.writeProductsToXML(productList);

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
}
