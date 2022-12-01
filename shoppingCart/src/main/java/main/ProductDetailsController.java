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
 * This class shows the information related to the provided product.
 *
 * @author Enrique Delgado
 */
public class ProductDetailsController extends AbstractController implements Initializable {

    private static final String TARGET_FXML = "productDetails.fxml";

    /**
     * Product description label binding.
     */
    @FXML
    private Label productDescription;

    /**
     * Product name label binding.
     */
    @FXML
    private Label productName;

    /**
     * Product price label binding.
     */
    @FXML
    private Label productPrice;

    /**
     * Product quantity binding.
     */
    @FXML
    private Label productQuantity;

    /**
     * Product to be displayed.
     */
    private Product product;

    /**
     * Instantiantes a new ProductDetailsController with the provided product object.
     *
     * @param product the product to show details for.
     */
    public ProductDetailsController(Product product) {
        this.product = product;
    }

    @Override
    public String getTargetFxml() {
        return TARGET_FXML;
    }

    /**
     * Initializes this class setting the values from the provided product.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productName.setText(product.getName());
        //productDescription.setText(product.getDescription());
        productPrice.setText(String.valueOf(product.getPrice()));
        productQuantity.setText(String.valueOf(product.getQuantity()));
    }

    /**
     * Redirects the user to the product list view.
     * @param event the button action event
     * @throws IOException
     */
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
