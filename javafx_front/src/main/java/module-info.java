module ewa.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires com.google.gson;

    opens ewa.frontend to javafx.fxml, com.google.gson;
    exports ewa.frontend;
}
