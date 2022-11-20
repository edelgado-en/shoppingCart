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

        //save the shopping cart to the file system
        shoppingCartDAO.save(shoppingCart);

    }

    public void removeFromCart(Product product) {
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
