module com.cop4331.shoppingcart {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens main to javafx.fxml;
    opens models to javafx.base;
    opens dao to javafx.base;
    opens services to javafx.base;


    exports models;
    exports main;
}