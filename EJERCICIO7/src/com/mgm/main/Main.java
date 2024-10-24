/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;

import java.io.BufferedReader;
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
        String fileName,line,keyword;
        int ocurrences = 0;
        String regex = "[^\\p{L}]+";
        
        System.out.println("Introduzca el nombre del fichero en el que buscar");
        fileName = scanner.nextLine();
        System.out.println("Introduzca la palabra a buscar a en el texto");
        keyword = scanner.nextLine();
        
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(fileName));
            
            while((line = br.readLine()) != null){
                String[] words = line.split(regex);
                
                for(String word : words){
                    if(word.equalsIgnoreCase(keyword)){
                        ocurrences++;
                    }
                }
            }
            System.out.println("La palabra " + keyword + " aparece un total de " + ocurrences + " veces");
        }
        catch(Exception leyendoExcepion){
            System.err.println("Ha ocurrido un error ");
        }
    }
    
}
