package com.example.comunidad_programadores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
        }

        if(!rbMujer.isSelected()){
            alert.setContentText("Error. El genero deben estar completos.");
            alert.showAndWait();
            return;
        }
        if (!rbHombre.isSelected()) {
            alert.setContentText("Error. El genero deben estar completos.");
            alert.showAndWait();
            return;
        }
        if (!rbDecir.isSelected()) {
            alert.setContentText("Error. El genero deben estar completos.");
            alert.showAndWait();
            return;
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
