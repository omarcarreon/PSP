/*
 * Programa4
 *
 * Programa que solicita y recibe valores de x y dof, y llama a función 
 * calculateDistT que calcula la distribución t.
 * Manda a llamar función que despliega en pantalla los valores calculados con 
 * 5 decimales redondeados hacia arriba en su último dígito (solo en caso de 
 * tener decimales).
 *
 *
 * @author Omar Antonio Carreón Medrano A01036074
 * @date 15/03/2016
 * @version 1.0
 */
//&p-Programa4
package programa4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programa4 {

    /*
     * main
     * 
     * Pide al usuario el valor de x y dof y los almacena en el Handler. 
     * Llama a las función para calcular la distribución t. Llama a función 
     * printData para imprimir los resultados.
     * 
     * @param args the command line arguments
     */
    //&i
    
    // List para almacenar el valor de x, dof y distribucion t en DataHandler
    public static List<DataHandler> lstData = new ArrayList<DataHandler>();
    
    public static void main(String[] args) {
        
        System.out.println("Ingresa valor de x: ");
        Scanner scanner = new Scanner(System.in);
        Double dX = scanner.nextDouble();
        Integer iDof = 0;
        Double dDistT = 0.0d;
        
        // Valida que el valor de x sea mayor o igual a 0
        while (dX < 0) {
            System.out.println("Error, el valor de x debe ser mayor o igual a 0.");
            dX = scanner.nextDouble();
        }
        
        System.out.println("Ingresa valor de dof: ");
        iDof = scanner.nextInt();
        // Valida que el valor de dof sea mayor a 0
        while (iDof <= 0) {
            System.out.println("Error, el valor de dof debe ser mayor a 0.");
            iDof = scanner.nextInt();
        }
        if (dX == 0) {
            dDistT = 0.0;
        } else {
            // Calcula distribucion t
            dDistT = Calculation.calculateDistT(dX, iDof);
        }
        System.out.println(dDistT);
        
        // Agrega x, dof y la distribucion t a DataHandler
        lstData.add(new DataHandler(dX, iDof, dDistT));
        // Imprime resultados
        DataHandler.printData();
        
    }
    
}
