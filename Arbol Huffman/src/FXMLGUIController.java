/*
 *  Creado por: David Pérez Sánchez
 *  Matrícula: 163202
 *  Materia: Estructura de Datos Avanzada
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: 09/02/2018
 */

import Huffman.AlgoritmoHuffman;
import Otros.Ficheros;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author David Pérez S.
 */
public class FXMLGUIController implements Initializable {
      
      @FXML private TextField rutaDelArchivoFXML;
      @FXML private RadioButton escribirFXML;
      @FXML private RadioButton seleccionarFXML;
      @FXML private Button abrirArchivoFXML;
      @FXML private Button botonInicio;
      @FXML private TextArea escribirCuadroTexto;
      private String rutaArchivo= null;
    
      @FXML
      private void comprimir(ActionEvent event) throws IOException {
            
            if((escribirFXML.isSelected() && escribirCuadroTexto.getText().equals("")) || (seleccionarFXML.isSelected() && rutaDelArchivoFXML.getText().equals(""))){
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("Alerta");
                  alert.setContentText("Escribe algo antes de empezar !!");
                  alert.showAndWait();
            }else{
                  if (escribirFXML.isSelected()) {
                        String texto = escribirCuadroTexto.getText();
                        eastersEggs(texto);
                        AlgoritmoHuffman huffman = new AlgoritmoHuffman(texto);
                        huffman.comprimir();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Éxito");
                        alert.setContentText("Texto comprimido exitósamente");
                        alert.showAndWait();
                  } else if (seleccionarFXML.isSelected()) {
                        Ficheros fichero = new Ficheros(rutaArchivo);
                        ArrayList<String> listaDeContenidos= fichero.leer();
                        AlgoritmoHuffman huffman = new AlgoritmoHuffman(listaDeContenidos);
                        huffman.comprimir();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Éxito");
                        alert.setContentText("Texto comprimido exitósamente");
                        alert.showAndWait();
                  } else {
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Alerta");
                        alert2.setContentText("Selecciona una opción antes de comenzar");
                        alert2.showAndWait();
                  }
            }
            // Cerramos la ventana presente y mostramos los datos
            Stage old = (Stage) abrirArchivoFXML.getScene().getWindow();
            Stage nuevo = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDatos.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            nuevo.setResizable(false);
            nuevo.setScene(scene);
            old.close();
            nuevo.show();
      }
    @FXML
    private void abrirExplorador(ActionEvent e) {
          
        final ExploradorImagenes explorador = new ExploradorImagenes();
        rutaArchivo = explorador.startExplorer();
        rutaDelArchivoFXML.setText(rutaArchivo);
    }
    
      @FXML
      private void inicio(ActionEvent event) throws IOException {
            Stage old = (Stage) botonInicio.getScene().getWindow();
            Stage nuevo = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInicio.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            nuevo.setResizable(false);
            nuevo.setScene(scene);
            old.close();
            nuevo.show();
      }
      
      private void eastersEggs(String texto) {
            if (texto.equals("Ella") || texto.equals("Ella y yo") || texto.equals("Yo y ella") || texto.equals("ella") || texto.equals("ella y yo") || texto.equals("yo y ella")) {
                  Alert alert2 = new Alert(Alert.AlertType.ERROR);
                  alert2.setTitle("Error");
                  alert2.setContentText("Ni hoy ni nunca\n:\"\"v");
                  alert2.showAndWait();
            }
      }

      @Override
      public void initialize(URL url, ResourceBundle rb){
            
            escribirFXML.setSelected(true);
            rutaDelArchivoFXML.setDisable(true);
            abrirArchivoFXML.setDisable(true);
            
            escribirFXML.selectedProperty().addListener((observable, oldValue, newValue) -> {
                  if (escribirFXML.isSelected()) {
                        escribirCuadroTexto.setDisable(false);
                        
                        seleccionarFXML.setSelected(false);
                        rutaDelArchivoFXML.setDisable(true);
                        abrirArchivoFXML.setDisable(true);
                  }
            });
            seleccionarFXML.selectedProperty().addListener((observable, oldValue, newValue) -> {
                  if (seleccionarFXML.isSelected()) {
                        escribirCuadroTexto.setDisable(true);
                        
                        escribirFXML.setSelected(false);
                        rutaDelArchivoFXML.setDisable(false);
                        abrirArchivoFXML.setDisable(false);
                  }
            });
      }
}