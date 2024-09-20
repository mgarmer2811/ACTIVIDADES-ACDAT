/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;

import java.io.File;

/**
 *
 * @author Usuario14
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = "C:\\Users\\Usuario14\\Desktop";
        displayPath(path);
    }
    
    public static void displayPath(String path){
        File file = new File(path);
        String pathName = file.getName();
        System.out.println("Ruta: " + pathName);
        if(file.exists()){
            if(!file.isFile()){
                System.out.println("Es un directorio, a continuacion se muestra su contenido:");
                System.out.println("");
                File[] files = file.listFiles();
                for(int i = 0; i < files.length; i++){
                    if(files[i].isFile()){
                        System.out.println("_-" + files[i].getName());
                    }
                    else{
                        System.out.println("d-" + files[i].getName());
                    }
                }
            }
        }
        else{
            System.out.println("?-" + pathName);
            System.out.println("Este directorio no existe");
        }
    }
    
}
