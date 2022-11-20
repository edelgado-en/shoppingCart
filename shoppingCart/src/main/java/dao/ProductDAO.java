package dao;

import models.Product;

/**
 *
 * @author Enrique Delgado
 */
public class ProductDAO implements PersistenceService<Product> {

    @Override
    public void save(Product o) {
        //save product to the file system under products txt file
    }

    @Override
    public Product load(Product o) {
        // Read the product from the file system under products txt file
        //return the product object

        return null;
    }

}
