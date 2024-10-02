/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.utils;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class NumberUtils {
    
    public static void readFile(String fileName, ArrayList<Integer> numbers) throws PositiveNumbersOnlyException {
        FileInputStream fin = null;
        DataInputStream din = null;
        
        try{
            File file = new File(fileName);
            fin = new FileInputStream(file);
            din = new DataInputStream(fin);
            
            while(true){
                try{
                    int number = din.readInt();
                    if(number <= 0){
                        throw new PositiveNumbersOnlyException("Solo se permiten numeros mayores que 0, " + number + " no cumple");
                    }
                    numbers.add(number);
                }
                catch(EOFException eof){
                    break;
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
            System.err.println("Ha surgido un error abriendo el fichero");
        }
        finally{
            try{
                if(din != null)
                    din.close();
            }
            catch(IOException e){}
        }
    }
    
    public static void separateOddEven(ArrayList<Integer> numbers, ArrayList<Integer> oddNumbers, ArrayList<Integer> evenNumbers) {
        for(Integer number : numbers){
            if((number%2) == 0){
                evenNumbers.add(number);
            }
            else{
                oddNumbers.add(number);
            }
        }
    }
}
