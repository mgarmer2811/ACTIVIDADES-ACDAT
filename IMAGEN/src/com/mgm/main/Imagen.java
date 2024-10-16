/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author Usuario14
 */
public class Imagen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String imageName = "";
        System.out.println("Introduzca el nombre de una imagen (con extension de archivo)");
        imageName = scanner.nextLine();
        
        try{
            BufferedImage image = ImageIO.read(new File(imageName));
            System.out.println("Ancho de la imagen: " + image.getWidth() + " px");
            System.out.println("Alto de la imagen: " + image.getHeight() + " px");
            System.out.println("Pixeles de la imagen: " + (image.getWidth() * image.getHeight()));
            ImageIO.write(image, "png", new File("modified.png"));
        }
        catch(IOException ioe){}
    }
    
}
