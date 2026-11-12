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

public class EditarController {
    @FXML
    TextField txtNombre;
    @FXML
    TextField txtContrasena;
    @FXML
    private ComboBox<String> cbLenguaje;
    @FXML
    RadioButton rbMujer;
    @FXML
    RadioButton rbHombre;
    @FXML
    RadioButton rbDecir;
    @FXML
    Label labelEstado;

    private Progamadores p;

    public void initialize() {
        cbLenguaje.getItems().addAll("Java", "Python", "JavaScript");
        txtNombre.setDisable(true);
    }

    public void editarDatos(String usuario) {
        // Pedimos los datos del programador a la BD
        p = ProgramadoresDAO.mostrarUsuario(usuario);

        // Comprobamos que existe
        if (p == null) {
            System.out.println("No se ha encontrado el usuario: " + usuario);
            return;
        }

        // Setteamos los datos
        txtNombre.setText(p.getUsuario());
        txtContrasena.setText(p.getContrasenha());
        cbLenguaje.setValue(p.getLenguaje());

        // Cargamos el género
        // Cargamos el género
        if (p.getGenero() != null) {

            if (p.getGenero().equalsIgnoreCase(rbHombre.getText())) {
                rbHombre.setSelected(true);

            } else if (p.getGenero().equalsIgnoreCase(rbMujer.getText())) {
                rbMujer.setSelected(true);

            } else if (p.getGenero().equalsIgnoreCase(rbDecir.getText())) {
                rbDecir.setSelected(true);
            }
        }
    }

    public void guardar(ActionEvent actionEvent) {

        if (p == null) {
            System.out.println("No hay ningún programador cargado");
            return;
        }

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

        if (txtContrasena.getText().isEmpty()) {
            alert.setContentText("Error.El campo contraseña debe estar completo.");
            alert.showAndWait();
            return;
        }
        if (txtContrasena.getText().length() < 8) {
            alert.setContentText("Error. La contraseña debe de tener más de 8 carácteres.");
            alert.showAndWait();
            return;
        }
        if (txtContrasena.getText().equals((txtContrasena.getText().toUpperCase()))) {
            alert.setContentText("Error. La contraseña debe de tener al menos una minúscula.");
            alert.showAndWait();
            return;
        }
        if (txtContrasena.getText().equals(txtContrasena.getText().toLowerCase())) {
            alert.setContentText("Error. La contraseña debe de tener al menos una mayúscula.");
            alert.showAndWait();
            return;
        }
            // Recogemos los nuevos datos
            String contrasena = txtContrasena.getText();
            String lenguaje = cbLenguaje.getValue();

            // Recogemos el género seleccionado
            String genero = "";

            if (rbHombre.isSelected()) {
                genero = rbHombre.getText();
            } else if (rbMujer.isSelected()) {
                genero = rbMujer.getText();
            } else if (rbDecir.isSelected()) {
                genero = rbDecir.getText();
            }

            // Actualizamos el objeto
            p.setContrasenha(contrasena);
            p.setLenguaje(lenguaje);
            p.setGenero(genero);

            // Actualizamos en la base de datos
            ProgramadoresDAO.actualizarProgramador(p);

        labelEstado.setText("¡Usuario registrado correctamente!");
    }
    public void cancelar(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        InicioController ic = fxmlLoader.getController();
        ic.enviarDatos(txtNombre.getText(), p.getGenero());

        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();

        stage.setTitle("Comunidad de programadores");
        stage.setScene(scene);
        stage.show();
    }
}




