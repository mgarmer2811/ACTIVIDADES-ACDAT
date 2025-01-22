/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Usuario14
 */
public class AutorDao {
    
    public static List<Autor> getAutores(){
        Session session = Conexion.getSession();
        List<Autor> autores = session.createQuery("SELECT a FROM Autor AS a",Autor.class).getResultList();
        return autores;
    }
    
    public static Autor getAutor(int id){
        Autor autor = new Autor();
        Session session = Conexion.getSession();
        autor = session.createQuery("SELECT a FROM Autor AS a WHERE a.id=" + id,Autor.class).getSingleResult();
        return autor;
    }
    
    public static void addAutor(){
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el nombre del autor");
        String nombre = scanner.nextLine();
        System.out.println("Introduzca la fecha de nacimiento del autor (aaaa-mm-dd)");
        String fechaNacimiento = scanner.nextLine();
        System.out.println("Introduzca la nacionalidad del autor");
        String nacionalidad = scanner.nextLine();
        System.out.println("Introduzca el numero de obras que ha realizado el autor");
        int numeroObras = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduzca una pequeña biografia del autor");
        String biografia = scanner.nextLine();
        
        Autor autor = new Autor(nombre,fechaNacimiento,nacionalidad,numeroObras,biografia);
        Session session = Conexion.getSession();
        session.persist(autor);
    }
    
    public static void updateAutor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el ID del autor que desea actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        Session session = Conexion.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Autor autor = session.get(Autor.class, id);

            if (autor == null) {
                System.out.println("No se encontró un autor con el ID proporcionado.");
                return;
            }
            
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

            session.merge(autor);
            transaction.commit();

            System.out.println("El autor ha sido actualizado correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Se ha producido un error\n" + e.getMessage());
        }
    }

    
    public static void deleteAutor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el ID del autor que desea eliminar:");
        int idAutor = scanner.nextInt();
        scanner.nextLine();

        Session session = Conexion.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Autor autor = session.get(Autor.class, idAutor);
            if (autor == null) {
                System.out.println("No se encontró un autor con el ID proporcionado.");
                return;
            }
            
            session.remove(autor);
        }
        catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
    }
}
