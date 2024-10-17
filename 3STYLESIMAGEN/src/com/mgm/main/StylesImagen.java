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
public class StylesImagen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int MAX_COLOR_VALUE = 255;
        
        Scanner scanner = new Scanner(System.in);
        String imageName = "";
        String extension = "";
        
        System.out.println("Introduce el nombre de la imagen (con extension de fichero)");
        imageName = scanner.nextLine();
        
        String[] parts = imageName.split("\\.");
        extension = parts[1];
        
        try{
            BufferedImage ogImage = ImageIO.read(new File(imageName));
            BufferedImage brighter = ImageIO.read(new File(imageName));
            BufferedImage darker = ImageIO.read(new File(imageName));
            BufferedImage grayish = ImageIO.read(new File(imageName));
            
            for(int x = 0; x < ogImage.getWidth(); x++){
                for(int y = 0; y < ogImage.getHeight(); y++){
                    int rgb = ogImage.getRGB(x, y);
                    Color pixelColor = new Color(rgb);
                    
                    int red = pixelColor.getRed();
                    int green = pixelColor.getGreen();
                    int blue = pixelColor.getBlue();
                    
                    int gray = (int)((red * 0.3) + (green * 0.5) + (blue * 0.2));
                    
                    Color brighterColor = new Color(red,green,blue).brighter();
                    Color darkerColor = new Color(red,green,blue).darker();
                    Color grayColor = new Color(gray,gray,gray);
                    
                    brighter.setRGB(x, y, brighterColor.getRGB());
                    darker.setRGB(x, y, darkerColor.getRGB());
                    grayish.setRGB(x, y, grayColor.getRGB());
                }
            }
            ImageIO.write(brighter, extension, new File("brighter" +  "." + extension));
            ImageIO.write(darker, extension, new File("darker" +  "." + extension));
            ImageIO.write(grayish, extension, new File("grayish" +  "." + extension));
        }
        catch(IOException e){}
    }
}
