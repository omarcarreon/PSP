/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa1;
import java.util.Comparator;
/**
 *  Estructura que almacena nombre, número de lineas vacias y número de lineas
 *  con información de los archivos
 * @author Omar Antonio Carreon Medrano A01036074
 */
public class FileStruct {
    public String sFileName;
    public Integer iLinesEmpty, iLinesInfo;
    // Contructor de la clase
    public FileStruct(String sFileName, Integer iLinesEmpty, Integer iLinesInfo){
        this.sFileName = sFileName;
        this.iLinesEmpty = iLinesEmpty;
        this.iLinesInfo = iLinesInfo;
    }
    // Regresa nombre del archivo
    public String getFileName() {
        return sFileName;
    }
    // Regresa numero de lineas vacías
    public Integer getLinesEmpty() {
        return iLinesEmpty;
    }
    // Regresa número de lineas con información
    public Integer getLinesInfo() {
        return iLinesInfo;
    }
    // Función para ordenar/comparar a los archivos de menor a mayor de acuerdo
    //  al número de líneas con información de cada uno
    public static Comparator<FileStruct> orderByNumberOfLinesWithInfo = new Comparator<FileStruct>() {
        @Override
        public int compare(FileStruct fsObject1, FileStruct fsObject2) {
            Integer iFileObject1 = fsObject1.getLinesInfo();
            Integer iFileObject2 = fsObject2.getLinesInfo();
            return iFileObject1.compareTo(iFileObject2);
        }
    };
}
