/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario14
 */
public class AutorDao {
    
    public static ArrayList<Autor> getAutores(){
        ArrayList<Autor> autores = new ArrayList<>();
        
        try{
            String sqlSelect = "SELECT * FROM autor;";
            PreparedStatement pSelect = Conexion.getPreparedStatement(sqlSelect);
            ResultSet rsSelect = pSelect.executeQuery();
            while(rsSelect.next()){
                int id = rsSelect.getInt("id");
                String nombre = rsSelect.getString("nombre");
                String fechaNacimiento = rsSelect.getString("fecha_nacimiento");
                String nacionalidad = rsSelect.getString("nacionalidad");
                int numeroObras = rsSelect.getInt("numero_obras");
                String biografia = rsSelect.getString("biografia");
                
                Autor autor = new Autor(id,nombre,fechaNacimiento,nacionalidad,numeroObras,biografia);
                autores.add(autor);
            }
        }
        catch(SQLException sqle){
            System.err.println("Ha ocurrido un error\n" + sqle.getMessage());
        }
        return autores;
    }
    
    public static Autor getAutor(int id){
        Autor autor = new Autor();
        
        try{
            String sqlSelect = "SELECT * FROM autor WHERE id=?;";
            PreparedStatement pSelect = Conexion.getPreparedStatement(sqlSelect);
            pSelect.setInt(1,id);
            ResultSet rsSelect = pSelect.executeQuery();
            
            if(rsSelect.next()){
                int idAutor = rsSelect.getInt("id");
                String nombre = rsSelect.getString("nombre");
                String fechaNacimiento = rsSelect.getString("fecha_nacimiento");
                String nacionalidad = rsSelect.getString("nacionalidad");
                int numeroObras = rsSelect.getInt("numero_obras");
                String biografia = rsSelect.getString("biografia");
                
                autor.setId(idAutor);
                autor.setNombre(nombre);
                autor.setFechaNacimiento(fechaNacimiento);
                autor.setNacionalidad(nacionalidad);
                autor.setNumeroObras(numeroObras);
                autor.setBiografia(biografia);
            }
        }
        catch(SQLException sqle){
            System.err.println("Ha ocurrido un error\n" + sqle.getMessage());
        }
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
        
        try{
            String sqlSelect = "SELECT * FROM autor WHERE nombre=? AND fecha_nacimiento=? AND nacionalidad=?;";
            PreparedStatement pSelect = Conexion.getPreparedStatement(sqlSelect);
            pSelect.setString(1, nombre);
            pSelect.setString(2, fechaNacimiento);
            pSelect.setString(3, nacionalidad);
            ResultSet rsSelect = pSelect.executeQuery();
            if(!rsSelect.next()){
                String sqlInsert = "INSERT INTO autor(nombre,fecha_nacimiento,nacionalidad,numero_obras,biografia) VALUES(?,?,?,?,?)";
                PreparedStatement pInsert = Conexion.getPreparedStatement(sqlInsert);
                pInsert.setString(1, nombre);
                pInsert.setString(2, fechaNacimiento);
                pInsert.setString(3, nacionalidad);
                pInsert.setInt(4, numeroObras);
                pInsert.setString(5, biografia);
                
                int filasAfectadas = pInsert.executeUpdate();
                if(filasAfectadas < 1){
                    throw new SQLException();
                }
                System.out.println("El autor ha sido añadido al sistema");
            }
            else{
                throw new SQLException("No se ha añadido el autor porque ya esta en el sistema");
            }
        }
        catch(SQLException sqle){
            System.err.println("Se ha producido un error\n" + sqle.getMessage());
        }
    }
    
    public static void updateAutor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el ID del autor que desea actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            String sqlSelect = "SELECT * FROM autor WHERE id = ?";
            PreparedStatement pSelect = Conexion.getPreparedStatement(sqlSelect);
            pSelect.setInt(1, id);
            ResultSet rsSelect = pSelect.executeQuery();

            if (!rsSelect.next()) {
                System.out.println("No se encontró un autor con el ID proporcionado.");
                return;
            }

            String nombreActual = rsSelect.getString("nombre");
            String fechaNacimientoActual = rsSelect.getString("fecha_nacimiento");
            String nacionalidadActual = rsSelect.getString("nacionalidad");
            int numeroObrasActual = rsSelect.getInt("numero_obras");
            String biografiaActual = rsSelect.getString("biografia");
            
            System.out.println("Introduzca el nuevo nombre del autor (deje en blanco para no modificar):");
            String nombre = scanner.nextLine();
            System.out.println("Introduzca la nueva fecha de nacimiento del autor (aaaa-mm-dd, deje en blanco para no modificar):");
            String fechaNacimiento = scanner.nextLine();
            System.out.println("Introduzca la nueva nacionalidad del autor (deje en blanco para no modificar):");
            String nacionalidad = scanner.nextLine();
            System.out.println("Introduzca el nuevo número de obras realizadas por el autor (deje en blanco para no modificar):");
            int numeroObras = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Introduzca la nueva biografía del autor (deje en blanco para no modificar):");
            String biografia = scanner.nextLine();

