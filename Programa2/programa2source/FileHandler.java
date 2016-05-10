/// <summary>
/// FileHandler
///
/// Ayuda a abrir el archivo, contar lineas vacías y contar lineas con
/// información
/// Autor: Omar Carreon Matricula: A01036074 
///
/// </summary>


//&p-FileHandler
//&b=32
package programa2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Omar Antonio Carreon Medrano A01036074
 */
public class FileHandler {
    
    /*
    * FileHandler
    *
    *  Abre el archivo
    *  
    *  @param sFileName es el valor <code>String</code> del nombre de archivo
    *  @return un valor <code>File</code> que representa al archivo
    */
    //&i
    public static File openFile (String sFileName) throws FileNotFoundException{
        return (new File(sFileName)); 
    }
    
    public static void writeFile(List<String> lstOutput) throws FileNotFoundException, UnsupportedEncodingException {
        int iCounterOutput = 0;
        try (PrintWriter writer = new PrintWriter("ConteoLDC.txt", "UTF-8")) {
            while (iCounterOutput < lstOutput.size()) {
                writer.write(lstOutput.get(iCounterOutput));
                
                iCounterOutput++;
            }
        }
        
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

