package com.example.comunidad_programadores;

import com.example.comunidad_programadores.DAO.ProgramadoresDAO;
import com.example.comunidad_programadores.entidades.Progamadores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class EditarController {
    @FXML
    TextField txtNombre;
    @FXML
    private TextField txtContrasenha;
    @FXML
    private ComboBox<String> cbLenguaje;
    @FXML
    private RadioButton rbMujer;
    @FXML
    private RadioButton rbHombre;
    @FXML
    private RadioButton rbDecir;

    public void initialize() {
        cbLenguaje.getItems().addAll("Java", "Python", "JavaScript");
        txtNombre.setDisable(true);
    }

    public void editarDatos(String usuario) {

//      Pedimos los datos del programador a la BD
        Progamadores p = ProgramadoresDAO.mostrarUsuario(usuario);

//        QUEDASTE AQUÍ

    }

    public void guardar(ActionEvent actionEvent) {
    }

    public void cancelar(ActionEvent actionEvent) {

    }
}
