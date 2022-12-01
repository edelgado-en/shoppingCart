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

    /**
     * Instantiates a new ShoppingCart with the provided shopping cart item list and total price.
     * @param shoppingCartItemList
     * @param totalPrice
     */
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

    /**
     * Removes all entries of the shopping cart item list and rest the total price to 0.
     */
    public void clearShoppingCart() {
        shoppingCartItemList.clear();
        totalPrice = 0;
    }

    /**
     * Returns true is the shopping cart item list is empty.
     * @return
     */
    public boolean isEmpty() {
        return shoppingCartItemList.isEmpty();
    }

    /**
     * Returns the size of the shopping cart item list.
     * @return
     */
    public int size() {
        return shoppingCartItemList.size();
    }

    /**
     * Gets the shopping cart item from the shopping cart item list matching the provided product id.
     * Otherwise, returns null.
     * @param productId
     * @return
     */
    public ShoppingCartItem getShoppingCartItem(int productId) {
        // this lookup should be by productId
        for (ShoppingCartItem shoppingCartItem : shoppingCartItemList) {
            if (shoppingCartItem.getProduct().getId() == productId) {
                return shoppingCartItem;
            }
        }

        return null;
    }

    /**
     * Updates the shopping cart item list with the prodived shopping car item. The lookup is done
     * by index.
     * @param shoppingCartItem
     * @param index
     */
    public void updateShoppingCartItem(ShoppingCartItem shoppingCartItem, int index) {
        shoppingCartItemList.set(index, shoppingCartItem);
    }

    /**
     * Updates the total price of the shopping cart by adding the prices of all shopping cart items.
     */
    public void updateTotalPrice() {
        double totalPrice = 0;
        for (ShoppingCartItem shoppingCartItem : shoppingCartItemList) {
            totalPrice += shoppingCartItem.getPrice();
        }
        this.totalPrice = totalPrice;
    }
}
