/*
 * Calculation
 *
 * Calcula los valores de las equaciones lineares, manda a llamar el metodo
 * de Gauss para diagonalizar la matriz y resolver los parametros de regresion multiple
 *
 * @author Omar Antonio Carreón Medrano A01036074
 * @date 26/04/2016
 * @version 1.0
 */
//&p-Calculation
package programa7;

import java.util.List;

/**
 *
 * @author omarcarreon
 */
public class Calculation {
    
    public static Double[][] matLinearEq = new Double[4][5];
    
    public static Double dB0 = 0.0d;
    public static Double dB1 = 0.0d;
    public static Double dB2 = 0.0d;
    public static Double dB3 = 0.0d;
    
    /*
     * multipleRegression
     * 
     * Calcula los valores de las equaciones lineares, manda a llamar el metodo
     * de Gauss para diagonalizar la matriz y resolver los parametros de regresion multiple
     * 
     * @param lstCuadruplos es el valor <code>List<CuadruplosStruct></code> que contiene la lista de los cuadruplos
     * @param iN es el valor <code>integer</code> del número de cuadruplos almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las W de los cruadruplos almacenados
     */
    //&i
    public static void multipleRegression(List<CuadruplosStruct> lstCuadruplos, Integer iN){
        
        matLinearEq[0][0] = iN * 1.0;
        matLinearEq[0][1] = sumatoriaW(lstCuadruplos, iN);
        matLinearEq[0][2] = sumatoriaX(lstCuadruplos, iN);
        matLinearEq[0][3] = sumatoriaY(lstCuadruplos, iN);
        matLinearEq[0][4] = sumatoriaZ(lstCuadruplos, iN);
        matLinearEq[1][0] = sumatoriaW(lstCuadruplos, iN);
        matLinearEq[1][1] = sumatoriaW2(lstCuadruplos, iN);
        matLinearEq[1][2] = sumatoriaWX(lstCuadruplos, iN);
        matLinearEq[1][3] = sumatoriaWY(lstCuadruplos, iN);
        matLinearEq[1][4] = sumatoriaWZ(lstCuadruplos, iN);
        matLinearEq[2][0] = sumatoriaX(lstCuadruplos, iN);
        matLinearEq[2][1] = sumatoriaWX(lstCuadruplos, iN);
        matLinearEq[2][2] = sumatoriaX2(lstCuadruplos, iN);
        matLinearEq[2][3] = sumatoriaXY(lstCuadruplos, iN);
        matLinearEq[2][4] = sumatoriaXZ(lstCuadruplos, iN);
        matLinearEq[3][0] = sumatoriaY(lstCuadruplos, iN);
        matLinearEq[3][1] = sumatoriaWY(lstCuadruplos, iN);
        matLinearEq[3][2] = sumatoriaXY(lstCuadruplos, iN);
        matLinearEq[3][3] = sumatoriaY2(lstCuadruplos, iN);
        matLinearEq[3][4] = sumatoriaYZ(lstCuadruplos, iN);
        
        gaussMethod();
        
        dB3 = matLinearEq[3][4] / matLinearEq[3][3];
        dB2 = (matLinearEq[2][4] - (matLinearEq[2][3] * dB3)) / matLinearEq[2][2];
        dB1 = (matLinearEq[1][4] - (matLinearEq[1][3] * dB3) - (matLinearEq[1][2] * dB2)) / matLinearEq[1][1];
        dB0 = (matLinearEq[0][4] - (matLinearEq[0][3] * dB3) - (matLinearEq[0][2] * dB2) - (matLinearEq[0][1] * dB1)) / matLinearEq[0][0];
        
    }
    
