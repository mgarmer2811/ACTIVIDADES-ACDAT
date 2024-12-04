/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.ejercicio9_maven;

/**
 *
 * @author Usuario
 */
public class Prestamo {
    private int id;
    private int id_libro;
    private String lector;
    private String fecha;
    private String estado; //En la base de datos es un ENUM. valores: 'Pendiente' y 'Devuelto'

    public Prestamo(int id_libro, String lector, String fecha) {
        this.id_libro = id_libro;
        this.lector = lector;
        this.fecha = fecha;
    }
    
    public Prestamo(int id, int id_libro, String lector, String fecha, String estado) {
        this.id = id;
        this.id_libro = id_libro;
        this.lector = lector;
        this.fecha = fecha;
        this.estado = estado;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getLector() {
        return lector;
    }

    public void setLector(String lector) {
        this.lector = lector;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return id + "," + id_libro + "," + lector + "," + fecha + "," + estado;
    }
}
