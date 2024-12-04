/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.ejercicio9_maven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class Conexion {
    private String URL;
    private String USER;
    private String PASSWORD;
    private Connection connection = null;
    
    public Conexion(String URL, String USER, String PASSWORD){
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        getConexion();
    }
    
    public Connection getConexion(){
        if(connection == null){
            try{
                connection = DriverManager.getConnection(URL + USER + PASSWORD);
            }
            catch(SQLException sqle){
                System.err.println("Ha ocurrido un error estableciendo la conexion a la base de datos");
            }
        }
        return connection;
    }
    
    public void closeConnection(){
        try{
            connection.close();
        }
        catch(SQLException slqe){
            System.err.println("Ha ocurrido un error cerrando la conexion con la base de datos");
        }
    }
    
    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public PreparedStatement getPreparedStatementGeneratedKeys(String sql) throws SQLException {
        return connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
    }
    
    public void commit(){
        try{
            connection.commit();
        }
        catch(SQLException sqle){
            System.err.println("Ocurrio un error y no se pudo commitear");
        }
    }
    
    public void activateAutoCommit() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println("Ocurrio un error y no se pudo activar el commit automatico");
        }
    }

    public void deactivateAutoCommit() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println("Ocurrio un error y no se pudo desactivar el commit automatico");
        }
    }
    
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.err.println("Ocurrio un error y no se pudo hacer rollback");
        }
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
