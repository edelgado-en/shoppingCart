package dao;

import models.ShoppingCart;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * The ShoppingCartDAO class is responsible for saving and loading the shopping cart.
 *
 * @author Enrique Delgado
 */
public class ShoppingCartDAO implements PersistenceService<ShoppingCart> {

    private static final String FILE_NAME = "shoppingCart.xml";

    @Override
    public void save(ShoppingCart shoppingCart) {
        //using the xml encoder, save the provided shopping cart object to the file system under shoppingCart.xml
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(FILE_NAME);
            XMLEncoder encoder = new XMLEncoder(out);
            encoder.writeObject(shoppingCart);
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
    public ShoppingCart load(ShoppingCart o) {
        ShoppingCart loadedShoppingCart = null;

        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(FILE_NAME)));
            loadedShoppingCart = (ShoppingCart) decoder.readObject();
            decoder.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return loadedShoppingCart;
    }
}
