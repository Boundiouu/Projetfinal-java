module com.choupinou.projetfinaljava {
    requires javafx.controls;
    requires javafx.fxml;


    opens projetjava to javafx.fxml;
    exports projetjava;
}