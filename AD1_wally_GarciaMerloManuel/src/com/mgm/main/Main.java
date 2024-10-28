/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

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
        String lvlImageName = "", wallyImageName = "", lvl = "";
        String projectDir = System.getProperty("user.dir");
        
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
        
        switch (optionLvl) {
            case 1: {
                lvlImageName = "niveles/1.png";
                wallyImageName = "niveles/1-wally.png";
                lvl = "1";
                break;
            }
            case 2: {
                lvlImageName = "niveles/2.png";
                wallyImageName = "niveles/2-wally.png";
                lvl = "2";
                break;
            }
            case 3: {
                lvlImageName = "niveles/3.png";
                wallyImageName = "niveles/3-wally.png";
                lvl = "3";
                break;
            }
            case 4: {
                lvlImageName = "niveles/4.png";
                wallyImageName = "niveles/4-wally.png";
                lvl = "4";
                break;
            }
            case 5: {
                lvlImageName = "niveles/5.png";
                wallyImageName = "niveles/5-wally.png";
                lvl = "5";
                break;
            }
            case 6: {
                lvlImageName = "niveles/6.png";
                wallyImageName = "niveles/6-wally.png";
                lvl = "6";
                break;
            }
            case 7: {
                lvlImageName = "niveles/7.png";
                wallyImageName = "niveles/7-wally.png";
                lvl = "7";
                break;
            }
            case 8: {
                lvlImageName = "niveles/8.png";
                wallyImageName = "niveles/8-wally.png";
                lvl = "8";
                break;
            }
            case 9: {
                lvlImageName = "niveles/9.png";
                wallyImageName = "niveles/9-wally.png";
                lvl = "9";
                break;
            }
            case 10: {
                lvlImageName = "niveles/10.png";
                wallyImageName = "niveles/10-wally.png";
                lvl = "10";
                break;
            }
        }
        try {
            BufferedImage mainImage = ImageIO.read(new File(lvlImageName));
            BufferedImage wallyImage = ImageIO.read(new File(wallyImageName));

            BufferedImage grayscaleImage = new BufferedImage(mainImage.getWidth(), mainImage.getHeight(), mainImage.getType());
            for (int y = 0; y < mainImage.getHeight(); y++) {
                for (int x = 0; x < mainImage.getWidth(); x++) {
                    Color originalColor = new Color(mainImage.getRGB(x, y));
                    int grayValue = (int) (0.3 * originalColor.getRed() + 0.5 * originalColor.getGreen() + 0.20 * originalColor.getBlue());
                    Color grayColor = new Color(grayValue, grayValue, grayValue);
                    grayColor = grayColor.darker();
                    grayscaleImage.setRGB(x, y, grayColor.getRGB());
                }
            }

            int wallyPosX = -1;
            int wallyPosY = -1;

            boolean found = false;
            for (int y = 0; y <= (mainImage.getHeight() - wallyImage.getHeight()) && (!found); y++) {
                for (int x = 0; x <= (mainImage.getWidth() - wallyImage.getWidth()); x++) {
                    if (matches(mainImage, wallyImage, x, y)) {
                        wallyPosX = x;
                        wallyPosY = y;
                        found = true;
                        break;
                    }
                }
            }

            if (wallyPosX != -1 && wallyPosY != -1) {
                for (int y = 0; y < wallyImage.getHeight(); y++) {
                    for (int x = 0; x < wallyImage.getWidth(); x++) {
                        int colorPixel = wallyImage.getRGB(x, y);
                        grayscaleImage.setRGB(wallyPosX + x, wallyPosY + y, colorPixel);
                    }
                }
                
                
                File output = new File(projectDir + "/resultado/" + lvl + "-" + "resultado" + ".png");
                ImageIO.write(grayscaleImage, "png", output);
                System.out.println("Se ha encontrado a Wally con exito");
            } else {
                System.out.println("No se ha encontrado a Wally");
            }

        } catch (IOException e) {
            System.err.println("Ha ocurrido un error leyendo/escribiendo en los ficheros");
        }
    }
    
    private static boolean matches(BufferedImage mainImage, BufferedImage wallyImage, int startX, int startY) {
        for (int y = 0; y < wallyImage.getHeight(); y++) {
            for (int x = 0; x < wallyImage.getWidth(); x++) {
                if (mainImage.getRGB(startX + x, startY + y) != wallyImage.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }
}
