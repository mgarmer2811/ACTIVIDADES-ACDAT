/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario14
 */
public class Controller {
    public static int menu(){
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        
        System.out.println("\n--- Menú ---");
        System.out.println("1. Listar Autores");
        System.out.println("2. Listar Autor por ID");
        System.out.println("3. Listar Libros");
        System.out.println("4. Listar Libro por ID");
        System.out.println("5. Agregar Autor");
        System.out.println("6. Agregar Libro");
        System.out.println("7. Actualizar Autor");
        System.out.println("8. Actualizar Libro");
        System.out.println("9. Eliminar Autor");
        System.out.println("10. Eliminar Libro");
        System.out.println("11. Salir");
        System.out.print("Seleccione una opción: ");
        option = scanner.nextInt();
        
        return option;
    }
    
    public static void listarAutores(){
        ArrayList<Autor> autores = AutorDao.getAutores();
        for(Autor autor : autores){
            System.out.println(autor.toString());
        }
    }
    
    public static void listarAutorID(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el id del autor");
        int id = scanner.nextInt();
        
        Autor autor = AutorDao.getAutor(id);
        System.out.println(autor.toString());
    }
    
    public static void listarLibros(){
        ArrayList<Libro> libros = LibroDao.getLibros();
        for(Libro libro : libros){
            System.out.println(libro.toString());
        }
    }
    
    public static void listarLibrosID(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el id del libro");
        int id = scanner.nextInt();
        
        Libro libro = LibroDao.getLibro(id);
        System.out.println(libro.toString());
    }
    
    public static void agregarAutor(){
        AutorDao.addAutor();
    }
    
    public static void agregarLibro(){
        LibroDao.addLibro();
    }
    
    public static void actualizarAutor(){
        AutorDao.updateAutor();
    }
    
    public static void actualizarLibro(){
        LibroDao.updateLibro();
    }
    
    public static void eliminarAutor(){
        AutorDao.deleteAutor();
    }
    
    public static void eliminarLibro(){
        LibroDao.deleteLibro();
    }
}
