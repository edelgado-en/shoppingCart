module com.cop4331.shoppingcart {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.cop4331.shoppingcart to javafx.fxml;
    exports com.cop4331.shoppingcart;
}