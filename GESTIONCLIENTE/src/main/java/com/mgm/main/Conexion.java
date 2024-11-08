/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Usuario14
 */
public class Conexion {
    private Connection conn;
    
    public Conexion() throws SQLException{
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ad_ej1","2dam","2dam");
    }
    
    public ResultSet ejecutarQuery(String sql) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        return rs;
    }
    
    public void ejecutar(String sql) throws SQLException{
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }
    
    public void cerrarConexion() throws SQLException{
        conn.close();
    }
}
