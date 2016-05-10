/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//&p-FileHandler
//&b=32
package programa1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Omar Antonio Carreon Medrano A01036074
 */
public class FileHandler {
    
    // Función para abrir archivo
    //&i
    public static File openFile (String sFileName) throws FileNotFoundException{
        return (new File(sFileName)); 
    }
    // Función para contar lineas vacias del archivo
    //&i
    public static Integer countEmptyLines (String sFileName) throws FileNotFoundException, IOException{
        BufferedReader brReader = null;
        String sLine = null;
        File fFile = openFile(sFileName);
        Integer iLinesEmpty = 0;
        
        brReader = new BufferedReader(new FileReader(fFile));
        while ((sLine = brReader.readLine()) != null) {
                if (sLine.trim().isEmpty()){
                    iLinesEmpty+=1;
                }
            }
        
        return iLinesEmpty;
    }
    // Funcion para contar lineas con información del archivo
    //&i
    public static Integer countInfoLines (String sFileName) throws FileNotFoundException, IOException{
        BufferedReader brReader = null;
        String sLine = null;
        File fFile = openFile(sFileName);
        Integer iLinesInfo = 0;
        
        brReader = new BufferedReader(new FileReader(fFile));
        while ((sLine = brReader.readLine()) != null) {
                if (!(sLine.trim().isEmpty())){
                    iLinesInfo+=1;
                }
            }
        
        return iLinesInfo;
    }
}

