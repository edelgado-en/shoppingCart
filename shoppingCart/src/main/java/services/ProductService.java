package services;

import dao.ProductDAO;
import models.Product;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void saveProduct(Integer id, String name, double price, int quantity) {
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

        try {
            out = new FileOutputStream("products.xml");
            XMLEncoder encoder = new XMLEncoder(out);
            encoder.writeObject(products);
            encoder.close();
            out.close();

            /*FileInputStream fis = new FileInputStream("products.xml");
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(fis));
            ArrayList<Product> test = (ArrayList<Product>) decoder.readObject();
            decoder.close();
            fis.close();*/

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
