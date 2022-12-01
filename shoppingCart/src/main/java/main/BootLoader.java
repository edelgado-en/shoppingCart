package main;

/**
 * BootLoader class in charge of loading the application. The main method calls the ShoppingCartApplication class.
 * In order to create an executable jar file, we need to create a BootLoader class that contains the main method.
 * You cannot create an executable jar file directly from ShoppingCartApplication because it extends another class.
 *
 * @author Enrique Delgado
 */
public class BootLoader {

    public static void main(String[] args) {
        ShoppingCartApplication.main(args);
    }
}
