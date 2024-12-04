/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.ejercicio9_maven;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class LibroDao {
    private Conexion connection;
    
    public LibroDao(Conexion connection){
        this.connection = connection;
    }
    
    public ArrayList<Libro> getBooks(){
        String sql = "SELECT * FROM libro;";
        ArrayList<Libro> books = new ArrayList<>();
        try{
            PreparedStatement stmt = connection.getPreparedStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int anyo_publicacion = rs.getInt("anyo_publicacion");
                int cantidad_disponible = rs.getInt("cantidad_disponible");
                String categoria = rs.getString("categoria");
                
                Libro book = new Libro(id,titulo,autor,anyo_publicacion,cantidad_disponible,categoria);
                books.add(book);
            }
        }
        catch(SQLException sqle){
            System.err.println("Ha ocurrido un error\n" + sqle.getMessage());
        }
        if(books.size() > 0){
            return books;
        }
        else{
            return null;
        }
    }
    
    public void updateDisponibilidad(int id, int quantity) throws SQLException{
        if(quantity < 0){
            reduceDisponibilidad(id);
        }
        else{
            addDisponibilidad(id);
        }
    }
    
    private void addDisponibilidad(int id) throws SQLException{
        String sql = "UPDATE libro SET cantidad_disponible = cantidad_disponible + 1 WHERE id = ?";
        PreparedStatement stmt = connection.getPreparedStatement(sql);
        stmt.setInt(1, id);
        if(stmt.executeUpdate() == 0){
            throw new SQLException();
        }
    }
    
    private void reduceDisponibilidad(int id) throws SQLException{
        String sql = "UPDATE libro SET cantidad_disponible = cantidad_disponible - 1 WHERE id = ? AND cantidad_disponible > 0";
        PreparedStatement stmt = connection.getPreparedStatement(sql);
        stmt.setInt(1, id);
        if(stmt.executeUpdate() == 0){
            System.out.println("Libro no disponible");
            throw new SQLException();
        }
    }
}
