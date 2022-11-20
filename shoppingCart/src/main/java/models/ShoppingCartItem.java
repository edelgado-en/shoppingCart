package models;

import java.io.Serializable;

/**
 * Shopping Cart Item model class that represents a product with its quantity,
 * and the price of the product multiply by the quantity.
 *
 * @author Enrique Delgado
 */
public class ShoppingCartItem implements Serializable {

    public static final long serialVersionUID = 1L;

    private Product product;
    private int quantity;

    /**
     * The price of the product multiply by the quantity.
     */
    private double price;

    public ShoppingCartItem() {

    }

    public ShoppingCartItem(Product product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return product.getName();
    }

    public void setName(String name) {
        product.setName(name);
    }
}
