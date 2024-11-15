package com.mgm.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    private Connection conn;

    public Conexion() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ad_ej1","2dam","2dam");
    }

    public ResultSet ejecutarQuery(String sql) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    public int ejecutar(String sql) throws SQLException{
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        int filasAfectadas = stmt.executeUpdate(sql);
        return filasAfectadas;
    }

    public void cerrarConexion() throws SQLException{
        conn.close();
    }
}
