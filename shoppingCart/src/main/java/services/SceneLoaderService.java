package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.AbstractController;
import main.ShoppingCartApplication;

import java.io.IOException;

/**
 * Service is charge of loading scenes. This is an abstraction to handle the responsability of loading scenes.
 *
 * @author Enrique Delgado
 */
public class SceneLoaderService {

    public static void loadScene(Stage stage, AbstractController controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingCartApplication.class.getResource(controller.getTargetFxml()));
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static Pane loadPane(AbstractController controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingCartApplication.class.getResource(controller.getTargetFxml()));
        fxmlLoader.setController(controller);
        return fxmlLoader.load();
    }

}
