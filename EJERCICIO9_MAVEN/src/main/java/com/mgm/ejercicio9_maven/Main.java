/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mgm.ejercicio9_maven;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Main {
    private static Conexion con;
    private static LibroDao libroDao = new LibroDao(con);
    private static PrestamoDao prestamoDao = new PrestamoDao(con);
    static int option = 0;
    
    public static void main(String[] args) {
        do{
            option = menu();
            con = new Conexion("jdbc:mysql://127.0.0.1:3306/ad_ej9",",2dam",",2dam");
            
            switch(option){
                case 1:{
                    listBooks();
                    break;
                }
                case 2:{
                    registerPrestamo();
                    break;
                }
                case 3:{
                    returnPrestamo();
                    break;
                }
                case 4:{
                    listPrestamosPendientes();
                    break;
                }
                case 5:{
                    listPrestamos();
                    break;
                }
                case 6:{
                    System.out.println("Has salido del programa correctamente");
                    break;
                }
                default:{
                    System.out.println("Opcion incorrecta");
                }
            }
        }
        while(option != 6);
    }
    
    public static void listBooks(){
        ArrayList<Libro> books = libroDao.getBooks();
        if(books != null){
            System.out.println("Libros (ID,titulo,autor,anio publicacion, cantidad disponible, categoria");
            for(Libro book : books){
                System.out.println(book.toString());
            }
        }
        else{
            System.out.println("No se han podido listar los libros, intentalo mas tarde");
        }
    }
    
    public static void registerPrestamo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca ID del libro");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduzca nombre del lector");
        String lector = scanner.nextLine();
        System.out.println("Introduzca la fecha del prestamo {AAAA-DD-MM}");
        String fecha = scanner.nextLine();
        Prestamo newPrestamo = new Prestamo(id,lector,fecha);
        
        if(prestamoDao.insertPrestamo(newPrestamo)){
            System.out.println("Prestamo registrado correctamente!");
        }
        else{
            System.out.println("No se pudo registrar el prestamo, intentelo de nuevo");
        }
    }
    
    public static void returnPrestamo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el id del prestamo");
        int id = scanner.nextInt();
        
        if(prestamoDao.returnPrestamo(id)){
            System.out.println("Se ha devuelto el libro");
        }
        else{
            System.out.println("Error, NO se ha devuelto el libro");
        }
    }
    
    public static void listPrestamosPendientes(){
        ArrayList<Prestamo> prestamos = prestamoDao.getPrestamosPendientes();
        if(prestamos !=  null){
            System.out.println("Prestamos (ID,ID libro,lector,fecha,estado)");
            for(Prestamo prestamo : prestamos){
                System.out.println(prestamo.toString());
            }
        }
        else{
            System.out.println("No se han podido listar los prestamos, intentelo mas tarde");
        }
    }
    
    public static void listPrestamos(){
        ArrayList<Prestamo> prestamos = prestamoDao.getPrestamos();
        if(prestamos != null){
            System.out.println("Prestamos (ID,ID libro,lector,fecha,estado)");
            for(Prestamo prestamo : prestamos){
                System.out.println(prestamo.toString());
            }
        }
        else{
            System.out.println("No se han podido listar los prestamos, intentelo mas tarde");
        }
    }
    
    public static int menu(){
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        
        System.out.println("1. Listar libros");
        System.out.println("2. Registrar un prestamo");
        System.out.println("3. Devolver un prestamo");
        System.out.println("4. Listar prestamos pendientes");
        System.out.println("5. Listar prestamos");
        System.out.println("6. Salir");
        System.out.println("Escoja opcion");
        option = scanner.nextInt();
        
        return option;
    }
}
