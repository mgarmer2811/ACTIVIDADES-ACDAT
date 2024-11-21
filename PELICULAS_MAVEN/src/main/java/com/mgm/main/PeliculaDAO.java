/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class PeliculaDAO {
    private static Connection conexion = Conexion.getConexion();
    private static final int ERROR_CODE = -1;
    
    private PeliculaDAO() {
        throw new UnsupportedOperationException("Esta clase no puede ser instanciada");
    }
    
    public static ArrayList<Pelicula> getPeliculas(){
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT * FROM pelicula;";
        
        try{
            PreparedStatement stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String director = rs.getString("director");
                int anyo = rs.getInt("anyo");
                String genero = rs.getString("genero");
                
                Pelicula pelicula = new Pelicula(id,titulo,director,anyo,genero);
                peliculas.add(pelicula);
            }
        }
        catch(SQLException sqle){
            System.err.println("Se ha producido un error obteniendo de las peliculas");
        }
        return peliculas;
    }
    
    public static int addPelicula(Pelicula pelicula){
        int filasAfectadas = ERROR_CODE;
        String sql = "INSERT INTO pelicula (titulo,director,anyo,genero) VALUES (?,?,?,?);";
        try{
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, pelicula.getTitulo());
            stmt.setString(2, pelicula.getDirector());
            stmt.setInt(3, pelicula.getAnyo());
            stmt.setString(4, pelicula.getGenero());
            filasAfectadas = stmt.executeUpdate();
        }
        catch(SQLException slqe){
            System.err.println("Se ha producido un error añadiendo la pelicula");
        }
        
        return filasAfectadas;
    }
    
    public static int updatePelicula(Pelicula peli, int id){
        int filasAfectadas = ERROR_CODE;
        ResultSet rs;
        String sqlC = "SELECT * FROM pelicula WHERE id = ?;";
        String sqlU = "UPDATE pelicula SET titulo = ?, director = ?, anyo = ?, genero = ?;";
        int idP = 0,anyo = 0;
        String titulo = "",director = "",genero = "";
        Pelicula pelicula = null;
        try{
            PreparedStatement stmt = conexion.prepareStatement(sqlC);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while(rs.next()){
                idP = rs.getInt("id");
                titulo = rs.getString("titulo");
                director = rs.getString("director");
                anyo = rs.getInt("anyo");
                genero = rs.getString("genero");
                
                pelicula = new Pelicula(idP,titulo,director,anyo,genero);
            }
        }
        catch(SQLException slqe){
            System.err.println("Se ha producido un error recuperando la pelicula");
        }
        
        
        String nuevoTitulo,nuevoDirector,nuevoGenero;
        int nuevoAnyo;
        
        try{
            if(pelicula != null){
                if(!titulo.equalsIgnoreCase(peli.getTitulo())){
                    nuevoTitulo = peli.getTitulo();
                }
                else{
                    nuevoTitulo = titulo;
                }
                
                if(!director.equalsIgnoreCase(peli.getDirector())){
                    nuevoDirector = peli.getDirector();
                }
                else{
                    nuevoDirector = director;
                }
                
                if(anyo != peli.getAnyo()){
                    nuevoAnyo = peli.getAnyo();
                }
                else{
                    nuevoAnyo = anyo;
                }
                
                if(!genero.equalsIgnoreCase(peli.getGenero())){
                    nuevoGenero = peli.getGenero();
                }
                else{
                    nuevoGenero = genero;
                }
                
                PreparedStatement stmt = conexion.prepareStatement(sqlU);
                stmt.setString(1, nuevoTitulo);
                stmt.setString(2, nuevoDirector);
                stmt.setInt(3, nuevoAnyo);
                stmt.setString(4, nuevoGenero);
                
                filasAfectadas = stmt.executeUpdate();
            }
        }
        catch(SQLException sqle){
            System.err.println("Ha habido un error actualizando la pelicula");
        }
        return filasAfectadas;
    }
    
    public static int deletePelicula(int id){
        int filasAfectadas = ERROR_CODE;
        String sql = "DELETE FROM pelicula WHERE id = ?;";
        try{
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, id);
            filasAfectadas = stmt.executeUpdate();
        }
        catch(SQLException sqle){
            System.err.println("Se ha producido un error eliminando la pelicula");
        }
        return filasAfectadas;
    }
    
    public static void closeConnection(){
        Conexion.close();
    }
}
