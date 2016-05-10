/// <summary>
/// FileStruct
///
/// Ayuda a almacenar la información de las partes (estructura de datos)
/// Autor: Omar Carreon Matricula: A01036074 
///
/// </summary>

//&p-FileStruct
//&b=21
package programa2;
import java.sql.SQLType;
import java.util.Comparator;
/**
 *  Estructura que almacena nombre, número de lineas vacias y número de lineas
 *  con información de los archivos
 * @author Omar Antonio Carreon Medrano A01036074
 */
public class FileStruct {
    public String sFileName, sPartName, SClasificacion, sType; //&m
    public Integer iLinesEmpty, iLinesInfo;
    public Integer iTotal, iItems,iBase,iDeleted,iModified,iAdded;
    
    // Contructor de la clase
    //&i
    public FileStruct(String sPartName, Integer iTotal, Integer iItems, Integer iBase, Integer iDeleted, Integer iModified, Integer iAdded, String sType){ //&m
        this.sPartName = sPartName;
        this.iTotal = iTotal;
        this.iItems = iItems;
        this.iBase = iBase;
        this.iDeleted = iDeleted;
        this.iModified = iModified;
        this.iAdded = iAdded;
        this.sType = sType;
        //&d=2
    }
    //&i
    public Integer getTotal() {
        return iTotal;
    }
    //&i
    public Integer getItems() {
        return iItems;
    }
    //&i
    public Integer getBase() {
        return iBase;
    }
    //&i
    public Integer getDeleted() {
        return iDeleted;
    }
    //&i
    public Integer getModified() {
        return iModified;
    }
    //&i
    public Integer getAdded() {
        return iAdded;
    }
    //&i
    public String getType() {
        return sType;
    }
    // Regresa nombre del archivo
    //&i
    public String getFileName() {
        return sFileName;
    }
    // Regresa numero de lineas vacías
    //&i
    public Integer getLinesEmpty() {
        return iLinesEmpty;
    }
    // Regresa número de lineas con información
    //&i
    public Integer getLinesInfo() {
        return iLinesInfo;
    }
    //&i
    public String getPartName() {
        return sPartName;
    }
    
    // Función para ordenar/comparar a los archivos de menor a mayor de acuerdo
    //  al número de líneas con información de cada uno
    //&i
    public static Comparator<FileStruct> orderByNumberOfLinesWithInfo = new Comparator<FileStruct>() {
        @Override
        public int compare(FileStruct fsObject1, FileStruct fsObject2) {
            Integer iFileObject1 = fsObject1.getLinesInfo();
            Integer iFileObject2 = fsObject2.getLinesInfo();
            return iFileObject1.compareTo(iFileObject2);
        }
    };
}
