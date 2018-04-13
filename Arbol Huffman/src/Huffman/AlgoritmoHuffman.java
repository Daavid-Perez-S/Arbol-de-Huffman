/*
 *  Creado por: David Pérez Sánchez
 *  Matrícula: 163202
 *  Materia: Estructura de Datos Avanzada
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: 09/02/2018
 */

package Huffman;

import Otros.Archivo;
import Otros.Ficheros;
import Otros.NoditosFrecuencia;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Stack;

/**
 * @author David Pérez S.
 */
public class AlgoritmoHuffman {
      
      private final String texto;
      private final ArrayList<String> letrasRepetidas= new ArrayList<>();
      private final ArrayList<NoditosFrecuencia> referenciasANodosHojas= new ArrayList<>();
      
      public AlgoritmoHuffman(String texto){
            this.texto= texto;
      }
      public AlgoritmoHuffman(ArrayList<String> listaDeContenidos){
            
            this.texto= arrayListToString(listaDeContenidos);
      }
      
      public String comprimir(){
            
            String[ ] textoDividido= texto.split("");       // Dividimos nuestro texto letra por letra
            Integer frecuencia= 1;
            ArrayList<NoditosFrecuencia> listaDeNodosPorFrecuencia= new ArrayList<>();
            
            File fileCarpeta= new File(System.getProperty("user.dir") + "\\Datos Huffman");
            if(!fileCarpeta.exists())
                  fileCarpeta.mkdir();
            
            // Configuración de Archivo BinaryCode
            Ficheros ficheroBinCode= new Ficheros(System.getProperty("user.dir") + "\\Datos Huffman\\BinaryCode.binary");
            File fileBinCode= new File(System.getProperty("user.dir") + "\\Datos Huffman\\BinaryCode.binary");
            if(fileBinCode.exists())
                  fileBinCode.delete();
            
            // Configuración de Archivo FrequencyTable
            Ficheros ficheroFrequencyTable= new Ficheros(System.getProperty("user.dir") + "\\Datos Huffman\\FrequencyTable.frecuency");
            File fileFrequencyTable= new File(System.getProperty("user.dir") + "\\Datos Huffman\\FrequencyTable.frecuency");
            if(fileFrequencyTable.exists())
                  fileFrequencyTable.delete();
            
            // Configuración de Archivo FrequencyTree
            Archivo archivo= new Archivo("Datos Huffman\\FrequencyTree.huffman");
            File fileFrequencyTree= new File(System.getProperty("user.dir") + "\\Datos Huffman\\FrequencyTree.huffman");
            if(fileFrequencyTree.exists())
                  fileFrequencyTree.delete();
            
            // Configuración de Archivo BitCodesTable
            Ficheros ficheroBitCodesTable= new Ficheros(System.getProperty("user.dir") + "\\Datos Huffman\\BitCodesTable.bitCode");
            File fileBitCodesTable= new File(System.getProperty("user.dir") + "\\Datos Huffman\\BitCodesTable.bitCode");
            if(fileBitCodesTable.exists())
                  fileBitCodesTable.delete();

            for (int i = 0; i < textoDividido.length; i++) {
                  for (int k = 0; k < textoDividido.length; k++){
                        if (textoDividido[i].equals(textoDividido[k]) && i != k && !letraRepetida(textoDividido[i]))    // Comparamos la frecuencia de cada letra
                              frecuencia++;
                   }
                  if(!letraRepetida(textoDividido[i]))      // Comprobamos que no sean letras repetidas
                        listaDeNodosPorFrecuencia.add(new NoditosFrecuencia(textoDividido[i],frecuencia));
                  letrasRepetidas.add(textoDividido[i]);    // añadimos a nuestro baúl de letras repetidas
                  frecuencia = 1;
                  String binCode= convertirABinario(textoDividido[i]);  // Convertimos a BinaryCode cada letra en orden
                  ficheroBinCode.escribir(binCode, true);   // Escribimos nuestro archivo BinaryCode, letra por letra en orden
            }
            listaDeNodosPorFrecuencia= ordenarArrayListDeFrecuencias(listaDeNodosPorFrecuencia);      // Ordenamos el arrayList por frecuencia
            
            for(int i=0; i<listaDeNodosPorFrecuencia.size(); i++)
                  ficheroFrequencyTable.escribir(listaDeNodosPorFrecuencia.get(i).toString2(), true); // Escribimos nuestro archivo FrequencyTable, ordenados por frecuencia
            ArrayList<NoditosFrecuencia> arbolDeFrecuencias= construirArbolHuffman(listaDeNodosPorFrecuencia);    // Construimos nuestro árbol de frecuencias
            archivo.crearArchivoVacio(true);
            try {
                  archivo.serializar(arbolDeFrecuencias);   // Serializamos nuestro árbol de frecuencias
            } catch (FileNotFoundException ex) {
                  Logger.getLogger(AlgoritmoHuffman.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<String> arrayListBitCodes = obtenerBitCodeLetras();
            for(int i=0; i<arrayListBitCodes.size(); i++)
                  ficheroBitCodesTable.escribir(arrayListBitCodes.get(i), true); // Escribimos nuestro archivo BitCodesTable, ordenados por frecuencia
            return null;
      }
      
      private String arrayListToString(ArrayList<String> listaDeContenidos){
            
            String textoConcatenado= "";
            for(int i=0; i<listaDeContenidos.size(); i++)
                  textoConcatenado+= listaDeContenidos.get(i);
            return textoConcatenado;
      }
      private Boolean letraRepetida(String letra){
            
            for(int i=0; i<letrasRepetidas.size(); i++){
                  if(letra.equals(letrasRepetidas.get(i)))
                        return true;
            }
            return false;
      }

      private String rellenar(String cadena) {
            //Rellenamos la cadena con ceros a la izquierda
            String nueva = "";
            for (int i = 0; i < 8 - cadena.length(); i++)
                  nueva += "0";
            nueva += cadena;
            return nueva;
      }
      private String convertirABinario(String letra){
            String binCode= Integer.toBinaryString(letra.charAt(0));
            if(binCode.length() < 8)
                  binCode= rellenar(binCode);
            return binCode;
      }
      private ArrayList<NoditosFrecuencia> ordenarArrayListDeFrecuencias(ArrayList<NoditosFrecuencia> listaDeNodosPorFrecuencia){
            Collections.sort(listaDeNodosPorFrecuencia, (Object n1, Object n2) -> { // Ordenamiento por las frecuencias de los nodos
                  NoditosFrecuencia auxNodo1 = (NoditosFrecuencia) n1;
                  NoditosFrecuencia auxNodo2 = (NoditosFrecuencia) n2;
                  return auxNodo1.getFrecuencia().compareTo(auxNodo2.getFrecuencia());
            });
            return listaDeNodosPorFrecuencia;
      }
      
      private ArrayList<NoditosFrecuencia> construirArbolHuffman(ArrayList<NoditosFrecuencia> listaDeNodosPorFrecuencia){
            
            while (listaDeNodosPorFrecuencia.size() > 1) {
                  // A continuación sumamos las frecuencias de los primeros 2 nodos del ArrayList
                  Integer sumaFrecuencias = listaDeNodosPorFrecuencia.get(0).getFrecuencia() + listaDeNodosPorFrecuencia.get(1).getFrecuencia();
                  NoditosFrecuencia nodoAux = new NoditosFrecuencia(null, sumaFrecuencias);
                  // Removemos los primeros 2 nodos del ArrayList
                  NoditosFrecuencia n1= listaDeNodosPorFrecuencia.remove(0);
                  NoditosFrecuencia n2= listaDeNodosPorFrecuencia.remove(0);
                  // Configuramos e insertamos los nodos
                  n1.setBitCode(0);
                  n2.setBitCode(1);
                  nodoAux.setIzquierdo(n1);
                  nodoAux.setDerecho(n2);
                  n1.setPadre(nodoAux);
                  n2.setPadre(nodoAux);
                  if (n1.getLetra() != null)
                        referenciasANodosHojas.add(n1);
                  if (n2.getLetra() != null)
                        referenciasANodosHojas.add(n2);
                  listaDeNodosPorFrecuencia.add(nodoAux);
                  listaDeNodosPorFrecuencia= ordenarArrayListDeFrecuencias(listaDeNodosPorFrecuencia);
            }
            return listaDeNodosPorFrecuencia;
      }
      
      private ArrayList<String> obtenerBitCodeLetras(){
            
            NoditosFrecuencia nodo, nodoAux;
            String bitCode= "";
            ArrayList<String> arrayListBitCodes = new ArrayList<>();
            Stack<String> pilaBitCode = new Stack();
            
            if(!referenciasANodosHojas.isEmpty()){
                  System.out.println(referenciasANodosHojas.toString());
                  for(int i=0; i<referenciasANodosHojas.size(); i++){
                        nodo= referenciasANodosHojas.get(i);
                        while(nodo.getPadre() != null){
                              pilaBitCode.push(String.valueOf(nodo.getBitCode()));
                              nodoAux= nodo.getPadre();
                              nodo= nodoAux;
                        }
                        while(pilaBitCode.size() > 0)
                              bitCode+= pilaBitCode.pop();
                        arrayListBitCodes.add(bitCode);
                        bitCode= "";
                  }
            }else
                  arrayListBitCodes.add("No se pueden crear BitCodes para una sóla letra");
            return arrayListBitCodes;
      }
}