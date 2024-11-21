/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class EmpleadoDAO {
    private static Connection conexion;
    private static final int ERROR_CODE = -1;
    
    static {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/empleados", "2dam", "2dam");
            System.out.println("Conexion con la base de datos establecida correctamente");
        } catch (SQLException e) {
            System.err.println("Fallo estableciendo la conexion con la base de datos: " + e.getMessage());
            conexion = null;
        }
    }

    private EmpleadoDAO() {
        throw new UnsupportedOperationException("Esta clase no puede ser instanciada");
    }
    
    public static ArrayList<Empleado> listarEmpleados() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT ? FROM empleado;";
        try{
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, "*");
            rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String puesto = rs.getString("puesto");
                float salario = rs.getFloat("salario");
                int anyoIngreso = rs.getInt("anyo_ingreso");
                
                Empleado newEmpleado = new Empleado(id,nombre,puesto,salario,anyoIngreso);
                empleados.add(newEmpleado);
            }
        }
        catch(SQLException sqle){
            System.err.println("Se ha producido un error buscando a los clientes");
        }
        
        return empleados;
    }
    
    public static int anadirEmpleado(Empleado empleado) {
        int filasAfectadas = ERROR_CODE;
        ResultSet rs;
        String sql = "INSERT INTO empleado (id,nombre,puesto,salario,anyo_ingreso) VALUES (?,?,?,?,?);";
        try{
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, empleado.getId());
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getPuesto());
            stmt.setFloat(4, empleado.getSalario());
            stmt.setInt(5, empleado.getDate());
            filasAfectadas = stmt.executeUpdate();
        }
        catch(SQLException sqle){
            System.err.println("Se ha producido un error anadiendo el empleado");
        }
        
        return filasAfectadas;
    }
    
    public static int modificarEmpleado(Empleado empleado, int id) {
        int filasAfectadas = ERROR_CODE;
        ResultSet rs;
        String sql = "UPDATE empleado SET nombre=?, puesto=?, salario=?, date=? WHERE id=?;";
        try{
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getPuesto());
            stmt.setFloat(3, empleado.getSalario());
            stmt.setInt(4, empleado.getDate());
            stmt.setInt(5, id);
            
            filasAfectadas = stmt.executeUpdate();
        }
        catch(SQLException sqle){
            System.err.println("Se ha producido un error actualizando el empleado");
        }
        
        return filasAfectadas;
    }
    
    public static int borrarEmpleado(Empleado empleado) {
        int filasAfectadas = ERROR_CODE;
        ResultSet rs;
        String sql = "DELETE FROM empleado WHERE id = ?;";
        try{
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, empleado.getId());
            filasAfectadas = stmt.executeUpdate();
        }
        catch(SQLException sqle){
            System.err.println("Se ha producido un error borrando el empleado");
        }
        
        return filasAfectadas;
    }
}
