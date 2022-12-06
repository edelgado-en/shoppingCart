Shopping Cart Application
COP4331

Overview:
This application was built using Maven, Java 11, JavaFX 11.0.2 and SceneBuilder 11.0.0.
The application is a shopping cart that allows the user to add items to the cart,
remove items from the cart, and view the total price of the items in the cart.
The application also allows the user to save the cart to a file and load a cart from a file.
The application was built using the MVC design pattern.

Resources:
All of the fxml files were created using SceneBuilder 11.0.0 and are located in resources/main folder.
The dependencies for the project are located in the pom.xml file.
The persistence of the application is done using the Java Serialization API XMLEncoder and XMLDecoder. You can the xml
files at the root of the directory, namely: products.xml, shoppingCart.xml, and users.xml.


Application demo:
https://www.youtube.com/watch?v=O-IbO6w4Yiw

Build Instructions
------------------
1. You need to have Maven, Java 11 and JavaFx installed in your machine

2. Go to the root project where the pom.xml file is located and run the following command:
    mvn clean install
    This command will install all the required dependencies and build the application.

3. Configure your IDEA of choice to point to your Java and JavaFX SDKs

4. Run the application by going to the BootLoader.java class and execute the main method