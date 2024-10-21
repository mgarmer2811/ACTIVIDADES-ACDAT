/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Usuario14
 */
public class FicheroTemp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Persona persona1 = new Persona("Manu",20);
        Persona persona2 = new Persona("Manuel",21);
        String line = "";
        String[] parts;
        
        BufferedWriter bw = null;
        BufferedReader br = null;
        
        try{
            File tempFile = File.createTempFile("tempPersonas", ".tmp");
            System.out.println("La ruta absoluta del fichero es: " + tempFile.getAbsolutePath());
            bw = new BufferedWriter(new FileWriter(tempFile));
            br = new BufferedReader(new FileReader(tempFile));
            
            bw.write(persona1.toString());
            bw.newLine();
            bw.write(persona2.toString());
            bw.newLine();
            bw.flush();
            
            int iterator = 0;
            while((line = br.readLine()) != null){
                parts = line.split(",");
                Persona personaAux = new Persona(parts[0],Integer.parseInt(parts[1]));
                System.out.println(personaAux.toString());
            }
        }
        catch(Exception e){
            System.exit(1);
        }
        finally{
            try{
                if( bw != null){
                    bw.close();
                }
            }
            catch(Exception ec){
                System.exit(2);
            }
        }
    }
    
}
