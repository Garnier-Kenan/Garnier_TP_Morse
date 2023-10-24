module com.garnier.garnier_tp_morse {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.garnier.garnier_tp_morse to javafx.fxml;
    exports com.garnier.garnier_tp_morse;
}