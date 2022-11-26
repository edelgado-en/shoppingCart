package main;

/**
 * Abstract class that provides the basic functionality for all controllers.
 * All controllers must specify the target FXML file.
 *
 * @author Enrique Delgado
 */
public abstract class AbstractController {

    public abstract String getTargetFxml();
}
