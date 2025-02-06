/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm;

import java.util.Scanner;
import java.util.List;

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
        List<Autor> autores = AutorDao.getAutores();
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
        List<Libro> libros = LibroDao.getLibros();
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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el nombre del autor:");
        String nombre = scanner.nextLine();
        System.out.println("Introduzca la fecha de nacimiento del autor (aaaa-mm-dd):");
        String fechaNacimiento = scanner.nextLine();
        System.out.println("Introduzca la nacionalidad del autor:");
        String nacionalidad = scanner.nextLine();
        System.out.println("Introduzca el número de obras que ha realizado el autor:");
        int numeroObras = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduzca una pequeña biografía del autor:");
        String biografia = scanner.nextLine();

        Autor autor = new Autor(nombre, fechaNacimiento, nacionalidad, numeroObras, biografia);
        AutorDao.addAutor(autor);
    }
    
    public static void agregarLibro(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el título del libro:");
        String titulo = scanner.nextLine();
        System.out.println("Introduzca la fecha de publicación del libro (aaaa-mm-dd):");
        String fechaPublicacion = scanner.nextLine();
        System.out.println("Introduzca el género del libro:");
        String genero = scanner.nextLine();
        System.out.println("Introduzca el ISBN del libro:");
        String isbn = scanner.nextLine();
        System.out.println("Introduzca la editorial del libro:");
        String editorial = scanner.nextLine();
        System.out.println("Introduzca el ID del autor del libro:");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Autor autor = AutorDao.getAutor(id);
        Libro libro = new Libro(titulo, fechaPublicacion, genero, isbn, editorial, autor);
        LibroDao.addLibro(libro);
    }
    
    public static void actualizarAutor(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el ID del autor que desea actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Autor autor = AutorDao.getAutor(id); // session.get(Autor.class,id)
        
        System.out.println("Nombre actual: " + autor.getNombre());
        System.out.println("Fecha de nacimiento actual: " + autor.getFechaNacimiento());
        System.out.println("Nacionalidad actual: " + autor.getNacionalidad());
        System.out.println("Número de obras actual: " + autor.getNumeroObras());
        System.out.println("Biografía actual: " + autor.getBiografia());
        
        System.out.println("Introduzca el nuevo nombre del autor (deje en blanco para no modificar):");
        String nombre = scanner.nextLine();
        if (!nombre.isBlank()) {
            autor.setNombre(nombre);
        }

        System.out.println("Introduzca la nueva fecha de nacimiento del autor (aaaa-mm-dd, deje en blanco para no modificar):");
        String fechaNacimiento = scanner.nextLine();
        if (!fechaNacimiento.isBlank()) {
            autor.setFechaNacimiento(fechaNacimiento);
        }

        System.out.println("Introduzca la nueva nacionalidad del autor (deje en blanco para no modificar):");
        String nacionalidad = scanner.nextLine();
        if (!nacionalidad.isBlank()) {
            autor.setNacionalidad(nacionalidad);
        }

        System.out.println("Introduzca el nuevo número de obras realizadas por el autor (deje en blanco para no modificar):");
        String numeroObrasInput = scanner.nextLine();
        if (!numeroObrasInput.isBlank()) {
            autor.setNumeroObras(Integer.parseInt(numeroObrasInput));
        }

        System.out.println("Introduzca la nueva biografía del autor (deje en blanco para no modificar):");
        String biografia = scanner.nextLine();
        if (!biografia.isBlank()) {
            autor.setBiografia(biografia);
        }
        AutorDao.updateAutor(autor);
    }
    
    public static void actualizarLibro(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el ID del libro que desea actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Libro libro = LibroDao.getLibro(id);
        
        System.out.println("Título actual: " + libro.getTitulo());
        System.out.println("Introduzca el nuevo título del libro (deje en blanco para no modificar):");
        String titulo = scanner.nextLine();
        if (!titulo.isBlank()) {
            libro.setTitulo(titulo);
        }

        System.out.println("Fecha de publicación actual: " + libro.getFechaPublicacion());
        System.out.println("Introduzca la nueva fecha de publicación (aaaa-mm-dd, deje en blanco para no modificar):");
        String fechaPublicacion = scanner.nextLine();
        if (!fechaPublicacion.isBlank()) {
            libro.setFechaPublicacion(fechaPublicacion);
        }

        System.out.println("Género actual: " + libro.getGenero());
        System.out.println("Introduzca el nuevo género del libro (deje en blanco para no modificar):");
        String genero = scanner.nextLine();
        if (!genero.isBlank()) {
            libro.setGenero(genero);
        }

        System.out.println("ISBN actual: " + libro.getIsbn());
        System.out.println("Introduzca el nuevo ISBN del libro (deje en blanco para no modificar):");
        String isbn = scanner.nextLine();
        if (!isbn.isBlank()) {
            libro.setIsbn(isbn);
        }

        System.out.println("Editorial actual: " + libro.getEditorial());
        System.out.println("Introduzca la nueva editorial del libro (deje en blanco para no modificar):");
        String editorial = scanner.nextLine();
        if (!editorial.isBlank()) {
            libro.setEditorial(editorial);
        }
        
        LibroDao.updateLibro(libro);
    }
    
    public static void eliminarAutor(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el ID del autor que desea actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Autor autor = AutorDao.getAutor(id);
        AutorDao.deleteAutor(autor);
    }
    
    public static void eliminarLibro(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el ID del libro que desea eliminar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Libro libro = LibroDao.getLibro(id);
        LibroDao.deleteLibro(libro);
    }
    
    public static void close(){
        Conexion.close();
    }
}
