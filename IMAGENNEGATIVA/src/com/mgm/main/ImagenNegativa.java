/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author Usuario14
 */
public class ImagenNegativa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int MAX_COLOR_VALUE = 255;
        
        Scanner scanner = new Scanner(System.in);
        String imageName = "";
        String outputName = "";
        String newName = "";
        String extension = "";
        String option = "";
        
        System.out.println("Introduce el nombre de la imagen (con extension de fichero)");
        imageName = scanner.nextLine();
        System.out.println("Introduce el nombre de la nueva imagen (con extension de fichero)");
        outputName = scanner.nextLine();
        
        while(checkExistance(outputName)){
            System.out.println("El fichero de salida ya existe, desea sobreescribir (s) o cambiar el nombre del fichero (c)");
            option = scanner.nextLine();
            option = option.toLowerCase();
            
            switch(option){
                case "s":
                {
                    break;
                }
                case "c":
                {
                    System.out.println("Introduzca el nuevo nombre de la imagen (con extension de fichero)");
                    outputName = scanner.nextLine();
                    break;
                }
                default:
                {
                    System.out.println("Te has equivocado escribiendo la opcion, vuelve a escribirla");
                }
            }
            if(option.equals("s")){
                break;
            }
        }
        
        String[] parts = outputName.split("\\.");
        newName = parts[0];
        extension =  parts[1];
        
        try{
            BufferedImage image = ImageIO.read(new File(imageName));
            
            for(int x = 0; x < image.getWidth(); x++){
                for(int y = 0; y < image.getHeight(); y++){
                    int rgb = image.getRGB(x, y);
                    Color pixelColor = new Color(rgb);
                    
                    int red = pixelColor.getRed();
                    int green = pixelColor.getGreen();
                    int blue = pixelColor.getBlue();
                    
                    red = MAX_COLOR_VALUE - red;
                    green = MAX_COLOR_VALUE - green;
                    blue = MAX_COLOR_VALUE - blue;
                    
                    Color invertedColor = new Color(red,green,blue);
                    image.setRGB(x, y, invertedColor.getRGB());
                }
            }
            ImageIO.write(image, extension, new File(newName +  "." + extension));
        }
        catch(IOException ioe){}
    }
    
    public static boolean checkExistance(String outputName) {
        boolean exists;
        File checkFile = new File(outputName);
        if(checkFile.exists()){
            exists = true;
        }
        else{
            exists = false;
        }
        return exists;
    }
}
