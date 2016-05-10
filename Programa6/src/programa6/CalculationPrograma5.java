/*
 * CalculationPrograma5
 *
 * Realiza los cálculos para obtener la distribución t utilizando los valores de
 * x, dof, num_seg, gamma, multiplier, tolerancia de error y ancho de segmentos
 * 
 *
 * @author Omar Antonio Carreón Medrano A01036074
 * @date 17/04/2016
 * @version 1.0
 */
//&p-CalculationPrograma5
//&b=80
package programa6;

public class CalculationPrograma5 {
    
    public static final Double dE = 0.0000000000000001d; //&m 
    public static final Double dPI = 3.1416d; // Valor de pi
    public static Integer iNumSeg = 110; //&m Guarda numero de segmentos
    public static Double dW = 0.0d; // Guarda ancho de segmentos
    public static Integer iMultiplier = 1; // Guarda multiplier
    public static Integer iIndex = 0; // Guarda index que va de 0 a numSeg
    public static Boolean bOld = true; // Indica si ya se realizó el primer calculo de distribucion t
    public static Double dResultPart1 = 0.0d;  // Guarda la primera parte de la distribucion t
    public static Double dResultPart2 = 0.0d; // Guarda la segunda parte de la distribucion t
    public static Double dResultPart3 = 0.0d; // Guarda la tercera parte de la distribucion t
    public static Double dResultDistT1  = 0.0d; // Guarda el valor de la primera distribucion t
    public static Double dDiff = 0.0; // Guarda la diferencia entre el valor actual de distribucion t y el anterior
    
    /*
     * calculateDistT
     * 
     * Calcula la distribucion t
     * 
     * @param dX es el valor <code>double</code> del valor de x que ingresó el usuario
     * @param iDof es el valor <code>integer</code> del valor de dof que ingresó el usuario
     * @return un valor <code>double</code> que representa la distribucion t
     */
    //&i
    public static Double calculateDistT(Double dX, Integer iDof) {
        Double dDistTAcum = 0.0d;
        Double dResultFx = 0.0d;
        
        // Se calcula ancho de segmentos
        
        // Hasta que la diferencia entre el actual y el anterior distribucion t sea menor a 0
        while (dDiff < dE) {
            dW = dX / (iNumSeg * 1.0); 
            iIndex = 0;
            dDistTAcum = 0.0d;
            // Index de 0 a iNumSeg
            while (iIndex <= iNumSeg) {
                dResultPart1 = calculatePart1(iDof);
                dResultPart2 = calculatePart2(iDof);
                dResultPart3 = calculatePart3(iDof);
                dResultFx = calculateFx(dResultPart2,dResultPart3);
                iMultiplier = getMultiplier();
                // Guarda resultado en acumulador
                dDistTAcum = dDistTAcum + (dW / 3.0) * (iMultiplier * 1.0) * dResultFx; //&m
                iIndex = iIndex + 1;
            }
            
            // Si ya se calculó la primera distribucion t
            if (bOld) {
                // Guarda el resultado de la primera distribucion t
                dResultDistT1 = dDistTAcum;
                bOld = false;
                // Incrementa el numero de segmentos
                iNumSeg = iNumSeg * 2;
                // Calcula la segunda distribucion
                calculateDistT(dX, iDof);
            } else {
                // Calcula la diferencia
                dDiff = dResultDistT1 - dDistTAcum;
                iNumSeg = iNumSeg * 2;
                // Convierte el valor en absoluto
                if (dDiff < 0) {
                    dDiff *= -1.0;
                }
            }
        }
        return dDistTAcum;
    }
    
    /*
     * calculatePart1
     * 
     * Calcula la primer parte de la distribucion t que corresponde a
     * 1 + (xi ^ 2 / dof)
     * 
     * @param iDof es el valor <code>integer</code> del valor de dof que ingresó el usuario
     * @return un valor <code>double</code> que representa el resultado de la 
     * primer parte de la distribucion t
     */
    //&i
    public static Double calculatePart1(Integer iDof) {
        Double dResult = 0.0d;
        dResult = 1.0 + (Math.pow((dW * (iIndex * 1.0)), 2.0) / (iDof * 1.0)); //&m
        return dResult;
    }
    
    /*
     * calculatePart2
     * 
     * Calcula la segunda parte de la distribucion t que corresponde a
     * 1 + (xi ^ 2 / dof) ^ -(dof + 1/ 2)
     * 
     * @param iDof es el valor <code>integer</code> del valor de dof que ingresó el usuario
     * @return un valor <code>double</code> que representa el resultado de la 
     * segunda parte de la distribucion t
     */
    //&i
    public static Double calculatePart2(Integer iDof) {
        Double dResult = 0.0d;
        dResult = Math.pow(dResultPart1, -(((iDof + 1) * 1.0) / 2.0)); //&m
        return dResult;
    }
    
    /*
     * calculatePart3
     * 
     * Calcula la tercera parte de la distribucion t que corresponde a
     * gamma(dof + 1 / 2) / ((dof * pi ) ^ 1/2) * (gamma (dof / 2 ) 
     * 
     * @param iDof es el valor <code>integer</code> del valor de dof que ingresó el usuario
     * @return un valor <code>double</code> que representa el resultado de la 
     * tercera parte de la distribucion t
     */
    //&i
    public static Double calculatePart3(Integer iDof) {
        Double dResult = 0.0d;
        Double dResPartial1 = 0.0d;
        Double dResPartial2 = 0.0d;
        Double dResPartial3 = 0.0d;
        dResPartial1 = getGamma((((iDof + 1) * 1.0) / 2.0));
        dResPartial2 = Math.pow((iDof * 1.0) * dPI, 0.5);
        dResPartial3 = getGamma(((iDof * 1.0) / 2.0));
        dResult = dResPartial1 / (dResPartial2 * dResPartial3);
        return dResult;
    }
    
    /*
     * getGamma
     * 
     * Calcula el valor de gamma 
     * 
     * @param dGammaParam es el valor <code>double</code> de lo que se quiere calcular gamma
     * @return un valor <code>double</code> que representa el resultado de gamma
     */
    //&i
    public static Double getGamma(Double dGammaParam) {
        Double dResult = dGammaParam - 1.0;
        Double dIterate = dGammaParam - 1.0;
        for (;dIterate > 0.0;dIterate -= 1.0) {
            if (dIterate == 1.0) {
                dResult = dResult * 1.0;
            } else if (dIterate == 0.5) {
                dResult = dResult * Math.sqrt(dPI);
            } else {
                dResult = dResult * (dIterate - 1.0);
            }
        }
        return dResult;
    }
    
    /*
     * calculateFx
     * 
     * Calcula el valor de Fxi
     * 
     * @param dResultPart2 es el valor <code>double</code> del resultado de la 
     * segunda parte de la distribucion t
     * @param dResultPart3 es el valor <code>double</code> del resultado de la 
     * tercera parte de la distribucion t
     * @return un valor <code>double</code> que representa el resultado de Fxi
     */
    //&i
    public static Double calculateFx(Double dResultPart2, Double dResultPart3) {
        Double dResult = 0.0d;
        dResult = dResultPart2 * dResultPart3;
        return dResult;
    }
    
    /*
     * getMultiplier
     * 
     * Obtiene el multiplier del index
     * 
     * @return un valor <code>integer</code> que representa el multiplier del index
     */
    //&i
    public static Integer getMultiplier() {
        if (iIndex == 0 || iIndex == iNumSeg) {
            return 1;
        } else if (iIndex % 2 == 0) {
            return 2;
        } else {
            return 4;
        }     
    }
}
