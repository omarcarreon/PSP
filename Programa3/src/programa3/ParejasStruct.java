/*
 * ParejasStruct
 *
 * Estructura para almacenar los valores de X y Y de los pares que se leen del
 * archivo.
 *
 * @author Omar Antonio Carreón Medrano A01036074
 * @date 28/02/2016
 * @version 1.0
 */
//&p-ParejasStruct
//&b=14
package programa3;

import java.util.List;

public class ParejasStruct {
    
    
    public Double dParejaX;
    public Double dParejaY;
    
    /*
     * ParejasStruct
     * 
     * Constructor de la clase ParejasStruct
     * 
     * @param dParejaX es el valor <code>double</code> de la x de la pareja.
     * @param dParejaY es el valor <code>double</code> de la y de la pareja.
     */
    //&i
    public ParejasStruct(Double dParejaX, Double dParejaY){
        this.dParejaX = dParejaX;
        this.dParejaY = dParejaY;
    }
    
    /*
     * getX
     * 
     * Regresa el valor de X de la pareja
     * 
     * @return un valor <code>double</code> que representa el valor de x de la pareja
     */
    //&i
    public Double getX(){
        return dParejaX;
    }
    
    /*
     * getY
     * 
     * Regresa el valor de Y de la pareja
     * 
     * @return un valor <code>double</code> que representa el valor de y de la pareja
     */
    //&i
    public Double getY(){
        return dParejaY;
    }
    
    /*
     * getLength
     * 
     * Regresa el número de parejas que se almacenaron
     * 
     * @return un valor <code>integer</code> que representa el número de parejas que se almacenaron
     */
    //&i
    public static Integer getLength(List<ParejasStruct> lstParejas){
        return lstParejas.size();
    }
}
