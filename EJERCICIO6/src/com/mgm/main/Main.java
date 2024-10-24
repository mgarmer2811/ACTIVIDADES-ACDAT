/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
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
        String fileName;
        int option = 1;
        ArrayList<String> lines = new ArrayList<>();
        boolean reading = true, overwrite = false;
        
        System.out.println("Introduzca lineas de texto {escriba 'FIN' para terminar}");
        while(reading){
            String line = scanner.nextLine();
            if(!line.equalsIgnoreCase("fin")){
                lines.add(line);
            }
            else{
                reading = false;
            }
        }
        System.out.println("Introduzca el nombre del fichero en donde guardar el resultado");
        fileName = scanner.nextLine();
        
        File file = new File(fileName);
        if(file.exists()){
            System.out.println("El fichero ya existe, desea sobreescribirlo o anadirle el contenido al final? {1,2}");
            option = scanner.nextInt();
        }
        switch(option){
            case 1:{
                overwrite = false;
                break;
            }
            case 2:{
                overwrite = true;
                break;
            }
            default:{
                System.err.println("No has introducido bien la opcion");
                System.exit(1);
            }
        }
        
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file, overwrite));

            for (String line : lines) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
        } catch (Exception sobreescribiendoException) {
            System.err.println("Ha habido un error sobreescribiendo el archivo");
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception cerrarException) {
                System.err.println("Ha habido un error cerrando el writer");
            }
        }
    }
    
}
