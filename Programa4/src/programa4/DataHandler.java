/*
 * DataHandler
 *
 * Almacena en una estructura los valores de x y dof que el usuario tecleó , y 
 * el resultado de la distribución t. Contiene función para imprimir los valores
 * calculados con 5 decimales redondeados hacia arriba en su último dígito 
 * (solo en caso de tener decimales).
 *
 * @author Omar Antonio Carreón Medrano A01036074
 * @date 15/03/2016
 * @version 1.0
 */
//&p-DataHandler
package programa4;

import java.util.Locale;


public class DataHandler {
    
    public Double dX;
    public Integer iDof;
    public Double dDistT;
    
    /*
     * DataHandler
     * 
     * Constructor de la clase DataHandler
     * 
     * @param dX es el valor <code>double</code> de la x que tecleó el usuario
     * @param iDof es el valor <code>integer</code> de dof que tecleó el usuario
     * @param dDistT es el valor <code>double</code> de la distribución t
     */
    //&i
    public DataHandler(Double dX, Integer iDof, Double dDistT) {
        this.dX = dX;
        this.iDof = iDof;
        this.dDistT = dDistT;
    }
    
    /*
     * getX
     * 
     * Regresa el valor de la x que tecleó el usuario
     * 
     * @return un valor <code>double</code> que representa la x que tecleó 
     * el usuario
     */
    //&i
    public Double getX() {
        return dX;
    }
    
    /*
     * getDof
     * 
     * Regresa el valor de dof que tecleó el usuario
     * 
     * @return un valor <code>integer</code> que representa el valor de dof 
     * que tecleó el usuario 
     */
    //&i
    public Integer getDof() {
        return iDof;
    }
    
    /*
     * getDistT
     * 
     * Regresa el valor de distribución t que se calculó
     * 
     * @return un valor <code>double</code> que representa el valor de la 
     * distribución t 
     */
    //&i
    public Double getDistT() {
        return dDistT;
    }
    
    /*
     * printData
     * 
     * Despliega en pantalla los valores de x, dof y distribución t con
     * 5 decimales redondeados hacia arriba en su último dígito (solo en caso de 
     * tener decimales).
     * 
     * sin @return
     */
    //&i
    public static void printData() {
        System.out.printf(Locale.US, "  x  = %.5f \n",Programa4.lstData.get(0).getX());
        System.out.printf(Locale.US, "dof  = %d \n",Programa4.lstData.get(0).getDof());
        System.out.printf(Locale.US, "  p  = %.5f \n",Programa4.lstData.get(0).getDistT());
    }
}
