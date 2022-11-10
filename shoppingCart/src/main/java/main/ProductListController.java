package main;
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
import java.util.ResourceBundle;

public class ProductListController implements Initializable {

    @FXML
    private TableView<Product> productTable;

    @FXML
    private Button shoppingCart;

    private ProductService productService;

    public ProductListController(ProductService productService) {
        this.productService = productService;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn nameColumn = new TableColumn<Product, String>("Name");
        TableColumn priceColumn = new TableColumn<Product, Double>("Price");
        TableColumn quantityColumn = new TableColumn<Product, Integer>("Quantity");
        TableColumn actionsColumn = new TableColumn("Actions");

        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));

        productTable.getColumns().addAll(nameColumn, priceColumn, quantityColumn, actionsColumn);

        //read products from the file system (products.txt) and add them to the table
        //Product product = productService.loadProduct("name", 1.0, 1);

        productTable.getItems().add(new Product("product 1", 9.99, 25));
        productTable.getItems().add(new Product("product 2", 9.99, 25));
        productTable.getItems().add(new Product("product 3", 9.99, 25));
        productTable.getItems().add(new Product("product 4", 9.99, 25));
        productTable.getItems().add(new Product("product 5", 9.99, 25));
        productTable.getItems().add(new Product("product 6", 9.99, 25));
        productTable.getItems().add(new Product("product 7", 9.99, 25));
        productTable.getItems().add(new Product("product 8", 9.99, 25));

    }
}
