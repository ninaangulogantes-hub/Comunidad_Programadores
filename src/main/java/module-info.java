module com.example.comunidad_programadores {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires db4o;


    opens com.example.comunidad_programadores to javafx.fxml;
    exports com.example.comunidad_programadores;
}