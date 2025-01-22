///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mgm;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
///**
// *
// * @author Usuario14
// */
//public class LibroDao {
//    
//    public static ArrayList<Libro> getLibros(){
//        ArrayList<Libro> libros = new ArrayList<>();
//        
//        try {
//            String sqlSelect = "SELECT * FROM libro;";
//            PreparedStatement pSelect = Conexion.getPreparedStatement(sqlSelect);
//            ResultSet rsSelect = pSelect.executeQuery();
//            while (rsSelect.next()) {
//                int id = rsSelect.getInt("id");
//                String titulo = rsSelect.getString("titulo");
//                String fechaPublicacion = rsSelect.getString("fecha_publicacion");
//                String genero = rsSelect.getString("genero");
//                String isbn = rsSelect.getString("isbn");
//                String editorial = rsSelect.getString("editorial");
//                int idAutor = rsSelect.getInt("id_autor");
//                
//                Libro libro = new Libro(id, titulo, fechaPublicacion, genero, isbn, editorial, idAutor);
//                libros.add(libro);
//            }
//        } catch (SQLException sqle) {
//            System.err.println("Ha ocurrido un error\n" + sqle.getMessage());
//        }
//        return libros;
//    }
//    
//    public static Libro getLibro(int id){
//        Libro libro = new Libro();
//        
//        try{
//            String sqlSelect = "SELECT * FROM libro WHERE id=?;";
//            PreparedStatement pSelect = Conexion.getPreparedStatement(sqlSelect);
//            pSelect.setInt(1, id);
//            ResultSet rsSelect = pSelect.executeQuery();
//            
//            if(rsSelect.next()){
//                int idLibro = rsSelect.getInt("id");
//                String titulo = rsSelect.getString("titulo");
//                String fechaPublicacion = rsSelect.getString("fecha_publicacion");
//                String genero = rsSelect.getString("genero");
//                String isbn = rsSelect.getString("isbn");
//                String editorial = rsSelect.getString("editorial");
//                int idAutor = rsSelect.getInt("id_autor");
//                
//                libro.setId(idLibro);
//                libro.setTitulo(titulo);
//                libro.setFechaPublicacion(fechaPublicacion);
//                libro.setGenero(genero);
//                libro.setIsbn(isbn);
//                libro.setEditorial(editorial);
//                libro.setIdAutor(idAutor);
//            }
//        }
//        catch(SQLException sqle){
//            System.err.println("Ha ocurrido un error\n" + sqle.getMessage());
//        }
//        return libro;
//    }
//    
//    public static void addLibro() {
//        Scanner scanner = new Scanner(System.in);
//        
//        System.out.println("Introduzca el título del libro:");
//        String titulo = scanner.nextLine();
//        System.out.println("Introduzca la fecha de publicación del libro (aaaa-mm-dd):");
//        String fechaPublicacion = scanner.nextLine();
//        System.out.println("Introduzca el género del libro:");
//        String genero = scanner.nextLine();
//        System.out.println("Introduzca el isbn del libro");
//        String isbn = scanner.nextLine();
//        System.out.println("Introduzca la editorial del libro");
//        String editorial = scanner.nextLine();
//        System.out.println("Introduzca el id del autor del libro");
//        int idAutor = scanner.nextInt();
//        scanner.nextLine();
//        
//        try {
//            String sqlSelectAutor = "SELECT * FROM autor WHERE id = ?;";
//            PreparedStatement pSelectAutor = Conexion.getPreparedStatement(sqlSelectAutor);
//            pSelectAutor.setInt(1, idAutor);
//            ResultSet rsSelectAutor = pSelectAutor.executeQuery();
//            
//            if(!rsSelectAutor.next()){
//                System.err.println("El autor con el ID proporcionado no existe en el sistema");
//                return;
//            }
//            
//            String sqlSelect = "SELECT * FROM libro WHERE titulo = ? AND id_autor = ?;";
//            PreparedStatement pSelect = Conexion.getPreparedStatement(sqlSelect);
//            pSelect.setString(1, titulo);
//            pSelect.setInt(2, idAutor);
//            ResultSet rsSelect = pSelect.executeQuery();
//            
//            if (!rsSelect.next()) {
//                String sqlInsert = "INSERT INTO libro(titulo, fecha_publicacion, genero, editorial, id_autor) VALUES(?,?,?,?,?)";
//                PreparedStatement pInsert = Conexion.getPreparedStatement(sqlInsert);
//                pInsert.setString(1, titulo);
//                pInsert.setString(2, fechaPublicacion);
//                pInsert.setString(3, genero);
//                pInsert.setString(4, editorial);
//                pInsert.setInt(5, idAutor);
//                
//                int filasAfectadas = pInsert.executeUpdate();
//                if (filasAfectadas < 1) {
//                    throw new SQLException();
//                }
//                System.out.println("El libro ha sido añadido al sistema.");
//            } else {
//                throw new SQLException("El libro ya está en el sistema.");
//            }
//        } catch (SQLException sqle) {
//            System.err.println("Se ha producido un error\n" + sqle.getMessage());
//        }
//    }
//    
//    public static void updateLibro() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Introduzca el ID del libro que desea actualizar:");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//
//        try {
//            String sqlSelect = "SELECT * FROM libro WHERE id = ?";
//            PreparedStatement pSelect = Conexion.getPreparedStatement(sqlSelect);
//            pSelect.setInt(1, id);
//            ResultSet rsSelect = pSelect.executeQuery();
//
//            if (!rsSelect.next()) {
//                System.out.println("No se encontró un libro con el ID proporcionado.");
//                return;
//            }
//
//            String tituloActual = rsSelect.getString("titulo");
//            int autorActual = rsSelect.getInt("id_autor");
//            String fechaPublicacionActual = rsSelect.getString("fecha_publicacion");
//            String generoActual = rsSelect.getString("genero");
//            String editorialActual = rsSelect.getString("editorial");
//
//            System.out.println("Introduzca el nuevo título del libro (deje en blanco para no modificar):");
//            String titulo = scanner.nextLine();
//            System.out.println("Introduzca el ID del nuevo autor del libro (deje en blanco para no modificar):");
//            int autor = scanner.nextInt();
//            scanner.nextLine();
//            
//            if(autor != autorActual){
//                String sqlSelectAutorExiste = "SELECT * FROM autor WHERE id = ?;";
//                PreparedStatement pSelectAutorExiste = Conexion.getPreparedStatement(sqlSelectAutorExiste);
//                pSelectAutorExiste.setInt(1, autor);
//                ResultSet rsSelectAutorExiste = pSelectAutorExiste.executeQuery();
//                
//                if (!rsSelectAutorExiste.next()) {
//                    System.err.println("El autor con el ID proporcionado no existe en el sistema");
//                    return;
//                }
//            }
//            
//            System.out.println("Introduzca la nueva fecha de publicación del libro (aaaa-mm-dd, deje en blanco para no modificar):");
//            String fechaPublicacion = scanner.nextLine();
//            System.out.println("Introduzca el nuevo género del libro (deje en blanco para no modificar):");
//            String genero = scanner.nextLine();
//            System.out.println("Introduzca la nueva editorial del libro (deje en blanco para no modificar):");
//            String editorial = scanner.nextLine();
//
//            StringBuilder sqlUpdate = new StringBuilder("UPDATE libro SET ");
//            boolean faltaComa = false;
//
//            if (!titulo.isBlank() && !titulo.equals(tituloActual)) {
//                sqlUpdate.append("titulo = ? ");
//                faltaComa = true;
//            }
//            if (!fechaPublicacion.isBlank() && !fechaPublicacion.equals(fechaPublicacionActual)) {
//                if (faltaComa) {
//                    sqlUpdate.append(", ");
//                }
//                sqlUpdate.append("fecha_publicacion = ? ");
//                faltaComa = true;
//            }
//            if (!genero.isBlank() && !genero.equals(generoActual)) {
//                if (faltaComa) {
//                    sqlUpdate.append(", ");
//                }
//                sqlUpdate.append("genero = ? ");
//                faltaComa = true;
//            }
//            if (!editorial.isBlank() && !editorial.equals(editorialActual)) {
//                if (faltaComa) {
//                    sqlUpdate.append(", ");
//                }
//                sqlUpdate.append("editorial = ? ");
//                faltaComa = true;
//            }
//            if (sqlUpdate.length() == "UPDATE libro SET ".length()) {
//                System.out.println("No se ha realizado ningún cambio en el libro.");
//                return;
//            }
//
//            sqlUpdate.append("WHERE id = ?");
//            PreparedStatement pUpdate = Conexion.getPreparedStatement(sqlUpdate.toString());
//
//            int indice = 1;
//
//            if (!titulo.isBlank() && !titulo.equals(tituloActual)) {
//                pUpdate.setString(indice++, titulo);
//            }
//            if (!fechaPublicacion.isBlank() && !fechaPublicacion.equals(fechaPublicacionActual)) {
//                pUpdate.setString(indice++, fechaPublicacion);
//            }
//            if (!genero.isBlank() && !genero.equals(generoActual)) {
//                pUpdate.setString(indice++, genero);
//            }
//            if (!editorial.isBlank() && !editorial.equals(editorialActual)) {
//                pUpdate.setString(indice++, editorial);
//            }
//            pUpdate.setInt(indice, id);
//
//            int filasAfectadas = pUpdate.executeUpdate();
//            if (filasAfectadas < 1) {
//                throw new SQLException("No se pudo actualizar el libro.");
//            }
//            System.out.println("El libro ha sido actualizado correctamente.");
//        } catch (SQLException sqle) {
//            System.err.println("Se ha producido un error\n" + sqle.getMessage());
//        }
//    }
//    
//    public static void deleteLibro() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Introduzca el ID del libro que desea eliminar:");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//
//        try {
//            String sqlSelect = "SELECT * FROM libro WHERE id = ?";
//            PreparedStatement pSelect = Conexion.getPreparedStatement(sqlSelect);
//            pSelect.setInt(1, id);
//            ResultSet rsSelect = pSelect.executeQuery();
//
//            if (!rsSelect.next()) {
//                System.out.println("No se encontró un libro con el ID proporcionado.");
//                return;
//            }
//
//            String sqlDelete = "DELETE FROM libro WHERE id = ?";
//            PreparedStatement pDelete = Conexion.getPreparedStatement(sqlDelete);
//            pDelete.setInt(1, id);
//
//            int filasAfectadas = pDelete.executeUpdate();
//            if (filasAfectadas < 1) {
//                throw new SQLException("No se pudo eliminar el libro.");
//            }
//            System.out.println("El libro ha sido eliminado correctamente.");
//        } catch (SQLException sqle) {
//            System.err.println("Se ha producido un error\n" + sqle.getMessage());
//        }
//    }
//}
