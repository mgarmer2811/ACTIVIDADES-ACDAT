/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.bibliotecaXML;

import java.io.Serializable;

/**
 *
 * @author MGM
 */
public class Autor implements Serializable{
    private int idAutor;
    private String nombreAutor;
    private String apellidosAutor;

    public Autor() {
        
    }
    
    public Autor(int idAutor,String nombreAutor,String apellidosAutor) {
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.apellidosAutor = apellidosAutor;
    }
    
    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getApellidosAutor() {
        return apellidosAutor;
    }

    public void setApellidosAutor(String apellidosAutor) {
        this.apellidosAutor = apellidosAutor;
    }
    
    @Override
    public String toString() {
        return "id: " + idAutor + "\nnombre: " + nombreAutor + "\napellido(s): " + apellidosAutor;
    }
    
}
