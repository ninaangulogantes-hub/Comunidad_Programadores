package com.example.comunidad_programadores;

import com.db4o.ObjectContainer;
import com.example.comunidad_programadores.DAO.ConexionDb4o;
import com.example.comunidad_programadores.DAO.ProgramadoresDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.*;

import java.io.IOException;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoginController {
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtContrasenha;

    @FXML
    private Button bttLogin;

    @FXML
    private Button bttRegistrarse;
    @FXML
    private Label labelError;

    private String errores = "";

    @FXML
    public void loggearse(ActionEvent actionEvent) throws IOException {

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

        if(ProgramadoresDAO.usuarioValido(txtNombre.getText(), txtContrasenha.getText())){

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inicio.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

//          Le compartimos a inicio el usuario que se ha loggeado
            InicioController inicio = fxmlLoader.getController();
            inicio.enviarDatos(txtNombre.getText());

            Stage stage = (Stage)((Node)(actionEvent.getSource())).getScene().getWindow();
            stage.setTitle("Inicio");
            stage.setScene(scene);
            stage.show();

        }else{

            labelError.setText("Error. Ese el usuario no existe. Vuelva a intentarlo o pulse el boton -Registrar-.");
            txtNombre.setText(" ");
            txtContrasenha.setText(" ");
        }
    }

    public void registrarse(ActionEvent actionEvent) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registro.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = (Stage)((Node)(actionEvent.getSource())).getScene().getWindow();
            stage.setTitle("Inicio");
            stage.setScene(scene);
            stage.show();

    }
}
