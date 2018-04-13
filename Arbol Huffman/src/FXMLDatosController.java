/*
 *  Creado por: David Pérez Sánchez
 *  Matrícula: 163202
 *  Materia: 
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: /10/2017
 */

import Otros.Ficheros;
import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David Pérez S.
 */
public class FXMLDatosController implements Initializable {
      
      @FXML private Button botonInicio;
      @FXML private TextArea tablaDeFrecuencias;
      @FXML private TextArea tablaDeBitCodes;
      
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
      
      /**
       * Initializes the controller class.
       * @param url
       * @param rb
       */
      @Override
      public void initialize(URL url, ResourceBundle rb) {
            
            Ficheros ficheroFrequencyTable= new Ficheros(System.getProperty("user.dir") + "\\Datos Huffman\\FrequencyTable.frecuency", false);
            File fileFrequencyTree= new File(System.getProperty("user.dir") + "\\Datos Huffman\\FrequencyTree.huffman");
            if(!fileFrequencyTree.exists())
                  tablaDeFrecuencias.setText("No se pudieron cargar los datos");
            else{
                  String texto= "";
                  ArrayList<String> contenido= ficheroFrequencyTable.leer();
                  for(int i=0; i<contenido.size(); i++){
                        texto+= contenido.get(i);
                        texto+= "\n";
                  }
                  tablaDeFrecuencias.setText(texto);
            }
            
            Ficheros ficheroBitCodesTable= new Ficheros(System.getProperty("user.dir") + "\\Datos Huffman\\BitCodesTable.bitCode", false);
            File fileBitCodesTable= new File(System.getProperty("user.dir") + "\\Datos Huffman\\BitCodesTable.bitCode");
            if(!fileBitCodesTable.exists())
                  tablaDeBitCodes.setText("No se pudieron cargar los datos");
            else {
                  String texto = "";
                  ArrayList<String> contenido = ficheroBitCodesTable.leer();
                  for (int i = 0; i < contenido.size(); i++) {
                        texto += contenido.get(i);
                        texto += "\n";
                  }
                  tablaDeBitCodes.setText(texto);
            }
      }    
}