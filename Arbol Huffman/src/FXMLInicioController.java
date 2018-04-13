/*
 *  Creado por: David Pérez Sánchez
 *  Matrícula: 163202
 *  Materia: Estructura de Datos Avanzada
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: 09/02/2017
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David Pérez S.
 */
public class FXMLInicioController implements Initializable {
      
      @FXML private Button comprimir;
      @FXML private Button desComprimir;
      /**
       * Initializes the controller class.
       */
      @FXML
      private void comprimir(ActionEvent event) throws IOException {
            
            Stage old = (Stage) comprimir.getScene().getWindow();
            Stage nuevo = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGUI.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            nuevo.setResizable(false);
            nuevo.setScene(scene);
            old.close();
            nuevo.show();
      }
      @FXML
      private void desComprimir(ActionEvent event) throws IOException {
            
            Stage old = (Stage) desComprimir.getScene().getWindow();
            Stage nuevo = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDatosDescomprimidos.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            nuevo.setResizable(false);
            nuevo.setScene(scene);
            old.close();
            nuevo.show();
      }
      @Override
      public void initialize(URL url, ResourceBundle rb) {
            // Vacío
      }      
      
}
