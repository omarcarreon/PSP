/*
 * FileHandler
 *
 * Ayuda a abrir el archivo, escribir en el archivo, contar lineas vacías y 
 * contar lineas con información
 * 
 * @author Omar Antonio Carreón Medrano A01036074
 * @date 26/04/2016
 * @version 1.0
 */
//&p-FileHandler
//&b=40
package programa7;

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

public class FileHandler {
    
    /*
     * openFile
     *
     * Abre el archivo
     *  
     * @param sFileName es el valor <code>String</code> del nombre de archivo
     * @return un valor <code>File</code> que representa al archivo
     * @exception <code>FileNotFoundException</code> representa que el archivo no existe
     */
    //&i
    public static File openFile (String sFileName) throws FileNotFoundException{
        return (new File(sFileName)); 
    }
    
    /*
     * writeFile
     *
     * Escribe en el archivo
     *  
     * @param lstOutput es el valor <code>List<String></code> de lo que se escribirá en el archivo
     * @exception <code>FileNotFoundException</code> representa que el archivo no se encontró 
     * @exception <code>UnsupportedEncodingException</code> representa que el caracter de encoding no es soportado
     */
    //&i
    public static void writeFile(List<String> lstOutput) throws FileNotFoundException, UnsupportedEncodingException {
        int iCounterOutput = 0;
        try (PrintWriter writer = new PrintWriter("ConteoLDC.txt", "UTF-8")) {
            while (iCounterOutput < lstOutput.size()) {
                writer.write(lstOutput.get(iCounterOutput));
                
                iCounterOutput++;
            }
        }
        
    }
    
    /*
     * countEmptyLines
     *
     * Cuenta líneas vacías
     *  
     * @param sFileName es el valor <code>String</code> del nombre del archivo
     * @return un valor <code>integer</code> que representa el número de líneas vacías
     * @exception <code>FileNotFoundException</code> representa que el archivo no se encontró 
     * @exception <code>IOException</code> representa error de I/O
     */
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
    /*
     * countInfoLines
     *
     * Cuenta líneas con información
     *  
     * @param sFileName es el valor <code>String</code> del nombre del archivo
     * @return un valor <code>integer</code> que representa el número de líneas con información
     * @exception <code>FileNotFoundException</code> representa que el archivo no se encontró 
     * @exception <code>IOException</code> representa error de I/O
     */
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

