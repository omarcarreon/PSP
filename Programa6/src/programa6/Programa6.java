/*
 * Programa6
 *
 * A partir de un archivo, calcula la cantidad de parejas de datos leídas (N), 
 * los coeficientes de correlación rx,y y r2, la significancia de tal correlación,
 * los parámetros de regresión lineal β0 y β1, una predicción mejorada yk, 
 * donde yk = β0 + β1 xk y el intervalo de predicción al 70% para tal estimado.
 *
 * @author Omar Antonio Carreón Medrano A01036074
 * @date 17/04/2016
 * @version 1.0
 */

//&p-Programa6
//&b=91
package programa6;

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

public class Programa6 {
    public static List<ParejasStruct> lstParejas = new ArrayList<ParejasStruct>();
    /*
     * main
     * 
     * A partir de un archivo, calcula la cantidad de parejas de datos leidas (N), 
     * los coeficientes de correlación rx,y y r2, la significancia de tal correlación,
     * los parámetros de regresión lineal β0 y β1, una predicción mejorada yk, 
     * donde yk = β0 + β1 xk y el intervalo de predicción al 70% para tal estimado.
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
        Double dXk = 0.0d;
        Integer iN = 0;
        Integer iIndex = 0;
        Double dSumatoriaXY = 0.0d;
        Double dSumatoriaX = 0.0d;
        Double dSumatoriaY = 0.0d;
        Double dSumatoriaX2 = 0.0d;
        Double dSumatoriaY2 = 0.0d;
        Double dPromedioX = 0.0d;
        Double dPromedioY = 0.0d;
        Double dB0 = 0.0d;
        Double dB1 = 0.0d;
        Double dRxy = 0.0d;
        Double dR2 = 0.0d;
        Double dYK = 0.0d;
        Boolean bXkValido = false;
        Integer iNumParejaInvalido = 0; //&m
        Double dXSign = 0.0d;
        Double dTailArea = 0.0d;
        Double dRange = 0.0d;
        Double dUPI = 0.0d;
        Double dLPI =0.0d;
        try{
            String sLine = null;
            brReader = new BufferedReader(new FileReader(fFile));
            // Leo primera linea
            sLine = brReader.readLine();
            if (sLine == null) {
                System.out.println("Archivo vacío");
            } else {
                // Almaceno xk
                dXk = Double.parseDouble(sLine);
                // Reviso si xk es mayor o igual a 0
                if (dXk >= 0.0) { //&m
                    bXkValido = true;
                } else {
                    bXkValido = false;
                }
                // Si xk contiene un número menor a 0
                if (!bXkValido) {
                    System.out.println("xk contiene un número invalido.");
                } else { // Si xk contiene un número mayor o igual a 0
                    // Leo el resto del archivo
                    while ((sLine = brReader.readLine()) != null) {
                        // Incremento contador de parejas
                        // Separo x y
                        String[] sParts = sLine.split(","); 
                        // Reviso si los numeros son mayores o iguales que 0
                        if (Double.parseDouble(sParts[0]) >= 0.0 && Double.parseDouble(sParts[1]) >= 0.0) {                    
                            // Guardo las parejas de numeros en struct
                            lstParejas.add(new ParejasStruct(Double.parseDouble(sParts[0]), Double.parseDouble(sParts[1])));
                        } else {
                            iNumParejaInvalido += 1; //&m
                            break;
                        }
                    }
                    // Si los numeros no fueron mayores o iguales a 0
                    if (iNumParejaInvalido > 0) { //&m
                        System.out.println("Error, existe número invalido en las parejas.");
                    } else { // Si los numeros fueron mayores o iguales a 0
                        // Obtengo el número de parejas que se almacenaron
                        iN = ParejasStruct.getLength(lstParejas);
                        if (iN == 0){
                            System.out.println("No hay parejas de números en el archivo.");
                        } else {
                            // Llamo a funciones de Calculation para obtener las sumatorias y promedios
                            dSumatoriaXY = CalculationPrograma3.sumatoriaXY(iN); //&m
                            dSumatoriaX = CalculationPrograma3.sumatoriaX(iN); //&m
                            dSumatoriaX2 = CalculationPrograma3.sumatoriaX2(iN); //&m
                            dSumatoriaY2 = CalculationPrograma3.sumatoriaY2(iN); //&m
                            dSumatoriaY = CalculationPrograma3.sumatoriaY(iN); //&m
                            dPromedioX = dSumatoriaX / (iN * 1.0); //&m
                            dPromedioY = dSumatoriaY / (iN * 1.0); //&m

                            // Llamo a funciones de Calculation para obtener B1, Rxy, r2, B0 y Yk
                            dB1 = CalculationPrograma3.calculateB1(dSumatoriaXY, iN ,dPromedioX ,dPromedioY, dSumatoriaX2); //&m
                            dRxy = CalculationPrograma3.calculateRxy(iN, dSumatoriaXY, dSumatoriaX, dSumatoriaY, dSumatoriaX2, dSumatoriaY2); //&m
                            dR2 = dRxy * dRxy;
                            dXSign  = CalculationPrograma6.calculateX(iN, dRxy);
                            dTailArea = CalculationPrograma6.calculateTailArea(iN,dXSign);
                            dB0 = CalculationPrograma3.calculateB0(dPromedioY, dB1, dPromedioX); //&m
                            dYK = CalculationPrograma3.calculateYk(dB0, dB1, dXk); //&m
                            dRange = CalculationPrograma6.calculateRange(iN,dXk,dPromedioX,dB0,dB1);
                            dUPI = CalculationPrograma6.calculateUPI(dYK,dRange);
                            dLPI = CalculationPrograma6.calculateLPI(dYK,dRange);
                            if (Double.isNaN(dRxy) || Double.isNaN(dR2) || Double.isNaN(dB0) || Double.isNaN(dB1) || Double.isNaN(dYK) ){
                                System.out.println("Error, hay operaciones con valores de 0.");
                            } else {
                                // Despliego resultados
                                System.out.println("N  = " + iN);
                                System.out.println("xk = " + dXk.intValue());
                                System.out.printf(Locale.US, "r  = %.5f \n", dRxy);
                                System.out.printf(Locale.US, "r2 = %.5f \n", dR2);
                                System.out.printf(Locale.US, "b0 = %.5f \n", dB0);
                                System.out.printf(Locale.US, "b1 = %.5f \n", dB1);
                                System.out.printf(Locale.US ,"yk = %.5f \n", dYK);
                                System.out.printf(Locale.US ,"sig= %.10f \n", dTailArea);
                                System.out.printf(Locale.US ,"ran= %.5f \n", dRange);
                                System.out.printf(Locale.US ,"LS = %.5f \n", dUPI);
                                System.out.printf(Locale.US ,"LI = %.5f \n", dLPI);
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
