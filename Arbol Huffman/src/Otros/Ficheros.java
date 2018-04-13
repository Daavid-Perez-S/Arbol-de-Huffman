/*
 *  Creado por: David Pérez Sánchez
 *  Matrícula: 163202
 *  Materia: 
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: 09/02/2018
 */

package Otros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author David Pérez S.
 */
public class Ficheros{
      
      private final String rutaArchivo;
      private ArrayList<String> listaDeContenidos= new ArrayList<>();
      private Boolean banderaAgregarSaltoDeLinea;
      
      public Ficheros(String rutaArchivo){
            this.rutaArchivo= rutaArchivo;
            banderaAgregarSaltoDeLinea= true;
      }
      public Ficheros(String rutaArchivo, Boolean banderaAgregarSaltoDeLinea){
            this.rutaArchivo= rutaArchivo;
            this.banderaAgregarSaltoDeLinea= banderaAgregarSaltoDeLinea;
      }
      
      public ArrayList<String> leer() {
            File archivo;
            FileReader fr= null;
            BufferedReader br;
            char saltoDeLinea= '\n';
            try {
                  // Apertura del fichero y creacion de BufferedReader para poder
                  // hacer una lectura comoda (disponer del metodo readLine()).
                  archivo = new File(rutaArchivo);
                  fr = new FileReader(archivo);
                  br = new BufferedReader(fr);

                  // Lectura del fichero
                  String linea;
                  while ((linea = br.readLine()) != null){
                        if(banderaAgregarSaltoDeLinea)
                              listaDeContenidos.add(linea + saltoDeLinea);
                        else
                              listaDeContenidos.add(linea);
                  }
            } catch (IOException e) {
            } finally {
                  // En el finally cerramos el fichero, para asegurarnos
                  // que se cierra tanto si todo va bien como si salta 
                  // una excepcion.
                  try {
                        if (fr != null)
                              fr.close();
                  } catch (IOException e2) {}
            }
            return listaDeContenidos;
      }
      
      public void escribir(String contenido, Boolean sobreEscribir){
            
            FileWriter fichero = null;
            PrintWriter pw;
            try {
                  fichero = new FileWriter(rutaArchivo, sobreEscribir);
                  pw = new PrintWriter(fichero);
                  pw.println(contenido);
                  
            } catch (IOException e) {
            } finally {
                  try {
                        // Nuevamente aprovechamos el finally para 
                        // asegurarnos que se cierra el fichero.
                        if (fichero != null)
                              fichero.close();
                  } catch (IOException e2) {}
            }
      }
      
      public void escribir(String contenido){
            escribir(contenido, false);
      }
}