/*
 * CuadruplosStruct
 *
 * Estructura para almacenar los valores de W, X , Y y Z de los cuadruplos 
 * que se leen del archivo.
 *
 * @author Omar Antonio Carreón Medrano A01036074
 * @date 26/04/2016
 * @version 1.0
 */
//&p-CuadruplosStruct
//&b=14
package programa7;

import java.util.List;

public class CuadruplosStruct { //&m
    
    public Double dCuadruploW;
    public Double dCuadruploX; //&m
    public Double dCuadruploY; //&m
    public Double dCuadruploZ;
    
    /*
     * CuadruplosStruct
     * 
     * Constructor de la clase CuadruplosStruct
     * 
     * @param dCuadruploW es el valor <code>double</code> de la w del cuadruplo.
     * @param dCuadruploX es el valor <code>double</code> de la x del cuadruplo.
     * @param dCuadruploY es el valor <code>double</code> de la y del cuadruplo.
     * @param dCuadruploZ es el valor <code>double</code> de la z del cuadruplo.
     */
    //&i
    public CuadruplosStruct(Double dCuadruploW, Double dCuadruploX, Double dCuadruploY, Double dCuadruploZ){ //&m
        this.dCuadruploW = dCuadruploW;
        this.dCuadruploX = dCuadruploX; //&m
        this.dCuadruploY = dCuadruploY; //&m
        this.dCuadruploZ = dCuadruploZ;
    }
    
    /*
     * getW
     * 
     * Regresa el valor de W del cuadruplo
     * 
     * @return un valor <code>double</code> que representa el valor de W del cuadruplo
     */
    //&i
    public Double getW(){
        return dCuadruploW; 
    }
    
    /*
     * getX
     * 
     * Regresa el valor de X del cuadruplo
     * 
     * @return un valor <code>double</code> que representa el valor de x del cuadruplo
     */
    //&i
    public Double getX(){
        return dCuadruploX; //&m
    }
    
    /*
     * getY
     * 
     * Regresa el valor de Y del cuadruplo
     * 
     * @return un valor <code>double</code> que representa el valor de y del cuadruplo
     */
    //&i
    public Double getY(){
        return dCuadruploY; //&m
    }
    
    /*
     * getZ
     * 
     * Regresa el valor de Z del cuadruplo
     * 
     * @return un valor <code>double</code> que representa el valor de Z del cuadruplo
     */
    //&i
    public Double getZ(){
        return dCuadruploZ;
    }
    
    /*
     * getLength
     * 
     * Regresa el número de cuadruplos que se almacenaron
     * 
     * @return un valor <code>integer</code> que representa el número de cuadruplos que se almacenaron
     */
    //&i
    public static Integer getLength(List<CuadruplosStruct> lstCuadruplos){ //&m
        return lstCuadruplos.size(); //&m
    }
}
