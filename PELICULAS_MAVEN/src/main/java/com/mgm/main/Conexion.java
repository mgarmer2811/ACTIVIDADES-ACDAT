/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario14
 */
public class Conexion {
    private static Connection conexion = null;
    
    private Conexion() {
        getConexion();
    }
    
    public static Connection getConexion() {
        if(conexion == null){
            try {
                conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/peliculas", "2dam", "2dam");
                System.out.println("Se ha establecido la conexion con la base de datos correctamente");
            } catch (SQLException slqe) {
                conexion = null;
            }
        }
        return conexion;
    }
    
    public static void close() {
        try{
            getConexion().close();
        }
        catch(SQLException sqle){
            System.err.println("Se ha producido un error cerrando la conexion con la base de datos");
        }
    }
}
