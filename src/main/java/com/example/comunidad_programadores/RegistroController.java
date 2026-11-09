package com.example.comunidad_programadores;

import com.example.comunidad_programadores.DAO.ProgramadoresDAO;
import com.example.comunidad_programadores.entidades.Progamadores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroController {
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtContrasenha;
    @FXML
    private RadioButton rbMujer;
    @FXML
    private RadioButton rbHombre;
    @FXML
    private RadioButton rbDecir;
    @FXML
    private ComboBox<String> cbLenguaje;
    @FXML
    private Label labelEstado;

    String genero = "";
    String lenguaje = "";

    public void initialize() {
        cbLenguaje.getItems().addAll("Java", "Python", "JavaScript");
    }

    public void registrar(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");

        if (txtNombre.getText().length() < 5) {
            alert.setContentText("El nombre debe tener más de 5 carácteres.");
            alert.showAndWait();
            return;
        }
        if (txtNombre.getText().isEmpty()) {
            alert.setContentText("Error. El campo nombre debe estar completo.");
            alert.showAndWait();
            return;
        }

//      Hacer lo de que solo permita unico nombre de usuario
        if (txtContrasenha.getText().isEmpty()) {
            alert.setContentText("Error.El campo contraseña debe estar completo.");
            alert.showAndWait();
            return;
        }
        if (txtContrasenha.getText().length() < 8) {
            alert.setContentText("Error. La contraseña debe de tener más de 8 carácteres.");
            alert.showAndWait();
            return;
        }
        if (txtContrasenha.getText().equals((txtContrasenha.getText().toUpperCase()))) {
            alert.setContentText("Error. La contraseña debe de tener al menos una minúscula.");
            alert.showAndWait();
            return;
        }
        if (txtContrasenha.getText().equals(txtContrasenha.getText().toLowerCase())) {
            alert.setContentText("Error. La contraseña debe de tener al menos una mayúscula.");
            alert.showAndWait();
            return;
        }
        if (cbLenguaje == null) {
            alert.setContentText("Error. Los lenguajes deben estar completos.");
            alert.showAndWait();
            return;
        }else{
           lenguaje = cbLenguaje.getValue();
        }

        if(rbMujer.isSelected()){
            genero = "Mujer";
        } else if (rbHombre.isSelected()) {
            genero = "Hombre";
        } else if (rbDecir.isSelected()) {
            genero = "Prefiero no decirlo";
        }else{
            alert.setContentText("Error. El sexo debe ser seleccionado");
            alert.showAndWait();
            return;
        }

        if(ProgramadoresDAO.registrarProgramador(txtNombre.getText(),txtContrasenha.getText(), genero, lenguaje)){
            labelEstado.setText("Usuario registrado con éxito!");
        }else{
            labelEstado.setText("Error al registrar. Inténtelo de nuevo.");
            txtNombre.setText(" ");
            txtContrasenha.setText(" ");
        }
    }

    public void inicio(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        Stage stage = (Stage)((Node)(actionEvent.getSource())).getScene().getWindow();

        stage.setTitle("Inicio");
        stage.setScene(scene);
        stage.show();
    }
}
