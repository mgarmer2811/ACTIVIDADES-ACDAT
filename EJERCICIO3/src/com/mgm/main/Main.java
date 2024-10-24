/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        File file = new File(path);
        listProperties(file);
        
    }
    
    public static void listProperties(File file){
        Date date = new Date(file.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        System.out.println("Nombre: " + file.getName());
        System.out.println("Tamaño: " + file.length() + " bytes");
        System.out.println("Fecha ultima modificacion: " + sdf.format(date));
        if(file.canRead())
            System.out.println("El usuario tiene permisos de lectura");
        if(file.canWrite())
            System.out.println("El usuario tiene permisos de escritura");
        if(file.canExecute())
            System.out.println("El usuario tiene persmisos de escritura");
        if(file.isHidden())
            System.out.println("El archivo esta en oculto");
    }
}