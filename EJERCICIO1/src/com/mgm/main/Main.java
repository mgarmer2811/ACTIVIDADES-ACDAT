/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;

import java.io.File;
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
        String path;
        
        System.out.println("Introduzca la ruta");
        path = scanner.nextLine();
        
        try{
            File file = new File(path);
            
            if(file.exists()){
                if(file.isFile()){
                    System.out.println("La ruta pertenece a un fichero");
                    if(file.canRead()){
                        System.out.println("El usuario tiene permisos de lectura");
                    }
                    if(file.canWrite()){
                        System.out.println("El usuario tiene permisos de escritura");
                    }
                    if(file.canExecute()){
                        System.out.println("El ususario tiene permisos de ejecucion");
                    }
                }
                else{
                    System.out.println("La ruta pertenece a un directorio");
                    if(file.canRead()){
                        System.out.println("El usuario tiene permisos de lectura");
                    }
                    if(file.canWrite()){
                        System.out.println("El usuario tiene permisos de escritura");
                    }
                    if(file.canExecute()){
                        System.out.println("El ususario tiene permisos de ejecucion");
                    }
                }
            }
        }
        catch(Exception e){
            System.err.println("Ha ocurrido un error en el fichero");
        }
    }
    
}
