package services;

import dao.ProductDAO;
import models.Product;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void saveProduct(String name, double price, int quantity) {
        productDAO.save(new Product(name, price, quantity));
    }

    public Product loadProduct(String name, double price, int quantity) {
        return productDAO.load(new Product(name, price, quantity));
    }
}
