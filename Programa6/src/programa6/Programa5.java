/*
 * Programa5
 *
 * Pide al usuario el valor de p. Llama a la función para calcular la 
 * distribución t y compara resultado con la p que se ingresó. Ajusta valor 
 * de x y d de acuerdo al resultado y vuelve a integrar hasta que se encuentre 
 * una x que de una distribución dentro de la tolerancia de error. Guarda valor 
 * de p, dof y x en DataHandler. Llama a función printData para imprimir los 
 * resultados.
 
 * @author Omar Antonio Carreón Medrano A01036074
 * @date 17/04/2016
 * @version 1.0
 */
//&p-Programa5
//&b=51
package programa6; //&m

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Programa5 {

    // List para almacenar el valor de x, dof y distribucion t en DataHandler
    //&d=1
    /*
     * main
     * 
     * Pide al usuario el valor de p. Llama a la función para calcular la 
     * distribución t y compara resultado con la p que se ingresó. Ajusta valor 
     * de x y d de acuerdo al resultado y vuelve a integrar hasta que se encuentre 
     * una x que de una distribución dentro de la tolerancia de error. Guarda valor 
     * de p, dof y x en DataHandler. Llama a función printData para imprimir los 
     * resultados.
     * 
     * @param args the command line arguments
     */
    //&i
    public static Double main(Integer iDofParam) { //&m
        //&d=2
        Double dP = 0.35; //&m
        Integer iDof = iDofParam ; //&m
        Double dDistT = 0.0d;
        Double dX = 1.0d;
        Double dD = 0.5d;
        Double dPrimerDiff = 0.0d;
        Double dSegundaDiff = 0.0d;

        //&d=8
        if (dX == 0.0) { //&m
            dP = 0.0; //&m
        } else {
            
            // Calcula distribucion t
            dDistT = CalculationPrograma5.calculateDistT(dX, iDof); //&m
            // Guarda la diferencia entre la distribucion calculada y la ingresada
            dPrimerDiff = dDistT - dP;
            
            dSegundaDiff = dPrimerDiff;
            // Mientras que no este dentro de la tolerancia de error
            while (Math.abs(dPrimerDiff) >= CalculationPrograma5.dE) { //&m
                // Si distribucion calculada es menor a la esperada
                if (dPrimerDiff < 0.0) {
                    dX += dD; // Suma d a x
                } else {
                    dX -= dD;
                }
                // Verifica si hubo cambio de signo entre la diferencia entre valor actual y anterior de distribucion
                if ((dSegundaDiff > 0.0 && dPrimerDiff < 0.0) ||  (dSegundaDiff < 0.0 && dPrimerDiff > 0.0)){
                    dD /= 2.0; // si cambio el signo, se divide d / 2
                }

                CalculationPrograma5.iNumSeg = 110; //&m Reinicia numero de segmentos
                CalculationPrograma5.iMultiplier = 1; //&m Reinicia multiplier
                CalculationPrograma5.iIndex = 0; //&m Reinicia index que va de 0 a numSeg
                CalculationPrograma5.bOld = true; //&m Reinicia si ya se realizo el primer calculo de distribucion t
                CalculationPrograma5.dResultPart1 = 0.0;  //&m Reinicia la primera parte de la distribucion t
                CalculationPrograma5.dResultPart2 = 0.0; //&m Reinicia la segunda parte de la distribucion t
                CalculationPrograma5.dResultPart3 = 0.0; //&m Reinicia la tercera parte de la distribucion t
                CalculationPrograma5.dResultDistT1  = 0.0; //&m Reinicia el valor de la primera distribucion t
                CalculationPrograma5.dDiff = 0.0; //&m Reinicia la diferencia entre el valor actual de distribucion t y el anterior al calcular la distribucion t
                // Calcula otra distribucion
                dDistT = CalculationPrograma5.calculateDistT(dX, iDof); //&m
                

                // Se intercambian signos para siguiente iteracion
                dSegundaDiff = dPrimerDiff;
                // Se guarda referencia de la primera diferencia
                dPrimerDiff =  dDistT - dP;
            }
                    
            
        }
        //&d=2
        return dX; 
    }
}
