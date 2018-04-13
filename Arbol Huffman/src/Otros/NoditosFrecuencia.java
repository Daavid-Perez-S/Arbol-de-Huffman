package Otros;

import java.io.Serializable;

/*
 *  Creado por: David Pérez Sánchez
 *  Matrícula: 163202
 *  Materia: Estructura de Datos Avanzada
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: 09/02/2018
 */

/**
 * @author David Pérez S.
 */
public class NoditosFrecuencia implements Serializable{

      private String letra;
      private Integer frecuencia;
      private NoditosFrecuencia izquierdo;
      private NoditosFrecuencia derecho;
      private NoditosFrecuencia padre;
      private Integer bitCode;
      
      public NoditosFrecuencia(String letra, Integer frecuencia) {
            this.letra= letra;
            this.frecuencia= frecuencia;
            izquierdo= null;
            derecho= null;
            padre= null;
            bitCode= null;
      }
      //public NoditosFrecuencia() {}
      
      public String getLetra() {         // Obtenemos la letra del nodo presente
            return letra;
      }
      public Integer getFrecuencia() {         // Obtenemos la frecuencia del nodo presente
            return frecuencia;
      }
      public NoditosFrecuencia getIzquierdo() {
            return izquierdo;
      }
      public NoditosFrecuencia getDerecho() {
            return derecho;
      }
      public NoditosFrecuencia getPadre() {
            return padre;
      }
      public Integer getBitCode(){
            return bitCode;
      }
      public void setLetra(String letra){
            this.letra= letra;
      }
      public void setFrecuencia(Integer frecuencia){
            this.frecuencia= frecuencia;
      }
      public void setIzquierdo(NoditosFrecuencia izquierdo){
            this.izquierdo= izquierdo;
      }
      public void setDerecho(NoditosFrecuencia derecho){
            this.derecho= derecho;
      }
      public void setPadre(NoditosFrecuencia padre){
            this.padre= padre;
      }
      public void setBitCode(Integer bitCode){
            this.bitCode= bitCode;
      }
      @Override
      public String toString(){
            return letra + "-" + frecuencia + "-" + bitCode;
      }
      public String toString2() {
            return letra + "-" + frecuencia;
      }
}