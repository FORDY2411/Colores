module com.mycompany.modelorgb {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.modelorgb to javafx.fxml;
    exports com.mycompany.modelorgb;
}
