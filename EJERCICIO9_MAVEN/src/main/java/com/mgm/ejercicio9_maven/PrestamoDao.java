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
public class PrestamoDao {
    private Conexion connection;
    
    public PrestamoDao(Conexion connection){
        this.connection = connection;
    }
    
    public boolean insertPrestamo(Prestamo prestamo){
        String sql = "INSERT INTO prestamo (id_libro, lector, fecha_prestamo, estado)  VALUES(?,?,?,?)";
        boolean result = false;
        
        try{
            PreparedStatement stmt = connection.getPreparedStatement(sql);
            connection.deactivateAutoCommit();
            stmt.setInt(1,prestamo.getId_libro());
            stmt.setString(2,prestamo.getLector());
            stmt.setString(3, prestamo.getFecha());
            stmt.setString(4,"Pendiente");
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            new LibroDao(connection).updateDisponibilidad(prestamo.getId_libro(), -1); //Aqui puede petar
            connection.commit(); //Si no peta, inserto
            result = true;
        }
        catch(SQLException sqle){
            connection.rollback();
            System.err.println("Ocurrio un error insertando el prestamo");
        }
        finally{
            connection.activateAutoCommit();
        }
        return result;
    }
    
    public boolean returnPrestamo(int id){
        String sql = "UPDATE prestamo SET estado = 'Devuelto' WHERE id = ?";
        boolean result = false;
        
        try{
            PreparedStatement stmt = connection.getPreparedStatement(sql);
            connection.deactivateAutoCommit();
            stmt.setInt(1, id);
            stmt.executeUpdate();
            new LibroDao(connection).updateDisponibilidad(getBookId(id),1);
            connection.commit();
            result = true;
        }
        catch(SQLException sqle){
            System.err.println("Ocurrion un error en la devolucion del libro");
        }
        finally{
            connection.activateAutoCommit();
        }
        return result;
    }
    
    public ArrayList<Prestamo> getPrestamosPendientes(){
        String sql = "SELECT prestamo.id,titulo,autor,lector,fecha_prestamo,estado FROM prestamo "
                + "JOIN libro ON libro.id = prestamo.id_libro "
                + "WHERE estado = 'Pendiente'";
        ArrayList<Prestamo> prestamos = null;
        
        try{
            PreparedStatement stmt = connection.getPreparedStatement(sql);
            ResultSet rs = stmt.executeQuery();
            prestamos = getData(rs);
        }
        catch(SQLException sqle){
            System.err.println("Ocurrio un error al obtener los prestamos pendientes");
        }
        return prestamos;
    }
    
    public ArrayList<Prestamo> getPrestamos(){
        String sql = "SELECT prestamo.id,titulo,autor,lector,fecha_prestamo,estado FROM prestamo "
                + "JOIN libro ON libro.id = prestamo.id_libro";
        ArrayList<Prestamo> prestamos = null;
        
        try{
            PreparedStatement stmt = connection.getPreparedStatement(sql);
            ResultSet rs = stmt.executeQuery();
            prestamos = getData(rs);
        }
        catch(SQLException sqle){
            System.err.println("Ocurrio un error al obtener los prestamos");
        }
        return prestamos;
    }
    
    private int getBookId(int id) throws SQLException{
        String sql = "SELECT id_libro FROM prestamo WHERE id = ?";
        int idLibro;
        
        PreparedStatement stmt = connection.getPreparedStatement(sql);
        stmt.setInt(1,id);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        idLibro = rs.getInt("id_libro");
        return idLibro;
    }
    
    private ArrayList<Prestamo> getData(ResultSet rs) throws SQLException{
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        
        while(rs.next()){
            int id = rs.getInt("id");
            int idLibro = rs.getInt("id_libro");
            String lector = rs.getString("lector");
            String fechaPrestamo = rs.getString("fecha_prestamo");
            String estado = rs.getString("estado");
            
            Prestamo newPrestamo = new Prestamo(id,idLibro,lector,fechaPrestamo,estado);
            prestamos.add(newPrestamo);
        }
        return prestamos;
    }
}
