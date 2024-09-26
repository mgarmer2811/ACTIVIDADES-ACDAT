/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;
import com.mgm.biblioteca.*;
import java.util.Scanner;

/**
 *
 * @author MGM
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Autor autor1 = new Autor(1,"Irene","Vallejo");
        Autor autor2 = new Autor(2,"Federico","Garcia Lorca");
        Libro libro1 = new Libro(1,"El infinito en un junco",autor1,"Siruela",2022);
        Libro libro2 = new Libro(2,"Poeta en Nueva York",autor2,"Séneca",1940);
        Libro libro3 = new Libro(3,"El silbido del arquero",autor1,"Contraseña editorial",2015);
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);
        
        biblioteca.addLibroCompleto(libro1);
        biblioteca.addLibroCompleto(libro2);
        biblioteca.addLibroCompleto(libro3);
        
        do{
            System.out.println("Elija una opcion:");
            System.out.println("1. Mostrar todos los libros");
            System.out.println("2. Mostrar libros por nombre/apellidos del autor");
            System.out.println("3. Mostrar libros por titulo");
            System.out.println("4. Mostrar libros por orden alfabetico");
            System.out.println("5. Mostrar libros por reciente publicacion");
            System.out.println("6. Salir");
            System.out.println("");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Tengo que hacer esto para deshacerme del caracter de \n (me molesta para leer strings)
            
            switch(opcion){
                case 1:
                    biblioteca.mostrarLibros();
                    System.out.println("");
                    break;
                case 2:
                    String texto;
                    System.out.println("Introduce el nombre/apellido(s) del autor");
                    texto = scanner.nextLine();
                    biblioteca.mostrarPorTexto(texto);
                    System.out.println("");
                    break;
                case 3:
                    String titulo;
                    System.out.println("Introduce el titulo del libro");
                    titulo = scanner.nextLine();
                    biblioteca.mostrarPorTitulo(titulo);
                    System.out.println("");
                    break;
                case 4:
                    biblioteca.mostrarAlfabeticamente();
                    break;
                case 5:
                    biblioteca.mostrarPorTiempo();
                    break;
                case 6:
                    System.out.println("Has salido del programa");
            }
        }
        while(opcion != 6);
    } 
}
