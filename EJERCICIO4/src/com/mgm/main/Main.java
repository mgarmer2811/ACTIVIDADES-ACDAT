/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
        String dirPath;
        ArrayList<String> extensionsList = new ArrayList<>();
        
        System.out.println("Introduzca la ruta del directorio");
        dirPath = scanner.nextLine();
        
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        
        for(File dirFile : files){
            if(dirFile.isFile()){
                String[] parts = dirFile.getName().split("\\.");
                extensionsList.add(parts[1]);
            }
        }
        
        Set<String> extensionsSet = new HashSet<>(extensionsList);
        String[] extensions = extensionsSet.toArray(new String[0]);
        
        int[] amount = new int[extensions.length];
        
        for(int i = 0; i < extensions.length; i++){
            for(int j = 0; j < extensionsList.size(); j++){
                if(extensionsList.get(j).equals(extensions[i])){
                    amount[i]++;
                }
            }
        }
        
        for(int i = 0; i < extensions.length; i++){
            System.out.println(extensions[i] + ": " + amount[i]);
        }
    }
}
