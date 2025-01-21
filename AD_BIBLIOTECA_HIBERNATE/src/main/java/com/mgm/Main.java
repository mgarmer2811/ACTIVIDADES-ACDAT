/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mgm;

/**
 *
 * @author Usuario14
 */
public class Main {

    public static void main(String[] args) {
        int option = -1;
        
        do{
            option = Controller.menu();
            switch(option){
                case 1:{
                    Controller.listarAutores();
                    break;
                }
                case 2:{
                    Controller.listarAutorID();
                    break;
                }
                case 3:{
                    Controller.listarLibros();
                    break;
                }
                case 4:{
                    Controller.listarLibrosID();
                    break;
                }
                case 5:{
                    Controller.agregarAutor();
                    break;
                }
                case 6:{
                    Controller.agregarLibro();
                     break;
                }
                case 7:{
                    Controller.actualizarAutor();
                    break;
                }
                case 8:{
                    Controller.actualizarLibro();
                    break;
                    
                }
                case 9:{
                    Controller.eliminarAutor();
                    break;
                }
                case 10:{
                    Controller.eliminarLibro();
                    break;
                }
                default:{
                    System.out.println("Has salido del programa");
                }
            }
        }
        while(option != 11);
    }
}