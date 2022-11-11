package models;

import javafx.beans.property.*;

import java.io.Serializable;

/**
 * Product model class that represents a product.
 *
 * @author Enrique Delgado
 */
public class Product implements Serializable {

    public static final long serialVersionUID = 1L;

    private IntegerProperty id = new SimpleIntegerProperty();

    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty price = new SimpleDoubleProperty();
    private IntegerProperty quantity = new SimpleIntegerProperty();

    public Product() {

    }
    public Product(Integer id, String name, double price, int quantity) {
        this.id.set(id);
        this.name.set(name);
        this.price.set(price);
        this.quantity.set(quantity);
    }
    public void setId(Integer value) {
        id.set(value);
    }

    public Integer getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price.get();
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Product)) {
            return false;
        }

        Product product = (Product) obj;

        return name.equals(product.getName()) && price.equals(product.getPrice()) && quantity.equals(product.getQuantity());
    }

    @Override
    public int hashCode() {
        return (int) (name.get().hashCode() + price.get() + quantity.get());
    }
}
