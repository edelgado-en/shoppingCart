package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ShoppingCartItemTest class in charge of testing the ShoppingCartItem class.
 *
 * @author Enrique Delgado
 */
public class ShoppingCartItemTest {

    @Test
    public void testShoppingCartItem() {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        assertNotNull(shoppingCartItem);
    }

    @Test
    public void testShoppingCartItemConstructor() {
        Product product = new Product(1, "name", 1.0, 1);

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, 1, 1);
        assertNotNull(shoppingCartItem);
    }

    @Test
    public void testGetProduct() {
        Product product = new Product(1, "name", 1.0, 1);

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, 1, 1);
        assertEquals(product, shoppingCartItem.getProduct());
    }

    @Test
    public void testGetQuantity() {
        Product product = new Product(1, "name", 1.0, 1);

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, 1, 1);
        assertEquals(1, shoppingCartItem.getQuantity());
    }

}
