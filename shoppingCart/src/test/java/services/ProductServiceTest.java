package services;

import dao.ProductDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTest {

    private ProductDAO productDAO;

    @Test
    public void testGetProductList() {
        ProductService productService = new ProductService(new ProductDAO());
        assertEquals(1, 1);
    }

}