            StringBuilder sqlUpdate = new StringBuilder("UPDATE autor SET ");
            boolean faltaComa = false;

            if (!nombre.isBlank() && !nombre.equals(nombreActual)) {
                sqlUpdate.append("nombre = ?");
                faltaComa = true;
            }
            if (!fechaNacimiento.isBlank() && !fechaNacimiento.equals(fechaNacimientoActual)) {
                if (faltaComa) {
                    sqlUpdate.append(", ");
                }
                sqlUpdate.append("fecha_nacimiento = ?");
                faltaComa = true;
            }
            if (!nacionalidad.isBlank() && !nacionalidad.equals(nacionalidadActual)) {
                if (faltaComa) {
                    sqlUpdate.append(", ");
                }
                sqlUpdate.append("nacionalidad = ?");
                faltaComa = true;
            }
            if (numeroObras >= 0 && numeroObras != numeroObrasActual) {
                if (faltaComa) {
                    sqlUpdate.append(", ");
                }
                sqlUpdate.append("numero_obras = ?");
                faltaComa = true;
            }
            if (!biografia.isBlank() && !biografia.equals(biografiaActual)) {
                if (faltaComa) {
                    sqlUpdate.append(", ");
                }
                sqlUpdate.append("biografia = ?");
            }

            if (sqlUpdate.length() == "UPDATE autor SET ".length()) {
                System.out.println("No se ha realizado ningún cambio en el autor.");
                return;
            }

            sqlUpdate.append(" WHERE id = ?");
            PreparedStatement pUpdate = Conexion.getPreparedStatement(sqlUpdate.toString());

            int indice = 1;

            if (!nombre.isBlank() && !nombre.equals(nombreActual)) {
                pUpdate.setString(indice++, nombre);
            }
            if (!fechaNacimiento.isBlank() && !fechaNacimiento.equals(fechaNacimientoActual)) {
                pUpdate.setString(indice++, fechaNacimiento);
            }
            if (!nacionalidad.isBlank() && !nacionalidad.equals(nacionalidadActual)) {
                pUpdate.setString(indice++, nacionalidad);
            }
            if (numeroObras >= 0 && numeroObras != numeroObrasActual) {
                pUpdate.setInt(indice++, numeroObras);
            }
            if (!biografia.isBlank() && !biografia.equals(biografiaActual)) {
                pUpdate.setString(indice++, biografia);
            }

            pUpdate.setInt(indice, id);

            int filasAfectadas = pUpdate.executeUpdate();
            if (filasAfectadas < 1) {
                throw new SQLException("No se pudo actualizar el autor.");
            }
            System.out.println("El autor ha sido actualizado correctamente.");
        } catch (SQLException sqle) {
            System.err.println("Se ha producido un error\n" + sqle.getMessage());
        }
    }
    
    public static void deleteAutor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el ID del autor que desea eliminar:");
        int idAutor = scanner.nextInt();
        scanner.nextLine();

        try {
            String sqlSelectAutor = "SELECT * FROM autor WHERE id = ?";
            PreparedStatement pSelectAutor = Conexion.getPreparedStatement(sqlSelectAutor);
            pSelectAutor.setInt(1, idAutor);
            ResultSet rsAutor = pSelectAutor.executeQuery();

            if (!rsAutor.next()) {
                System.out.println("No se encontró un autor con el ID proporcionado.");
                return;
            }

            String sqlDeleteLibros = "DELETE FROM libro WHERE id_autor = ?";
            PreparedStatement pDeleteLibros = Conexion.getPreparedStatement(sqlDeleteLibros);
            pDeleteLibros.setInt(1, idAutor);
            int filasAfectadasLibros = pDeleteLibros.executeUpdate();
            if (filasAfectadasLibros > 0) {
                System.out.println("Se han eliminado " + filasAfectadasLibros + " libro(s) asociado(s) al autor.");
            }

            String sqlDeleteAutor = "DELETE FROM autor WHERE id = ?";
            PreparedStatement pDeleteAutor = Conexion.getPreparedStatement(sqlDeleteAutor);
            pDeleteAutor.setInt(1, idAutor);
            int filasAfectadas = pDeleteAutor.executeUpdate();

            if (filasAfectadas < 1) {
                throw new SQLException("No se pudo eliminar el autor.");
            }
            System.out.println("El autor ha sido eliminado correctamente.");
        } catch (SQLException sqle) {
            System.err.println("Se ha producido un error\n" + sqle.getMessage());
        }
    }
}
