/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Usuario14
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName,line;
        int numLinea = 0;
        
        System.out.println("Introduzca nombre del fichero");
        fileName = scanner.nextLine();
        
        BufferedReader br = null;
        FileReader fr = null;
        
        try{
            File file = new File(fileName);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            
            while((line = br.readLine()) != null){
                numLinea++;
                System.out.println(numLinea + ". " + line);
            }
            System.out.println("Total lineas: " + numLinea);
        }
        catch(Exception leerException){
            System.err.println("Ha ocurrido un error leyendo del fichero");
        }
        finally{
            try{
                if(br != null){
                    br.close();
                }
            }
            catch(Exception cerrarReaderException){
                System.err.println("Ha habido un error cerrando el reader");
            }
        }
    }
    
}
