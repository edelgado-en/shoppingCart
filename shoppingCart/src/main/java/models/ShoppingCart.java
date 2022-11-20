package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The ShoppingCart class represents a shopping cart.
 * It contains a list of products with their corresponding quantity, price, and the total price of the products.
 * @author Enrique Delgado
 */
public class ShoppingCart implements Serializable {

    public static final long serialVersionUID = 1L;

    private List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();

    /**
     * The total price of all the products in the shopping cart.
     */
    private double totalPrice;

    public ShoppingCart() {

    }

    public ShoppingCart(List<ShoppingCartItem> shoppingCartItemList, double totalPrice) {
        this.shoppingCartItemList = shoppingCartItemList;
        this.totalPrice = totalPrice;
    }

    public List<ShoppingCartItem> getShoppingCartItemList() {
        return shoppingCartItemList;
    }

    public void setShoppingCartItemList(List<ShoppingCartItem> shoppingCartItemList) {
        this.shoppingCartItemList = shoppingCartItemList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        shoppingCartItemList.add(shoppingCartItem);
    }

    public void removeShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        shoppingCartItemList.remove(shoppingCartItem);
    }

    public void clearShoppingCart() {
        shoppingCartItemList.clear();
        totalPrice = 0;
    }

    public boolean isEmpty() {
        return shoppingCartItemList.isEmpty();
    }

    public int size() {
        return shoppingCartItemList.size();
    }


    public ShoppingCartItem getShoppingCartItem(int productId) {
        // this lookup should be by productId
        for (ShoppingCartItem shoppingCartItem : shoppingCartItemList) {
            if (shoppingCartItem.getProduct().getId() == productId) {
                return shoppingCartItem;
            }
        }

        return null;
    }

    public void updateShoppingCartItem(ShoppingCartItem shoppingCartItem, int index) {
        shoppingCartItemList.set(index, shoppingCartItem);
    }

    public void updateTotalPrice() {
        double totalPrice = 0;
        for (ShoppingCartItem shoppingCartItem : shoppingCartItemList) {
            totalPrice += shoppingCartItem.getPrice();
        }
        this.totalPrice = totalPrice;
    }
}
