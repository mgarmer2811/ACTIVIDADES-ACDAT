/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;
import com.mgm.shop.*;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Shop tienda = new Shop();
        int option = 0;
        Scanner scanner = new Scanner(System.in);
        
        do{
            System.out.println("1. Agregar un producto");
            System.out.println("2. Modificar un producto");
            System.out.println("3. Eliminar un producto");
            System.out.println("4. Consultar un producto (segun codigo)");
            System.out.println("5. Listar todos los productos");
            System.out.println("6. Salir");
            System.out.print("Escoja una opcion: ");
            option = scanner.nextInt();
            
            switch(option){
                case 1:
                    Shop.addProduct();
                    break;
                case 2:
                    System.out.println("");
                    System.out.println("Introduzca el id del producto que desea modificar");
                    int productId = scanner.nextInt();
                    Shop.updateProduct(productId);
                    break;
                case 3:
                    System.out.println("");
                    System.out.println("Introduzca el id del producto que desea eliminar");
                    int productIdD = scanner.nextInt();
                    Shop.deleteProduct(productIdD);
                    break;
                case 4:
                case 5:
                    Shop.listProducts();
            }
        }
        while(option != 6);
        
    }
    
}
