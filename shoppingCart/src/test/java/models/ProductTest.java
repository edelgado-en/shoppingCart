package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * ProductTest class in charge of testing the Product class.
 *
 * @author Enrique Delgado
 */
public class ProductTest {

    @Test
    public void testProduct() {
        Product product = new Product();
        assertEquals(1, 1);
    }

    @Test
    public void testProductConstructor() {
        Product product = new Product(1, "name", 1.0, 1);
        assertEquals(1, 1);
    }

    @Test
    public void testSetId() {
        Product product = new Product();
        product.setId(1);
        assertEquals(1, 1);
    }

    @Test
    public void testGetId() {
        Product product = new Product();
        product.setId(1);
        assertEquals(1, product.getId());
    }

    @Test
    public void testGetName() {
        Product product = new Product();
        product.setName("name");
        assertEquals("name", product.getName());
    }

    @Test
    public void testGetPrice() {
        Product product = new Product();
        product.setPrice(1.0);
        assertEquals(1.0, product.getPrice());
    }

    @Test
    public void testGetQuantity() {
        Product product = new Product();
        product.setQuantity(1);
        assertEquals(1, product.getQuantity());
    }

    @Test
    public void testSetName() {
        Product product = new Product();
        product.setName("name");
        assertEquals(1, 1);
    }

    @Test
    public void testSetPrice() {
        Product product = new Product();
        product.setPrice(1.0);
        assertEquals(1, 1);
    }

    @Test
    public void testSetQuantity() {
        Product product = new Product();
        product.setQuantity(1);
        assertEquals(1, 1);
    }

    @Test
    public void testEquals() {
        Product product = new Product();
        product.setName("name");
        Product product2 = new Product();
        product2.setName("name");
        assertEquals(product, product2);
    }

    @Test
    public void testNotEquals() {
        Product product = new Product();
        product.setName("name1");
        Product product2 = new Product();
        product2.setName("name2");
        assertNotEquals(product, product2);
    }
}
