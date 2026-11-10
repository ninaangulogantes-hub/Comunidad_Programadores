package com.example.comunidad_programadores;

import com.example.comunidad_programadores.DAO.ProgramadoresDAO;
import com.example.comunidad_programadores.entidades.Progamadores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class InicioController {

    @FXML
    private ComboBox<String> cbLenguaje;
    @FXML
    private TableView<Progamadores> tvProgramadores;
    @FXML
    private TableColumn<Progamadores, String> colUsuario;
    @FXML
    private TableColumn<Progamadores, String> colGenero;
    @FXML
    private TableColumn<Progamadores, String> colLenguaje;
    @FXML
    private Label labelBienvenida;

    String usuario = "";

    @FXML
    public void initialize() {
        cbLenguaje.getItems().addAll("Todos", "Java", "Python", "JavaScript");
        cbLenguaje.setValue("Todos");

        // Vinculamos cada columna con el atributo exacto de la clase Progamadores
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colLenguaje.setCellValueFactory(new PropertyValueFactory<>("lenguaje"));

        seleccionarLenguaje(null);

    }

    //  Metodo que cierra la sesion
    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();

        stage.setTitle("Inicio");
        stage.setScene(scene);
        stage.show();
    }

    //  Metodo que edita el perfil REVISAR HACER UN UNICO METODO
    public void editarPerfil(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        EditarController editar = fxmlLoader.getController();
        editar.editarDatos(usuario);

        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();

        stage.setTitle("Editar");
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
    @FXML
    public void seleccionarLenguaje(ActionEvent actionEvent) {

        String lenguajeElegido = cbLenguaje.getValue();

//      recogemos el arraylist del dao
        ArrayList<Progamadores> listaDesdeDAO = ProgramadoresDAO.filtrarProgramadores(lenguajeElegido);
//      Limpiamos para borrar la seleccion anterior
        tvProgramadores.getItems().clear();

        // Lo convertimos al tipo para mostrarlo en la tabla
//      ObservableList<Progamadores> lista = FXCollections.observableArrayList(listaDesdeDAO);
        tvProgramadores.getItems().addAll(listaDesdeDAO);


    }

    public void enviarDatos(String nombre) {
        this.usuario = usuario;

        if(cbLenguaje.getValue().equalsIgnoreCase("hombre")){
            labelBienvenida.setText("Bienvenido, "+usuario);
        } else if (cbLenguaje.getValue().equalsIgnoreCase("mujer")) {
            labelBienvenida.setText("Bienvenida, "+usuario);
        }else{
            labelBienvenida.setText("Bienvenidx, "+usuario);
        }

    }
}
