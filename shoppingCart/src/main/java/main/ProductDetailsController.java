package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import models.Product;
import services.SceneLoaderService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * ProductDetailsController class in charge of handling actions related to product details.
 *
 * @author Enrique Delgado
 */
public class ProductDetailsController extends AbstractController implements Initializable {

    private static final String TARGET_FXML = "productDetails.fxml";

    @FXML
    private Label productDescription;

    @FXML
    private Label productName;

    @FXML
    private Label productPrice;

    @FXML
    private Label productQuantity;

    private Product product;

    public ProductDetailsController(Product product) {
        this.product = product;
    }

    @Override
    public String getTargetFxml() {
        return TARGET_FXML;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productName.setText(product.getName());
        //productDescription.setText(product.getDescription());
        productPrice.setText(String.valueOf(product.getPrice()));
        productQuantity.setText(String.valueOf(product.getQuantity()));
    }

    @FXML
    void backToListing(ActionEvent event) throws IOException {
        Pane centerView = SceneLoaderService.loadPane(ProductListController.build());
        MainController.loadCenterView(centerView);

    }

    /**
     * Factory method to create a new ProductDetailsController. Abstraction to hide the implementation details.
     * This method knows how to build a ProductDetailsController.
     *
     * @return an instance of ProductDetailsController
     */
    public static ProductDetailsController build(Product product) {
        return new ProductDetailsController(product);
    }
}
