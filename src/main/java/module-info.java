module com.example.comunidad_programadores {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires db4o;

    exports com.example.comunidad_programadores;
    exports com.example.comunidad_programadores.DAO;

    opens com.example.comunidad_programadores to javafx.fxml;
    opens com.example.comunidad_programadores.entidades to db4o, javafx.base;


}