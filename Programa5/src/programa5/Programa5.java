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
 * @date 04/04/2016
 * @version 1.0
 */
//&p-Programa5
//&b=51
package programa5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Programa5 {

    // List para almacenar el valor de x, dof y distribucion t en DataHandler
    public static List<DataHandler> lstData = new ArrayList<DataHandler>();
    
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
    public static void main(String[] args) {
        
        System.out.println("Ingresa valor de p: "); //&m
        Scanner scanner = new Scanner(System.in);
        Double dP = scanner.nextDouble(); //&m
        Integer iDof = 0;
        Double dDistT = 0.0d;
        Double dX = 1.0d;
        Double dD = 0.5d;
        Double dPrimerDiff = 0.0d;
        Double dSegundaDiff = 0.0d;
        
        // Valida que el valor de p este entre 0 y 0.5
        while (dP < 0.0 || dP > 0.5) { //&m
            System.out.println("Error, el valor de p debe estar entre 0 y 0.5"); //&m
            dP = scanner.nextDouble(); //&m
        }
        
        System.out.println("Ingresa valor de dof: ");
        iDof = scanner.nextInt();
        // Valida que el valor de dof sea mayor a 0
        while (iDof <= 0) {
            System.out.println("Error, el valor de dof debe ser mayor a 0.");
            iDof = scanner.nextInt();
        }
        if (dX == 0.0) { //&m
            dP = 0.0; //&m
        } else {
            
            // Calcula distribucion t
            dDistT = Calculation.calculateDistT(dX, iDof);
            // Guarda la diferencia entre la distribucion calculada y la ingresada
            dPrimerDiff = dDistT - dP;
            
            dSegundaDiff = dPrimerDiff;
            // Mientras que no este dentro de la tolerancia de error
            while (Math.abs(dPrimerDiff) >= Calculation.dE) {
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

                Calculation.iNumSeg = 10; // Reinicia numero de segmentos
                Calculation.iMultiplier = 1; // Reinicia multiplier
                Calculation.iIndex = 0; // Reinicia index que va de 0 a numSeg
                Calculation.bOld = true; // Reinicia si ya se realizo el primer calculo de distribucion t
                Calculation.dResultPart1 = 0.0;  // Reinicia la primera parte de la distribucion t
                Calculation.dResultPart2 = 0.0; // Reinicia la segunda parte de la distribucion t
                Calculation.dResultPart3 = 0.0; // Reinicia la tercera parte de la distribucion t
                Calculation.dResultDistT1  = 0.0; // Reinicia el valor de la primera distribucion t
                Calculation.dDiff = 0.0; // Reinicia la diferencia entre el valor actual de distribucion t y el anterior al calcular la distribucion t
                // Calcula otra distribucion
                dDistT = Calculation.calculateDistT(dX, iDof);
                

                // Se intercambian signos para siguiente iteracion
                dSegundaDiff = dPrimerDiff;
                // Se guarda referencia de la primera diferencia
                dPrimerDiff =  dDistT - dP;
            }
                    
            
        }
         // Agrega x, dof y la distribucion t a DataHandler
        lstData.add(new DataHandler(dX, iDof, dP)); //&m
        // Imprime resultados
        DataHandler.printData();
    }
}
