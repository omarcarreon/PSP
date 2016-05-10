/*
 * Programa3
 *
 * Programa que lee un archivo con N cantidad de parejas de números , calcula
 * la correlación, regresión, sumatorias, promedios y la ecuacion de una linea,
 * y despliega en pantalla los valores calculados con 5 decimales redondeados 
 * hacia arriba en su último dígito (solo en caso de tener decimales).
 *
 * @author Omar Antonio Carreón Medrano A01036074
 * @date 28/02/2016
 * @version 1.0
 */
//&p-Programa3
//&b=91
package programa3;

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

public class Programa3 {
    public static List<ParejasStruct> lstParejas = new ArrayList<ParejasStruct>();
    
    /*
     * main
     * 
     * Lee un archivo con N cantidad de parejas de números y utiliza funciones
     * de las FileHandler, Calculation y ParejasStruct para leer archivos y 
     * hacer calculos de correlacion, regresion, sumatorias, promedios y
     * ecuacion de una linea.
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
        Integer iXk = 0;
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
        
        try{
            String sLine = null;
            brReader = new BufferedReader(new FileReader(fFile));
            // Leo primera linea
            sLine = brReader.readLine();
            if (sLine == null) {
                System.out.println("Archivo vacío");
            } else {
                // Almaceno xk
                iXk = Integer.parseInt(sLine);
                // Reviso si xk es mayor o igual a 0
                if (iXk >= 0) {
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
                        System.out.println("Error, existe número invalido en el archivo.");
                    } else { // Si los numeros fueron mayores o iguales a 0
                        // Obtengo el número de parejas que se almacenaron
                        iN = ParejasStruct.getLength(lstParejas);
                        if (iN == 0){
                            System.out.println("No hay parejas de números en el archivo.");
                        } else {
                            // Llamo a funciones de Calculation para obtener las sumatorias y promedios
                            dSumatoriaXY = Calculation.sumatoriaXY(iN);
                            dSumatoriaX = Calculation.sumatoriaX(iN);
                            dSumatoriaX2 = Calculation.sumatoriaX2(iN);
                            dSumatoriaY2 = Calculation.sumatoriaY2(iN);
                            dSumatoriaY = Calculation.sumatoriaY(iN);
                            dPromedioX = dSumatoriaX / iN;
                            dPromedioY = dSumatoriaY / iN;

                            // Llamo a funciones de Calculation para obtener B1, Rxy, r2, B0 y Yk
                            dB1 = Calculation.calculateB1(dSumatoriaXY, iN ,dPromedioX ,dPromedioY, dSumatoriaX2);
                            dRxy = Calculation.calculateRxy(iN, dSumatoriaXY, dSumatoriaX, dSumatoriaY, dSumatoriaX2, dSumatoriaY2);
                            dR2 = dRxy * dRxy;
                            dB0 = Calculation.calculateB0(dPromedioY, dB1, dPromedioX);
                            dYK = Calculation.calculateYk(dB0, dB1, iXk);
                            if (Double.isNaN(dRxy) || Double.isNaN(dR2) || Double.isNaN(dB0) || Double.isNaN(dB1) || Double.isNaN(dYK) ){
                                System.out.println("Error, hay operaciones con valores de 0.");
                            } else {
                                // Despliego resultados
                                System.out.println("N  = " + iN);
                                System.out.println("xk = " + iXk);
                                System.out.printf(Locale.US, "r  = %.5f \n", dRxy);
                                System.out.printf(Locale.US, "r2 = %.5f \n", dR2);
                                System.out.printf(Locale.US, "b0 = %.5f \n", dB0);
                                System.out.printf(Locale.US, "b1 = %.5f \n", dB1);
                                System.out.printf(Locale.US ,"yk = %.5f \n", dYK);
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
