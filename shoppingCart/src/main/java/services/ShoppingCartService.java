package services;

import dao.ShoppingCartDAO;
import models.Product;
import models.ShoppingCart;
import models.ShoppingCartItem;

/**
 * ShoppingCartService class in charge of handling actions related to shopping cart.
 *
 * @author Enrique Delgado
 */
public class ShoppingCartService {

    private ShoppingCartDAO shoppingCartDAO;

    public ShoppingCartService(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }

    /**
     * Adds a product to the shopping cart. If the shopping cart does not exists, it creates a new one and
     * add the provided product with a quantity of 1.
     * if the shopping cart exists, then it checks if the product already exists in the shopping cart. If it does,
     * it increases the quantity by 1 and updates the total price of the shopping cart. If the product does not exist
     * in the shopping cart, it adds the product with a quantity of 1 and updates the total price of the shopping cart.
     * @param product
     */
    public void addToCart(Product product) {
        // fetch shopping cart from shoppingCart.xml
        ShoppingCart shoppingCart = shoppingCartDAO.load(null);

        //if there is no shoppingCart object, create a new one and add a shopping cart item with the provided product
        //with quantity 1
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();

            //create a new shopping cart item with the provided product and quantity 1
            shoppingCart.addShoppingCartItem(new ShoppingCartItem(product,
                                                           1,
                                                                  product.getPrice()));

        } else {
            //check if the product already exists in the shopping cart
            ShoppingCartItem shoppingCartItem = shoppingCart.getShoppingCartItem(product.getId());

            //if the shoppingCartItem exists, increment the quantity by 1
            if (shoppingCartItem != null) {
                // the price has to be adjusted based on the new quantity
                double newPrice = product.getPrice() * (shoppingCartItem.getQuantity() + 1);

                shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + 1);
                shoppingCartItem.setPrice(newPrice);

            } else {

                shoppingCart.addShoppingCartItem(new ShoppingCartItem(product,
                                                               1,
                                                                      product.getPrice()));
            }
        }

        //update the shopping total price
        shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() + product.getPrice());

        //save the shopping cart to the persistence layer
        shoppingCartDAO.save(shoppingCart);

    }

    /**
     * Decreases the quantity of the provided product in the shopping cart. If the quantity reaches 0, then
     * the product is removed from the shopping cart. If the provided product is removed, then it returns true,
     * otherwise it returns false.
     *
     * If the shopping cart does not exists, it returns an exception.
     *
     * @param product
     * @return
     */
    public boolean removeFromCart(Product product) {
        boolean productRemoved = false;

        // fetch shopping cart from shoppingCart.xml
        ShoppingCart shoppingCart = shoppingCartDAO.load(null);

        if (shoppingCart != null) {
            //check if the product already exists in the shopping cart
            ShoppingCartItem shoppingCartItem = shoppingCart.getShoppingCartItem(product.getId());

            //if the shoppingCartItem exists, decrement the quantity by 1
            if (shoppingCartItem != null) {
                int updatedQuantity = shoppingCartItem.getQuantity() - 1;
                shoppingCartItem.setQuantity(updatedQuantity);

                //if the quantity is 0, remove the shopping cart item from the shopping cart
                if (shoppingCartItem.getQuantity() == 0) {
                    shoppingCart.removeShoppingCartItem(shoppingCartItem);

                    productRemoved = true;

                } else {
                    // the price has to be adjusted based on the new quantity
                    double newPrice = product.getPrice() * updatedQuantity;

                    shoppingCartItem.setPrice(newPrice);
                }
            }

            //update the shopping total price
            shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() - product.getPrice());

            //save the shopping cart to the file system
            shoppingCartDAO.save(shoppingCart);

            return productRemoved;

        } else {
            throw new RuntimeException("Shopping cart not found");
        }
    }

    public void deleteShoppingCart() {
        // fetch shopping cart from shoppingCart.xml
        ShoppingCart shoppingCart = shoppingCartDAO.load(null);

        if (shoppingCart != null) {
            //clear the shopping cart
            shoppingCart.clearShoppingCart();

            //save the shopping cart to the file system
            shoppingCartDAO.save(shoppingCart);
        }
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCartDAO.load(null);
    }

}
