/// <summary>
/// CounterLDC
///
/// Maneja el conteo de lineas validas, nombre de las partes y conteo de lineas
/// base, modificadas, borradas, el tipo y el nombre de la parte 
/// Autor: Omar Carreon Matricula: A01036074 
///
/// </summary>

//&p-CounterLDC
package programa2;

/**
 *
 * @author omarcarreon
 */
public class CounterLDC {
    public static Boolean bFoundTagP = false;
    public static Boolean bFoundMultipleOpen = false;
    public static Boolean bFoundMultipleClose = false;
    public static Integer iCountMultiple = 0;
    public static Integer iCountLDCPart = 0;
    
    /// <summary>
    /// countLDCTotal
    /// Cuenta lineas totales validas
    /// <param name="sLine"> Linea a revisar </param>
    /// <returns>Regresa una variable incrementada si linea es valida </returns>
    /// </summary>
    //&i
    public static Integer countLDCTotal(String sLine){
        int iLdc = 0;
        if (sLine.trim().startsWith("//&p")) {
            bFoundTagP = true;
        }
        if (sLine.trim().startsWith("/*") && sLine.trim().endsWith("*/")) {
            bFoundMultipleOpen = false;
            bFoundMultipleClose = false;
        } else if (!sLine.trim().startsWith("/*") && sLine.trim().endsWith("*/")) {
            bFoundMultipleClose = true;
        } else if (sLine.trim().startsWith("/*")){
            bFoundMultipleOpen = true; 
        }
        if ("".equals(sLine.trim()) || 
            sLine.trim().startsWith("//") ||
            sLine.trim().startsWith("//&") ||
            sLine.trim().startsWith("/*") ||
            sLine.trim().endsWith("*/") ||
            sLine.trim().startsWith("///") ||
            sLine.trim().startsWith("*") ||
            "{".equals(sLine.trim()) ||
            "}".equals(sLine.trim())) {
            // ignora la linea
        } else {
            if (bFoundMultipleOpen && !bFoundMultipleClose){
                iCountMultiple = iCountMultiple + 1;
            }
            iLdc++;
        }
        if (sLine.trim().startsWith("/*") && sLine.trim().endsWith("*/")) {
            iCountMultiple=0;
        }
        return iLdc;
    }
    /// <summary>
    /// getPartName
    /// Obtiene nombre de la parte
    /// <param name="sLine"> Linea a revisar </param>
    /// <returns>Regresa el nombre d la parte </returns>
    /// </summary>
    //&i
    public static String getPartName(String sLine) {
        String sPartName="";
        if (sLine.trim().startsWith("//&p-")) {
            sPartName = sLine.trim().substring(5);
        }
        return sPartName;
    }
    /// <summary>
    /// countItems
    /// Cuenta los items en la parte
    /// <param name="sLine"> Linea a revisar </param>
    /// <returns>Regresa variable incrementada si encuentra tag i </returns>
    /// </summary>
    //&i
    public static Integer countItems(String sLine){
        int iItems = 0;
        if (sLine.trim().startsWith("//&i")) {
            iItems++;
        }
        return iItems;
    }
    /// <summary>
    /// countBase
    /// Obtiene valor de tag base
    /// <param name="sLine"> Linea a revisar </param>
    /// <returns>Regresa el valor del tag base</returns>
    /// </summary>
    //&i
    public static Integer countBase(String sLine){
        int iBase = 0;
        if (sLine.trim().startsWith("//&b=")) {
            iBase = Integer.parseInt(sLine.trim().substring(5));
        }
        return iBase;
    }
    /// <summary>
    /// countDeleted
    /// Obtiene valor de tag deleted
    /// <param name="sLine"> Linea a revisar </param>
    /// <returns>Regresa valor del tag deleted </returns>
    /// </summary>
    //&i
    public static Integer countDeleted(String sLine){
        int iDeleted = 0;
        if (sLine.trim().startsWith("//&d=")) {
            iDeleted = Integer.parseInt(sLine.trim().substring(5));
        }
        return iDeleted;
    }
    /// <summary>
    /// countModified
    /// Cuenta tags modified
    /// <param name="sLine"> Linea a revisar </param>
    /// <returns>Regresa variable incrementada si es tag modified</returns>
    /// </summary>
    //&i
    public static Integer countModified(String sLine){
        int iModified = 0;
        if (sLine.trim().endsWith("//&m")) {
            iModified++;
        }
        return iModified;
    }
    /// <summary>
    /// getType
    /// Calcula tipo de la parte
    /// <param name="iBase"> numero de base </param>
    /// <param name="iModified"> numero de lineas modificadas </param>
    /// <param name="iDeleted"> numero de lineas borradas </param>
    /// <param name="iAdded"> numero de lineas agregadas </param>
    /// <returns>Tipo de la parte</returns>
    /// </summary>
    //&i
    public static String getType(Integer iBase, Integer iModified, Integer iDeleted, Integer iAdded) {
        String sType="";
        
        if (iBase > 0 && (iModified > 0 || iDeleted > 0 || iAdded > 0)){
            sType = "BASE";
        } else if (iBase == 0 && iModified == 0 && iDeleted == 0 && iAdded > 0) {
            sType = "NUEVA";
        } else if (iBase>0 && iModified == 0 && iDeleted == 0 && iAdded == 0) {
            sType = "REUSADA";
        } else {
            sType = "error";
            System.out.println("error");
        }
        return sType;
    }
}
