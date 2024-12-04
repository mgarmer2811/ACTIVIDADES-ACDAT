/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.ejercicio9_maven;

/**
 *
 * @author Usuario
 */
public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private int anyo_publicacion;
    private int cantidad_disponible;
    private String categoria;

    public Libro(int id, String titulo, String autor, int anyo_publicacion, int cantidad_disponible, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anyo_publicacion = anyo_publicacion;
        this.cantidad_disponible = cantidad_disponible;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnyo_publicacion() {
        return anyo_publicacion;
    }

    public void setAnyo_publicacion(int anyo_publicacion) {
        this.anyo_publicacion = anyo_publicacion;
    }

    public int getCantidad_disponible() {
        return cantidad_disponible;
    }

    public void setCantidad_disponible(int cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return id + "," + titulo + "," + autor + "," + anyo_publicacion + "," + cantidad_disponible + "," + categoria;
    }
    
}
