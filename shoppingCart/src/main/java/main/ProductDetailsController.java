package main;

import models.Product;

public class ProductDetailsController extends AbstractController {

    private static final String TARGET_FXML = "productDetails.fxml";

    private Product product;

    public ProductDetailsController(Product product) {
        this.product = product;
    }



    @Override
    public String getTargetFxml() {
        return TARGET_FXML;
    }
}
