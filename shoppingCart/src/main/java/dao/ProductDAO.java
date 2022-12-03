package dao;

import models.Product;
import models.User;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Enrique Delgado
 */
public class ProductDAO implements PersistenceService<Product> {

    private static final String FILE_NAME = "products.xml";

    @Override
    public void save(Product product) {
        //add product to the list of products in products.xml using XMLEncoder
        ArrayList<Product> productList = new ArrayList<>();

        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(FILE_NAME)));
            productList = (ArrayList<Product>) decoder.readObject();
            decoder.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check if the product already exists in the list. Throw an exception if it does.
        for (Product p : productList) {
            if (p.getName().equals(product.getName())) {
                throw new IllegalArgumentException("Product already exists");
            }
        }

        productList.add(product);

        //write the new list of users to the file
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(FILE_NAME);
            XMLEncoder encoder = new XMLEncoder(out);
            encoder.writeObject(productList);
            encoder.close();
            out.close();

        } catch (Exception e) {
            System.out.println(e);

        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override
    public Product load(Product o) {
        // Read the product from the file system under products txt file
        //return the product object

        return null;
    }

}
