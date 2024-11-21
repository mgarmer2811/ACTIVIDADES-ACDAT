/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mgm.main;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Main {

    public static void main(String[] args) {
        int opcion;
        do{
            opcion = menu();
            switch(opcion){
                case 1:{
                    ArrayList<Pelicula> peliculas = PeliculaDAO.getPeliculas();
                    for(Pelicula pelicula : peliculas){
                        System.out.println(pelicula.toString());
                    }
                    break;
                }
                case 2:
                case 3:
                case 4:{
                    Scanner scanner = new Scanner(System.in);
                    
                    System.out.println("Introduce el nuevo titulo de la pelicula");
                    String nuevoTitulo = scanner.nextLine();
                    System.out.println("Introduce el nuevo director de la pelicula");
                    String nuevoDirector = scanner.nextLine();
                    System.out.println("Introduce el nuevo anyo de publicacion de la pelicula");
                    String nuevoAnyos = scanner.nextLine();
                    System.out.println("Introduce el nuevo genero de la pelicula");
                    String nuevoGenero = scanner.nextLine();
                    
                    int nuevoAnyo = 0;
                    if(!nuevoAnyos.isBlank()){
                        nuevoAnyo = Integer.parseInt(nuevoAnyos);
                    }
                    Pelicula pelicula = new Pelicula(nuevoTitulo,nuevoDirector,nuevoAnyo,nuevoGenero);
                    int filasAfectadas = PeliculaDAO.addPelicula(pelicula);
                    if (filasAfectadas > 0){
                        System.out.println("Se ha añadido la pelicula correctamente");
                    }
                    else{
                        System.out.println("Ha surgido algun error y NO se ha añadido la pelicula");
                    }
                    break;
                }
                case 5:{
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Introduce el ID de la película que deseas actualizar:");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Introduce el nuevo título de la película (o deja vacío para mantener el actual):");
                    String nuevoTitulo = scanner.nextLine();
                    System.out.println("Introduce el nuevo director de la película (o deja vacío para mantener el actual):");
                    String nuevoDirector = scanner.nextLine();
                    System.out.println("Introduce el nuevo año de publicación de la película (o deja vacío para mantener el actual):");
                    String nuevoAnyoStr = scanner.nextLine();
                    System.out.println("Introduce el nuevo género de la película (o deja vacío para mantener el actual):");
                    String nuevoGenero = scanner.nextLine();

                    int nuevoAnyo = 0;
                    if (!nuevoAnyoStr.isBlank()) {
                        nuevoAnyo = Integer.parseInt(nuevoAnyoStr);
                    }

                    Pelicula nuevaPelicula = new Pelicula(nuevoTitulo, nuevoDirector, nuevoAnyo, nuevoGenero);
                    int filasAfectadas = PeliculaDAO.updatePelicula(nuevaPelicula, id);

                    if (filasAfectadas > 0) {
                        System.out.println("La película se ha actualizado correctamente.");
                    } else {
                        System.out.println("No se pudo actualizar la película. Asegúrate de que el ID sea correcto.");
                    }
                    break;
                }
                case 6:{
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Introduce el ID de la película que deseas borrar:");
                    int id = scanner.nextInt();

                    int filasAfectadas = PeliculaDAO.deletePelicula(id);

                    if (filasAfectadas > 0) {
                        System.out.println("La película se ha eliminado correctamente.");
                    } else {
                        System.out.println("No se pudo eliminar la película. Asegúrate de que el ID sea correcto.");
                    }
                    break;
                }
                case 7:{
                    System.out.println("Has salido del programa");
                    PeliculaDAO.closeConnection();
                    break;
                }
                default:{
                    System.out.println("Opcion invalida");
                }
            }
        }
        while(opcion != 7);
    }
    
    public static int menu() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("1. Listar todas las peliculas");
        System.out.println("2. Buscar por titulo (fragmento)");
        System.out.println("3. Buscar por anyo");
        System.out.println("4. Anadir una pelicula");
        System.out.println("5. Actualizar una pelicula");
        System.out.println("6. Borrar una pelicula");
        System.out.println("7. Salir");
        System.out.println("Escoja una opcion:");
        int opcion = scanner.nextInt();
        
        return opcion;
    }
}
