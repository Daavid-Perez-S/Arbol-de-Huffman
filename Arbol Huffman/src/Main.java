/*
 *  Creado por: David Pérez Sánchez
 *  Matrícula: 163202
 *  Materia: Estructura de Datos Avanzada
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: 09/02/2017
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author David Pérez S.
 */
public class Main extends Application {
      
      @Override
      public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLInicio.fxml"));
            Scene scene = new Scene(root);  
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
      }

      /**
       * @param args the command line arguments
       */
      public static void main(String[] args) {
            launch(args);
      }
}