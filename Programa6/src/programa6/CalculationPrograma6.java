/*
 * CalculationPrograma6
 *
 * Realiza los cálculos para obtener la distribución t utilizando los valores de
 * x, dof, num_seg, gamma, multiplier, tolerancia de error y ancho de segmentos
 * 
 *
 * @author Omar Antonio Carreón Medrano A01036074
 * @date 17/04/2016
 * @version 1.0
 */
//&p-CalculationPrograma6
package programa6;


public class CalculationPrograma6 {
    /*
     * calculateX
     * 
     * Calcula el valor de x = abs (rxy) sqrt(n-2) / sqrt(1 - rxy) 2 
     * 
     * @param iN es el valor <code>integer</code> del numero de datos
     * @param dRxy es el valor <code>double</code> de la correlacion
     * @return un valor <code>double</code> que representa el valor  de la x
     */
    //&i
    public static Double calculateX(Integer iN, Double dRxy) {
        Double dXSig = 0.0d;
        dXSig = (Math.abs(dRxy) * Math.sqrt((iN - 2.0) * 1.0)) / (Math.sqrt(1.0 - (dRxy * dRxy)));
        return dXSig;
    }
    
    /*
     * calculateTailArea
     * 
     * Calcula el valor del área de la cola de la curva
     * 
     * @param iN es el valor <code>integer</code> del numero de datos
     * @param dXSign es el valor <code>double</code> de la x con la que se calcula la distribucion
     * @return un valor <code>double</code> que representa el área de la cola de la curva
     */
    //&i
    public static Double calculateTailArea(Integer iN, Double dXSign) {
        Double dTailArea = 0.0d;
        Double dDist = 0.0d;
        dDist = CalculationPrograma5.calculateDistT(dXSign, iN - 2);
        dTailArea = 1.0 - 2.0 * dDist;
        return dTailArea;
    }
    
    /*
     * calculateX
     * 
     * Calcula el valor del rango de predicción para un intervalo 70%
     * 
     * @param iN es el valor <code>integer</code> del numero de datos
     * @param dXk es el valor <code>double</code> de xk que se lee del archivo
     * @param dPromedioX es el valor <code>double</code> del promedio de las x
     * @param dB0 es el valor <code>double</code> del parametro b0 
     * @param dB1 es el valor <code>double</code> del parametro b1
     * @return un valor <code>double</code> que representa el valor del rango de prediccion
     */
    //&i
    public static Double calculateRange(Integer iN,Double dXk,Double dPromedioX,Double dB0,Double dB1) {
        Double dRange = 0.0d;
        Double dStdDev = 0.0d;
        Double dAuxSumatoria = 0.0d;
        Double dSecondPartRange = 0.0d;
        Integer iIndex = 0;
        for (iIndex = 0; iIndex < iN; iIndex++){
            dAuxSumatoria += Math.pow((Programa6.lstParejas.get(iIndex).getY() - dB0 - (dB1 * Programa6.lstParejas.get(iIndex).getX())),2);
        }
        
        dStdDev = Math.sqrt((1.0 / ((iN * 1.0) - 2.0)) * dAuxSumatoria);
        dAuxSumatoria = 0.0d;
        for (iIndex = 0; iIndex < iN; iIndex++){
            dAuxSumatoria += Math.pow(Programa6.lstParejas.get(iIndex).getX() - dPromedioX, 2.0);
        }
        
        dSecondPartRange = Math.sqrt(1.0 + (1.0 / (iN * 1.0)) + ((Math.pow((dXk * 1.0) - dPromedioX,2.0))/dAuxSumatoria));
        
        dRange = Programa5.main(iN - 2) * dStdDev * dSecondPartRange;
        return dRange;
    }
    
    /*
     * calculateUPI
     * 
     * Calcula el valor del limite superior 
     *
     * @param dYK es el valor <code>double</code> de la prediccion mejorada
     * @param dRange es el valor <code>double</code> del rango de prediccion
     * @return un valor <code>double</code> que representa el limite superior
     */
    //&i
    public static Double calculateUPI(Double dYK,Double dRange) {
        Double dUPI = 0.0d;
        dUPI = dYK + dRange;
        return dUPI;
    }
    
    /*
     * calculateLPI
     * 
     * Calcula el valor del limite inferior 
     *
     * @param dYK es el valor <code>double</code> de la prediccion mejorada
     * @param dRange es el valor <code>double</code> del rango de prediccion
     * @return un valor <code>double</code> que representa el limite inferior
     */
    //&i
    public static Double calculateLPI(Double dYK,Double dRange) {
        Double dLPI = 0.0d;
        dLPI = dYK - dRange;
        if (dLPI < 0.0){
            dLPI = 0.0;
        }
        return dLPI;
    }
}
