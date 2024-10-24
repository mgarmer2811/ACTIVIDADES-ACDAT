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
            File[] paths = file.listFiles();
            listDirectory(paths);
        }
        catch(Exception e){}
    }
    
    public static void listDirectory(File[] paths){
        for(File path : paths){
            System.out.println(path);
            if(path.isDirectory()){
                File[] newPaths = path.listFiles();
                listDirectory(newPaths);
            }
        }
    }
}
