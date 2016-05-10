/*
 * Programa7
 *
 * Programa que lee un archivo los valores de wk, xk y yk que corresponden
 * al estimado del proyecto, N cuadruplos con información historica , y con esa
 * informacion calcula la prediccion mejorada utilizando regresion multiple y el
 * metodo de gauss para resolver los parametros de la regresion.
 * Despliega en pantalla los valores calculados con 5 decimales redondeados 
 * hacia arriba en su último dígito (solo en caso de tener decimales).
 *
 * @author Omar Antonio Carreón Medrano A01036074
 * @date 26/04/2016
 * @version 1.0
 */
//&p-Programa7
//&b=90
package programa7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Programa7 {
    public static List<CuadruplosStruct> lstCuadruplos = new ArrayList<CuadruplosStruct>(); //&m
    
    /*
     * main
     * 
     * Lee un archivo los valores de wk, xk y yk que corresponden
     * al estimado del proyecto, N cuadruplos con información historica , y con esa
     * informacion calcula la prediccion mejorada utilizando regresion multiple y el
     * metodo de gauss para resolver los parametros de la regresion.
     * Despliega en pantalla los valores calculados con 5 decimales redondeados 
     * hacia arriba en su último dígito (solo en caso de tener decimales).
     * 
     * @param args the command line arguments
     * @exception <code>IOException</code> representa error de I/O
     */
    //&i
    public static void main(String[] args) throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa nombre de archivo: ");
        String sFileName = scanner.nextLine();
        // Abre archivo
        File fFile = FileHandler.openFile(sFileName);
        // Variables globales
        BufferedReader brReader = null;
        
        Double dWk = 0.0d;
        Double dXk = 0.0d; //&m
        Double dYk = 0.0d;
        Double dZk = 0.0d;
        Integer iN = 0;
        Integer iIndex = 0;
        //&d=12
        
        Boolean bWXYValidos = false; //&m
        Integer iNumParejaInvalido = 0;
        
        try{
            String sLine = null;
            brReader = new BufferedReader(new FileReader(fFile));
            // Leo primera linea
            sLine = brReader.readLine();
            if (sLine == null) {
                System.out.println("Archivo vacío");
            } else {
                // Separo w x y
                String[] sPartsK = sLine.split(",");
                // Almaceno wk
                dWk = Double.parseDouble(sPartsK[0]);
                // Almaceno xk
                dXk = Double.parseDouble(sPartsK[1]); //&m
                // Almaceno yk
                dYk = Double.parseDouble(sPartsK[2]);
                
                // Reviso si xk, wk y yk son mayor o igual a 0
                if (dXk >= 0.0 && dWk >= 0.0 && dYk >= 0.0 ) { //&m
                    bWXYValidos = true; //&m
                } else {
                    bWXYValidos = false; //&m
                }
                // Si xk contiene un número menor a 0
                if (!bWXYValidos) { //&m
                    System.out.println("wk, xk y/o yk contienen un número invalido."); //&m
                } else { // Si contienen números mayor o igual a 0
                    // Leo el resto del archivo
                    while ((sLine = brReader.readLine()) != null) {
                        // Incremento contador de parejas
                        // Separo x y
                        String[] sParts = sLine.split(","); 
                        // Reviso si los numeros de los cuadruplos son mayores o iguales que 0
                        if (Double.parseDouble(sParts[0]) >= 0.0 && Double.parseDouble(sParts[1]) >= 0.0 && Double.parseDouble(sParts[2]) >= 0.0 && Double.parseDouble(sParts[3]) >= 0.0) { //&m                 
                            // Guardo los cuadruplos de numeros en struct
                            lstCuadruplos.add(new CuadruplosStruct(Double.parseDouble(sParts[0]), Double.parseDouble(sParts[1]), Double.parseDouble(sParts[2]), Double.parseDouble(sParts[3]))); //&m
                        } else {
                            iNumParejaInvalido += 1;
                            break;
                        }
                    }
                    // Si los numeros no fueron mayores o iguales a 0
                    if (iNumParejaInvalido > 0) { 
                        System.out.println("Error, existe número inválido en los cuádruplos del archivo.");
                    } else { // Si los numeros fueron mayores o iguales a 0
                        // Obtengo el número de parejas que se almacenaron
                        iN = CuadruplosStruct.getLength(lstCuadruplos); //&m
                        if (iN == 0){
                            System.out.println("No hay cuadruplos de números en el archivo."); //&m
                        } else {
                            //&d=12
                            //Llama a la funcion que realiza la regresion multiple
                            Calculation.multipleRegression(lstCuadruplos,iN);
                            // Calcula la prediccion mejorada
                            dZk = Calculation.dB0 + dWk * Calculation.dB1 + dXk * Calculation.dB2 + dYk * Calculation.dB3;
                            if (Double.isNaN(Calculation.dB0) || Double.isNaN(Calculation.dB1) || Double.isNaN(Calculation.dB2) || Double.isNaN(Calculation.dB3) || Double.isNaN(dZk) ){ //&m
                                System.out.println("Error, hay operaciones con valores de 0.");
                            } else {
                                // Despliego resultados
                                System.out.println("N  = " + iN);
                                System.out.printf(Locale.US, "wk = %.5f \n", dWk); //&m
                                System.out.printf(Locale.US, "xk = %.5f \n", dXk); //&m
                                System.out.printf(Locale.US, "yk = %.5f \n", + dYk); //&m
                                System.out.println("------------");
                                System.out.printf(Locale.US, "b0 = %.5f \n", Calculation.dB0); //&m
                                System.out.printf(Locale.US, "b1 = %.5f \n", Calculation.dB1); //&m
                                System.out.printf(Locale.US, "b2 = %.5f \n", Calculation.dB2); //&m
                                System.out.printf(Locale.US, "b3 = %.5f \n", Calculation.dB3); //&m
                                System.out.println("------------");
                                System.out.printf(Locale.US ,"zk = %.5f \n", dZk); //&m
                            }
                        }
                    }
                } 
            }
        } catch(FileNotFoundException ex){
            // El archivo no existe
            System.out.println("El archivo no existe.");

        }
    }
    
}