    /*
     * sumatoriaW
     * 
     * Calcula la sumatoria de las W de los cuadruplos almacenados
     * 
     * @param lstCuadruplos es el valor <code>List<CuadruplosStruct></code> que contiene la lista de los cuadruplos
     * @param iN es el valor <code>integer</code> del número de cuadruplos almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las W de los cruadruplos almacenados
     */
    //&i
    public static Double sumatoriaW(List<CuadruplosStruct> lstCuadruplos, Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += lstCuadruplos.get(iIndex).getW();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaX
     * 
     * Calcula la sumatoria de las X de los cuadruplos almacenados
     * 
     * @param lstCuadruplos es el valor <code>List<CuadruplosStruct></code> que contiene la lista de los cuadruplos
     * @param iN es el valor <code>integer</code> del número de cuadruplos almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las X de los cruadruplos almacenados
     */
    //&i
    public static Double sumatoriaX(List<CuadruplosStruct> lstCuadruplos, Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += lstCuadruplos.get(iIndex).getX();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaY
     * 
     * Calcula la sumatoria de las Y de los cuadruplos almacenados
     * 
     * @param lstCuadruplos es el valor <code>List<CuadruplosStruct></code> que contiene la lista de los cuadruplos
     * @param iN es el valor <code>integer</code> del número de cuadruplos almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las Y de los cruadruplos almacenados
     */
    //&i
    public static Double sumatoriaY(List<CuadruplosStruct> lstCuadruplos, Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += lstCuadruplos.get(iIndex).getY();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaZ
     * 
     * Calcula la sumatoria de las Z de los cuadruplos almacenados
     * 
     * @param lstCuadruplos es el valor <code>List<CuadruplosStruct></code> que contiene la lista de los cuadruplos
     * @param iN es el valor <code>integer</code> del número de cuadruplos almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las Z de los cruadruplos almacenados
     */
    //&i
    public static Double sumatoriaZ(List<CuadruplosStruct> lstCuadruplos, Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += lstCuadruplos.get(iIndex).getZ();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaW2
     * 
     * Calcula la sumatoria de las W al cuadrado de los cuadruplos almacenados
     * 
     * @param lstCuadruplos es el valor <code>List<CuadruplosStruct></code> que contiene la lista de los cuadruplos
     * @param iN es el valor <code>integer</code> del número de cuadruplos almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las W al cuadrado de los cruadruplos almacenados
     */
    //&i
    public static Double sumatoriaW2(List<CuadruplosStruct> lstCuadruplos, Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += lstCuadruplos.get(iIndex).getW() * lstCuadruplos.get(iIndex).getW();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaWX
     * 
     * Calcula la sumatoria de las W * X de los cuadruplos almacenados
     * 
     * @param lstCuadruplos es el valor <code>List<CuadruplosStruct></code> que contiene la lista de los cuadruplos
     * @param iN es el valor <code>integer</code> del número de cuadruplos almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las W * X de los cruadruplos almacenados
     */
    //&i
    public static Double sumatoriaWX(List<CuadruplosStruct> lstCuadruplos, Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += lstCuadruplos.get(iIndex).getW() * lstCuadruplos.get(iIndex).getX();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaWY
     * 
     * Calcula la sumatoria de las W * Y de los cuadruplos almacenados
     * 
     * @param lstCuadruplos es el valor <code>List<CuadruplosStruct></code> que contiene la lista de los cuadruplos
     * @param iN es el valor <code>integer</code> del número de cuadruplos almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las W * Y de los cruadruplos almacenados
     */
    //&i
    public static Double sumatoriaWY(List<CuadruplosStruct> lstCuadruplos, Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += lstCuadruplos.get(iIndex).getW() * lstCuadruplos.get(iIndex).getY();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaWZ
     * 
     * Calcula la sumatoria de las W * Z de los cuadruplos almacenados
     * 
     * @param lstCuadruplos es el valor <code>List<CuadruplosStruct></code> que contiene la lista de los cuadruplos
     * @param iN es el valor <code>integer</code> del número de cuadruplos almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las W * Z de los cruadruplos almacenados
     */
    //&i
    public static Double sumatoriaWZ(List<CuadruplosStruct> lstCuadruplos, Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += lstCuadruplos.get(iIndex).getW() * lstCuadruplos.get(iIndex).getZ();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaX2
     * 
     * Calcula la sumatoria de las X al cuadrado de los cuadruplos almacenados
     * 
     * @param lstCuadruplos es el valor <code>List<CuadruplosStruct></code> que contiene la lista de los cuadruplos
     * @param iN es el valor <code>integer</code> del número de cuadruplos almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las X al cuadrado de los cruadruplos almacenados
     */
    //&i
    public static Double sumatoriaX2(List<CuadruplosStruct> lstCuadruplos, Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += lstCuadruplos.get(iIndex).getX() * lstCuadruplos.get(iIndex).getX();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaXY
     * 
     * Calcula la sumatoria de las X * Y de los cuadruplos almacenados
     * 
     * @param lstCuadruplos es el valor <code>List<CuadruplosStruct></code> que contiene la lista de los cuadruplos
     * @param iN es el valor <code>integer</code> del número de cuadruplos almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las X * Y de los cruadruplos almacenados
     */
    //&i
    public static Double sumatoriaXY(List<CuadruplosStruct> lstCuadruplos, Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += lstCuadruplos.get(iIndex).getX() * lstCuadruplos.get(iIndex).getY();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaXZ
     * 
     * Calcula la sumatoria de las X * Z de los cuadruplos almacenados
     * 
     * @param lstCuadruplos es el valor <code>List<CuadruplosStruct></code> que contiene la lista de los cuadruplos
     * @param iN es el valor <code>integer</code> del número de cuadruplos almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las X * Z de los cruadruplos almacenados
     */
    //&i
    public static Double sumatoriaXZ(List<CuadruplosStruct> lstCuadruplos, Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += lstCuadruplos.get(iIndex).getX() * lstCuadruplos.get(iIndex).getZ();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaY2
     * 
     * Calcula la sumatoria de las Y al cuadrado de los cuadruplos almacenados
     *
     * @param lstCuadruplos es el valor <code>List<CuadruplosStruct></code> que contiene la lista de los cuadruplos
     * @param iN es el valor <code>integer</code> del número de cuadruplos almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las Y al cuadrado de los cruadruplos almacenados
     */
    //&i
    public static Double sumatoriaY2(List<CuadruplosStruct> lstCuadruplos, Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += lstCuadruplos.get(iIndex).getY() * lstCuadruplos.get(iIndex).getY();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaYZ
     * 
     * Calcula la sumatoria de las Y * Z de los cuadruplos almacenados
     * 
     * @param lstCuadruplos es el valor <code>List<CuadruplosStruct></code> que contiene la lista de los cuadruplos
     * @param iN es el valor <code>integer</code> del número de cuadruplos almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las Y * Z de los cruadruplos almacenados
     */
    //&i
    public static Double sumatoriaYZ(List<CuadruplosStruct> lstCuadruplos, Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += lstCuadruplos.get(iIndex).getY() * lstCuadruplos.get(iIndex).getZ();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * gaussMethod
     * 
     * Resuelve la matriz de ecuaciones lineales utilizando el metodo de gauss
     * 
     * Sin @return
     */
    //&i
    public static void gaussMethod(){
        Integer iI = 1;
        Integer iK = 1;
        for (iK = 0;iK < 4-1;iK++){
            for (iI = iK + 1; iI < 4; iI++){
                Double dZ = matLinearEq[iI][iK] / matLinearEq[iK][iK];
                matLinearEq[iI][iK] = 0.0;
                for (Integer iJ=iK + 1;iJ < 5;iJ++){
                    
                    matLinearEq[iI][iJ] = matLinearEq[iI][iJ] - (matLinearEq[iK][iJ] * dZ);
                }
            }
            
        }
        
    }
}
