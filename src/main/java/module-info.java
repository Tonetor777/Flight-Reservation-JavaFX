module com.example.flight {
    requires javafx.controls;
    requires javafx.fxml;

    opens models to javafx.base;
    opens com.example.flight to javafx.fxml;
    exports com.example.flight;
}
