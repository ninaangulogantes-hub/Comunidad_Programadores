package com.example.comunidad_programadores;

import com.example.comunidad_programadores.DAO.ProgramadoresDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioController {

    @FXML
    private ComboBox<String> cbLenguaje;
    @FXML
    private TableView<String> tvProgramadores;



//  Metodo que cierra la sesion
    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        Stage stage = (Stage)((Node)(actionEvent.getSource())).getScene().getWindow();

        stage.setTitle("Inicio");
        stage.setScene(scene);
        stage.show();
    }
//  Metodo que edita el perfil REVISAR HACER UN UNICO METODO
    public void editarPerfil(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        Stage stage = (Stage)((Node)(actionEvent.getSource())).getScene().getWindow();

        stage.setTitle("Inicio");
        stage.setScene(scene);
        stage.show();
    }
//   Metodo que crea un Excel
    public void crearExcel(ActionEvent actionEvent) {
    }
//  Metodo que crea un pdf
    public void crearPdf(ActionEvent actionEvent) {
    }
//  metodo que muestra en una tabla los programadores de un lenguaje en específico.
    public void seleccionarLenguaje(ActionEvent actionEvent) {
        String lenguaje = cbLenguaje.getValue();
        if(lenguaje.equalsIgnoreCase("java")){
            ProgramadoresDAO.filtrarProgramadores(cbLenguaje.getValue());
        }
    }
}
