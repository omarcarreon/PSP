/// <summary>
/// Programa2
///
/// Este programa es un contador de lineas de un archivo que se recibe como 
/// input y despliega la cuenta en la consola y en otro archivo.
/// Autor: Omar Carreon Matricula: A01036074 
///
/// </summary>

//&p-Programa2
//&b=47
package programa2; 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static programa2.FileHandler.openFile;

/**
 *
 * @author Omar Antonio Carreon Medrano A01036074
 */
public class Programa2 {

    /// <summary>
    /// main
    /// Lee archivo, cuenta lineas y escribe archivo con la cuenta
    /// @param args the command line arguments
    /// </summary>
    //&i
    public static void main(String[] args) throws IOException {
        // Inicialización de scanner para input del usuario
        Scanner scanner = new Scanner(System.in);
        // Inicialización de variable para respuesta del usuario 
        String sAnswer = "no";
        // Variables para contar el total de archivos, lineas vacias y lineas con info
        Integer iFileCounter = 0, iTotalLdc = 0; //&m
        // Lista de objeto FileStruct para guardar la info de los archivos
        List<FileStruct> lstParts = new ArrayList<FileStruct>();
        List<String> lstFile = new ArrayList<String>();
        do{ 
            List<String> lstLines = new ArrayList<String>();
            
            System.out.println("Ingresa nombre de archivo: ");
            String sFileName = scanner.nextLine();
            // Abre archivo
            File fFile = FileHandler.openFile(sFileName);
            // Variables para contar lineas vacias y con info de cada archivo
            Integer iLinesEmpty = 0, iLinesInfo = 0;
            Integer iLdc = 0, iItems=0, iBase=0, iDeleted=0, iModified=0, iAdded=0;
            String sType="", sPartName="";
            Integer iCountParts = 0;
            BufferedReader brReader = null;
            Integer i = 0;
            
            try {
                // Incrementa en 1 el contador de archivos
                iFileCounter += 1;
                //&d=2
                String sLine = null;
                brReader = new BufferedReader(new FileReader(fFile));
                while ((sLine = brReader.readLine()) != null) {
                   lstLines.add(sLine);
                }
                for(i=0; i<lstLines.size();i++) {
                    iTotalLdc = iTotalLdc + CounterLDC.countLDCTotal(lstLines.get(i));
                     if ((iCountParts!=0 && lstLines.get(i).trim().startsWith("//&p"))|| (i>lstLines.size()) ){
                        iAdded = iLdc - iBase + iDeleted;
                        sType = CounterLDC.getType(iBase, iModified, iDeleted, iAdded);
                        lstParts.add(new FileStruct(sPartName,iLdc, iItems, iBase, iDeleted, iModified, iAdded, sType)); 
                        iLdc = 0;
                        iBase = 0;
                        iItems = 0;
                        iDeleted = 0;
                        iModified = 0;
                        iAdded = 0;
                        sType = null;
                        sPartName = null;
                        iCountParts = 0;  
                    }
                     if (iCountParts==0 && lstLines.get(i).trim().startsWith("//&p")) {
                        iCountParts = iCountParts + 1;
                        sPartName = CounterLDC.getPartName(lstLines.get(i));
                    }
                    if (iCountParts>0) {
                        iLdc += CounterLDC.countLDCTotal(lstLines.get(i));
                        iItems += CounterLDC.countItems(lstLines.get(i));
                        iBase += CounterLDC.countBase(lstLines.get(i));
                        iDeleted += CounterLDC.countDeleted(lstLines.get(i));
                        iModified += CounterLDC.countModified(lstLines.get(i));
                    }      
                    
                }
                iAdded = iLdc - iBase + iDeleted;
                sType = CounterLDC.getType(iBase, iModified, iDeleted, iAdded);
                lstParts.add(new FileStruct(sPartName,iLdc, iItems, iBase, iDeleted, iModified, iAdded, sType));

                //&d=1
            } catch (FileNotFoundException ex) {
               System.out.println("El archivo no existe. Ingresa otro nombre de archivo.");
               iFileCounter -= 1;
            }
            
            System.out.println("Otro archivo? si/no ");
            sAnswer = scanner.nextLine();
            // mientras el usuario responda que si
        } while ("si".equals(sAnswer));
        //&d=11
        int iCounter;
        
        System.out.println("PARTES BASE:");
        for (iCounter = 0; iCounter < lstParts.size(); iCounter++) {
            if ("BASE".equals(lstParts.get(iCounter).getType())) {
                System.out.print(lstParts.get(iCounter).getPartName() + ": ");
                System.out.print("T=" + lstParts.get(iCounter).getTotal() + ", ");
                System.out.print("I=" + lstParts.get(iCounter).getItems()+ ", ");
                System.out.print("B=" + lstParts.get(iCounter).getBase()+ ", ");
                System.out.print("D=" + lstParts.get(iCounter).getDeleted()+ ", ");
                System.out.print("M=" + lstParts.get(iCounter).getModified()+ ", ");
                System.out.println("A=" + lstParts.get(iCounter).getAdded()+ " ");
                 
            }
        }
        
        lstFile.add("PARTES BASE:");
        lstFile.add("\n");
        for (iCounter = 0; iCounter < lstParts.size(); iCounter++) {
            if ("BASE".equals(lstParts.get(iCounter).getType())) {
                lstFile.add(lstParts.get(iCounter).getPartName() + ": ");
                lstFile.add("T=" + lstParts.get(iCounter).getTotal() + ", ");
                lstFile.add("I=" + lstParts.get(iCounter).getItems()+ ", ");
                lstFile.add("B=" + lstParts.get(iCounter).getBase()+ ", ");
                lstFile.add("D=" + lstParts.get(iCounter).getDeleted()+ ", ");
                lstFile.add("M=" + lstParts.get(iCounter).getModified()+ ", ");
                lstFile.add("A=" + lstParts.get(iCounter).getAdded()+ " ");
                lstFile.add("\n");
            }
        }
        
        System.out.println("---------------------------------------------");
        lstFile.add("---------------------------------------------");
        lstFile.add("\n");
        
        System.out.println("PARTES NUEVAS:");
        for (iCounter = 0; iCounter <lstParts.size(); iCounter++) {
            if ("NUEVA".equals(lstParts.get(iCounter).getType())) {
                System.out.print(lstParts.get(iCounter).getPartName() + ": ");
                System.out.print("T=" + lstParts.get(iCounter).getTotal() + ", ");
                System.out.println("I=" + lstParts.get(iCounter).getItems()+ " ");
                
            }
        }
        
        lstFile.add("PARTES NUEVAS:");
        lstFile.add("\n");
        for (iCounter = 0; iCounter <lstParts.size(); iCounter++) {
            if ("NUEVA".equals(lstParts.get(iCounter).getType())) {
                lstFile.add(lstParts.get(iCounter).getPartName() + ": ");
                lstFile.add("T=" + lstParts.get(iCounter).getTotal() + ", ");
                lstFile.add("I=" + lstParts.get(iCounter).getItems()+ " ");
                lstFile.add("\n");
            }
        }
        
        System.out.println("---------------------------------------------");
        lstFile.add("---------------------------------------------");
        lstFile.add("\n");

        System.out.println("PARTES REUSADAS:");
        for (iCounter = 0; iCounter <lstParts.size(); iCounter++) {
          if ("REUSADA".equals(lstParts.get(iCounter).getType())) {
                System.out.print(lstParts.get(iCounter).getPartName() + ": ");
                System.out.print("T=" + lstParts.get(iCounter).getTotal() + ", ");
                System.out.print("I=" + lstParts.get(iCounter).getItems()+ ", ");
                System.out.println("B=" + lstParts.get(iCounter).getBase()+ " ");
            }  
        }
        
        lstFile.add("PARTES REUSADAS:");
        lstFile.add("\n");
        for (iCounter = 0; iCounter <lstParts.size(); iCounter++) {
          if ("REUSADA".equals(lstParts.get(iCounter).getType())) {
                lstFile.add(lstParts.get(iCounter).getPartName() + ": ");
                lstFile.add("T=" + lstParts.get(iCounter).getTotal() + ", ");
                lstFile.add("I=" + lstParts.get(iCounter).getItems()+ ", ");
                lstFile.add("B=" + lstParts.get(iCounter).getBase()+ " ");
                lstFile.add("\n");
            }  
        }
        System.out.println("---------------------------------------------");
        System.out.println("Total de LDC=" + (iTotalLdc - CounterLDC.iCountMultiple));
        
        
        lstFile.add("---------------------------------------------");
        lstFile.add("\n");
        lstFile.add("Total de LDC=" + (iTotalLdc - CounterLDC.iCountMultiple));
        lstFile.add("\n");
        
        FileHandler.writeFile(lstFile);
        
    }
    
}
