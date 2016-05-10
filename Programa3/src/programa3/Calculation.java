/*
 * Calculation
 *
 * Realiza los cálculos de sumatorias, B0,B1, Rxy y Yk
 *
 * @author Omar Antonio Carreón Medrano A01036074
 * @date 28/02/2016
 * @version 1.0
 */
//&p-Calculation
//&b=53
package programa3;

public class Calculation {
    /*
     * sumatoriaXY
     * 
     * Calcula la sumatoria de X * Y de las parejas almacenadas
     * 
     * @param iN es el valor <code>integer</code> del número de parejas almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de X * Y
     */
    //&i
    public static Double sumatoriaXY(Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += Programa3.lstParejas.get(iIndex).getX() * Programa3.lstParejas.get(iIndex).getY();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaX
     * 
     * Calcula la sumatoria de las X de las parejas almacenadas
     * 
     * @param iN es el valor <code>integer</code> del número de parejas almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las X de las parejas almacenadas
     */
    //&i
    public static Double sumatoriaX(Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += Programa3.lstParejas.get(iIndex).getX();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaX2
     * 
     * Calcula la sumatoria de las X al cuadrado de las parejas almacenadas
     * 
     * @param iN es el valor <code>integer</code> del número de parejas almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las X al cuadrado de las parejas almacenadas
     */
    //&i
    public static Double sumatoriaX2(Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += Programa3.lstParejas.get(iIndex).getX() * Programa3.lstParejas.get(iIndex).getX();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaY2
     * 
     * Calcula la sumatoria de las Y al cuadrado de las parejas almacenadas
     * 
     * @param iN es el valor <code>integer</code> del número de parejas almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las Y al cuadrado de las parejas almacenadas
     */
    //&i
    public static Double sumatoriaY2(Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += Programa3.lstParejas.get(iIndex).getY() * Programa3.lstParejas.get(iIndex).getY();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * sumatoriaY
     * 
     * Calcula la sumatoria de las Y de las parejas almacenadas
     * 
     * @param iN es el valor <code>integer</code> del número de parejas almacenadas
     * @return un valor <code>double</code> que representa el valor de la sumatoria de las Y de las parejas almacenadas
     */
    //&i
    public static Double sumatoriaY(Integer iN) {
        Double dSumatoria = 0.0;
        Integer iIndex = 0;
        while(iIndex < iN){
            dSumatoria += Programa3.lstParejas.get(iIndex).getY();
            iIndex++;
        }
        return dSumatoria;
    }
    
    /*
     * calculateB1
     * 
     * Calcula el valor de B1
     * 
     * @param dSumatoriaXY es el valor <code>double</code> de la sumatoria de X * Y
     * @param iN es el valor <code>integer</code> del número de parejas almacenadas
     * @param dPromedioX es el valor <code>double</code> del promedio de las X
     * @param dPromedioY es el valor <code>double</code> del promedio de las Y
     * @param dSumatoriaX2 es el valor <code>double</code> de la sumatoria de las X al cuadrado
     * @return un valor <code>double</code> que representa el cálculo del valor de B1
     */
    //&i
    public static Double calculateB1(Double dSumatoriaXY, Integer iN, Double dPromedioX, Double dPromedioY, Double dSumatoriaX2) {
        Double calculation = 0.0;
        calculation = (dSumatoriaXY- ((iN * 1.0) * dPromedioX * dPromedioY))/(dSumatoriaX2 - ((iN * 1.0) * Math.pow(dPromedioX, 2)));
        return calculation;
    }
    
    /*
     * calculateRxy
     * 
     * Calcula el valor de r xy
     * 
     * @param iN es el valor <code>integer</code> del número de parejas almacenadas
     * @param dSumatoriaXY es el valor <code>double</code> de la sumatoria de X * Y
     * @param dSumatoriaX es el valor <code>double</code> de la sumatoria de las X
     * @param dSumatoriaY es el valor <code>double</code> de la sumatoria de las Y
     * @param dSumatoriaX2 es el valor <code>double</code> de la sumatoria de las X al cuadrado
     * @param dSumatoriaY2 es el valor <code>double</code> de la sumatoria de las Y al cuadrado
     * @return un valor <code>double</code> que representa el cálculo del valor de r xy
     */
    //&i
    public static Double calculateRxy(Integer iN, Double dSumatoriaXY, Double dSumatoriaX, Double dSumatoriaY, Double dSumatoriaX2, Double dSumatoriaY2) {
        Double calculation = 0.0;
        calculation = ((iN * 1.0) * (dSumatoriaXY) - (dSumatoriaX * dSumatoriaY))/(Math.sqrt((((iN * 1.0) * dSumatoriaX2) - Math.pow(dSumatoriaX, 2))* (((iN * 1.0) * dSumatoriaY2) - Math.pow(dSumatoriaY, 2))));
        return calculation;
    }
    
    /*
     * calculateB0
     * 
     * Calcula el valor de B0
     * 
     * @param dPromedioY es el valor <code>double</code> del promedio de las Y
     * @param dB1 es el valor <code>double</code> del valor de B1
     * @param dPromedioX es el valor <code>double</code> del promedio de las X
     * @return un valor <code>double</code> que representa el cálculo del valor de B0
     */
    //&i
    public static Double calculateB0(Double dPromedioY, Double dB1, Double dPromedioX) {
        Double calculation = 0.0;
        calculation = dPromedioY - (dB1 * dPromedioX);
        return calculation;
    }
    
    /*
     * calculateYk
     * 
     * Calcula el valor de yk
     * 
     * @param dB0 es el valor <code>double</code> del valor de B0
     * @param dB1 es el valor <code>double</code> del valor de B1
     * @param iXk es el valor <code>integer</code> del valor de xk
     * @return un valor <code>double</code> que representa el cálculo del valor de yk
     */
    //&i
    public static Double calculateYk(Double dB0, Double dB1, Integer iXk) {
        Double calculation = 0.0;
        calculation = dB0 + (dB1 * (iXk * 1.0));
        return calculation;
    }
}
