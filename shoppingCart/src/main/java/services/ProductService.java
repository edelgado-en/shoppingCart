package services;

import dao.ProductDAO;
import models.Product;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Service in charge of saving and loading products.
 *
 * @author Enrique Delgado
 */
public class ProductService {

    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void addProduct(Integer id, String name, double price, int quantity) {
        productDAO.save(new Product(id, name, price, quantity));
    }

    public Product loadProduct(Integer id, String name, double price, int quantity) {
        return productDAO.load(new Product(id, name, price, quantity));
    }

    //get product list from products.xml
    public ArrayList<Product> getProductList() {
        ArrayList<Product> productList = new ArrayList<>();

        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("products.xml")));
            productList = (ArrayList<Product>) decoder.readObject();
            decoder.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public void writeProductsToXML(ArrayList<Product> products) {
        FileOutputStream out = null;
        // TODO: This should be in the DAO layer
        try {
            out = new FileOutputStream("products.xml");
            XMLEncoder encoder = new XMLEncoder(out);
            encoder.writeObject(products);
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
}
