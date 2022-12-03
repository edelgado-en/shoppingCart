package main;

import dao.ProductDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import services.ProductService;
import services.SceneLoaderService;

import java.io.IOException;

/**
 * AddProductController class in charge of adding new products.
 *
 * @author Enrique Delgado
 */
public class AddProductController extends AbstractController {

    /**
     * Target FXML file.
     */
    private static final String TARGET_FXML = "newProduct.fxml";

    @FXML
    private TextField productName;

    @FXML
    private TextField productPrice;

    @FXML
    private TextField productQuantity;

    @FXML
    private Label responseMessage;

    private ProductService productService;

    /**
     * AddProductController constructor. It takes a productService instance.
     *
     * @param productService
     */
    public AddProductController(ProductService productService) {
        this.productService = productService;
    }

    @FXML
    void addProduct(ActionEvent event) {
        // get the latest product id and increase by 1
        int id = productService.getProductList().size() + 1;

        try {
            productService.addProduct(id,
                    productName.getText(),
                    Double.parseDouble(productPrice.getText()),
                    Integer.parseInt(productQuantity.getText()));
            //reset values
            productName.setText("");
            productPrice.setText("");
            productQuantity.setText("");

            //set response message
            responseMessage.setText("Product added successfully!");

        } catch (Exception e) {
            responseMessage.setText("product name already exists.");
        }
    }

    @FXML
    void backToInventory(ActionEvent event) throws IOException {
        Pane centerView = SceneLoaderService.loadPane(InventoryListController.build());
        MainController.loadCenterView(centerView);
    }

    @Override
    public String getTargetFxml() {
        return TARGET_FXML;
    }

    /**
     * Builds a new AddProductController instance.
     * @return
     */
    public static AddProductController build() {
        return new AddProductController(new ProductService(new ProductDAO()));
    }
}
