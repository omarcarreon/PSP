/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//&p-Programa1
//&b=47
package programa1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar Antonio Carreon Medrano A01036074
 */
public class Programa1 {

    /**
     * @param args the command line arguments
     */
    //&i
    public static void main(String[] args) throws IOException {
        // Inicialización de scanner para input del usuario
        Scanner scanner = new Scanner(System.in);
        // Inicialización de variable para respuesta del usuario 
        String sAnswer = "no";
        // Variables para contar el total de archivos, lineas vacias y lineas con info
        Integer iFileCounter = 0, iTotalLinesEmpty = 0, iTotalLinesInfo = 0;
        // Lista de objeto FileStruct para guardar la info de los archivos
        List<FileStruct> lFileObjects = new ArrayList<FileStruct>();
        
        do{ 
            
            System.out.println("Ingresa nombre de archivo: ");
            String sFileName = scanner.nextLine();
            // Abre archivo
            File fFile = FileHandler.openFile(sFileName);
            // Variables para contar lineas vacias y con info de cada archivo
            Integer iLinesEmpty = 0, iLinesInfo = 0;
         
            try {
                // Incrementa en 1 el contador de archivos
                iFileCounter += 1;
                // Obtiene lineas vacias de archivo
                iLinesEmpty = FileHandler.countEmptyLines(sFileName);
                // Obtiene lineas con información de archivo
                iLinesInfo = FileHandler.countInfoLines(sFileName);
                // Agrega en la estructura FileStruct la información del archivo
                lFileObjects.add(new FileStruct(sFileName,iLinesEmpty,iLinesInfo));
                
            } catch (FileNotFoundException ex) {
               System.out.println("El archivo no existe. Ingresa otro nombre de archivo.");
               iFileCounter -= 1;
            }
            System.out.println("Otro archivo? si/no ");
            sAnswer = scanner.nextLine();
            // mientras el usuario responda que si
        } while ("si".equals(sAnswer));
        // Ordena estructura FileStruct de acuerdo a número de lineas de los archivos
        Collections.sort(lFileObjects, FileStruct.orderByNumberOfLinesWithInfo);
        // Despliega ordenados los archivos y su info
        for (Integer iCounter=0; iCounter<iFileCounter;iCounter++){
            System.out.println("Nombre del archivo: "+ lFileObjects.get(iCounter).getFileName());
            System.out.println("Cantidad de líneas en blanco: " + lFileObjects.get(iCounter).getLinesEmpty());
            System.out.println("Cantidad de líneas con información: " + lFileObjects.get(iCounter).getLinesInfo());
            System.out.println("---------------------------------------------");
            iTotalLinesEmpty += lFileObjects.get(iCounter).getLinesEmpty();
            iTotalLinesInfo += lFileObjects.get(iCounter).getLinesInfo();
        }
        // Despliega los totales
        System.out.println("TOTALES: ");
        System.out.println("Cantidad de archivos: "+ iFileCounter);
        System.out.println("Cantidad total de líneas en blanco: " + iTotalLinesEmpty);
        System.out.println("Cantidad total de líneas con información: " + iTotalLinesInfo);
    }
    
}
