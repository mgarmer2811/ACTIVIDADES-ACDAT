/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;
import com.mgm.bibliotecaXML.*;
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
        int option = 0;
        Scanner scanner = new Scanner(System.in);
        
//        Autor autor1 = new Autor(1,"Irene","Vallejo");
//        Autor autor2 = new Autor(2,"Federico","Garcia Lorca");
//        Libro libro1 = new Libro(1,"El infinito en un junco",autor1,"Siruela",2022);
//        Libro libro2 = new Libro(2,"Poeta en Nueva York",autor2,"Seneca",1940);
//        Libro libro3 = new Libro(3,"El silbido del arquero",autor1,"Contrasena editorial",2015);
        
//        Biblioteca.addAutor(autor1);
//        Biblioteca.addAutor(autor2);
//        Biblioteca.addLibro(libro1);
//        Biblioteca.addLibro(libro2);
//        Biblioteca.addLibro(libro3);

//          Biblioteca.loadAutores();
//          Biblioteca.loadLibros();
        Biblioteca.loadAutoresXML();
        Biblioteca.loadLibrosXML();
        
        do{
//            System.out.println("1. Mostrar todos los libros");
//            System.out.println("2. Mostrar libros segun nombre/apellido del autor");
//            System.out.println("3. Mostrar libros segun titulo");
//            System.out.println("4. Mostrar libros por orden alfabetico del apellidos del autor");
//            System.out.println("5. Mostrar libros segun reciente publicacion");
            System.out.println("1. Anadir un autor");
            System.out.println("2. Anadir un libro");
            System.out.println("3. Mostrar todos los libros");
            System.out.println("4. Buscar por autor");
            System.out.println("5. Buscar por titulo");
            System.out.println("6. Salir");
            System.out.print("Escoja una opcion: ");
            option = scanner.nextInt();
            scanner.nextLine(); // To empty the buffer
            
            switch(option){
                case 1:
//                    System.out.println("");
//                    Biblioteca.listLibros();
//                    System.out.println("");
                    
                    
                    System.out.println("");
                    System.out.println("");
                    int idAutor;
                    String nombreAutor;
                    String apellidosAutor;
                    System.out.println("Introduce el id del autor"); // Im not checking the id to be unique
                    idAutor = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Introduce el nombre del autor");
                    nombreAutor = scanner.nextLine();
                    System.out.println("Introduce el/los apellido(s) del autor");
                    apellidosAutor = scanner.nextLine();
                    Autor autor = new Autor(idAutor,nombreAutor,apellidosAutor);
//                      Biblioteca.addAutor(autor);
//                      Biblioteca.storeAutor(autor);
                    Biblioteca.storeAutorXML(autor);
                    System.out.println("");
                    break;
                case 2:
//                    System.out.println("");
//                    System.out.println("Introduzca el nombre/apellido(s) del autor");
//                    String text = scanner.nextLine();
//                    Biblioteca.listLibrosByName(text);
//                    System.out.println("");
                    
                    
                    System.out.println("");
                    System.out.println("");
                    int idLibro;
                    String tituloLibro;
                    int idAutor2;
                    String editorialLibro;
                    int anyoLibro;
                    Autor autor2 = null;
                    System.out.println("Introduce el id del libro");
                    idLibro = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Introduce el titulo del libro");
                    tituloLibro = scanner.nextLine();
                    System.out.println("");
                    System.out.println("");
                    Biblioteca.listAutores();
                    System.out.println("Introduce el id del autor");
                    idAutor2 = scanner.nextInt();
                    scanner.nextLine();
                    autor2 = Biblioteca.getAutorById(idAutor2);
                    System.out.println("Introduce la editorial del libro");
                    editorialLibro = scanner.nextLine();
                    System.out.println("Introduce el anyo de publicacion del libro");
                    anyoLibro = scanner.nextInt();
                    Libro libro = new Libro(idLibro,tituloLibro,autor2,editorialLibro,anyoLibro);
//                      Biblioteca.addLibro(libro);
//                      Biblioteca.storeLibro(libro);
                    Biblioteca.storeLibroXML(libro);
                    System.out.println("");
                    break;
                case 3:
//                    System.out.println("");
//                    System.out.println("Introduzca el titulo del libro");
//                    String text2 = scanner.nextLine();
//                    Biblioteca.listLibrosByTitle(text2);
//                    System.out.println("");
                    
                    System.out.println("");
                    System.out.println("");
                    Biblioteca.listLibros();
                    System.out.println("");
                    break;
                case 4:
//                    System.out.println("");
//                    Biblioteca.listLibrosAlphabetically();
//                    System.out.println("");
                    
                    System.out.println("");
                    System.out.println("");
                    String name;
                    scanner.nextLine();
                    System.out.println("Introduce el nombre/apellido(s) del autor");
                    name = scanner.nextLine();
                    Biblioteca.listLibrosByName(name);
                    System.out.println("");
                    break;
                case 5:
//                    System.out.println("");
//                    Biblioteca.listLibrosByYear();
//                    System.out.println("");
                    
                    System.out.println("");
                    System.out.println("");
                    String title;
                    scanner.nextLine();
                    System.out.println("Introduce el titulo del libro");
                    title = scanner.nextLine();
                    Biblioteca.listLibrosByTitle(title);
                    System.out.println("");
                    break;
                case 6:
                    System.out.println("");
                    System.out.println("Has salido del programa");
                    break;
            }
        
        }while(option != 6);
        
    }
    
}
