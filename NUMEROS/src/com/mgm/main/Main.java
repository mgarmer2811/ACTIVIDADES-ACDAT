/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;

import com.mgm.utils.*;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        ArrayList<Integer> oddNumbers = new ArrayList<Integer>();
        ArrayList<Integer> evenNumbers = new ArrayList<Integer>();
        String fileName = "numeros_1.dat";
        
        try{
            NumberUtils.readFile(fileName, numbers);
            NumberUtils.separateOddEven(numbers, oddNumbers, evenNumbers);
            System.out.println("Numeros leidos:  " + numbers.toString());
            System.out.println("Numeros pares:  " + evenNumbers.toString());
            System.out.println("Numeros impares:  " + oddNumbers.toString());
        }
        catch(PositiveNumbersOnlyException pnoe){
            System.err.println(pnoe.getMessage());
        }
        numbers.clear();
        oddNumbers.clear();
        evenNumbers.clear();
    }
}
