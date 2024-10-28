/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;

import java.util.InputMismatchException;
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
        int optionLvl = 999;
        
        do{
            try{
                System.out.println("Introduzca el nivel a jugar {1-10}");
                optionLvl = scanner.nextInt();
            }
            catch(InputMismatchException ime){
                System.err.println("Formato no valido, por favor introduzca un entero {1-10}");
            }
        }
        while((optionLvl < 1) || (optionLvl > 10));
        
        
    }
    
}
