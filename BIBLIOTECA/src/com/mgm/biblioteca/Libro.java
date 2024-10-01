/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.biblioteca;

/**
 *
 * @author MGM
 */
public class Libro {
    private int idLibro;
    private String tituloLibro;
    private Autor autorLibro;
    private String editorialLibro;
    private int anyoLibro;

    public Libro() {
        
    }
    
    public Libro(int idLibro,String tituloLibro,Autor autorLibro,String editorialLibro,int anyoLibro) {
        this.idLibro = idLibro;
        this.tituloLibro = tituloLibro;
        this.autorLibro = autorLibro;
        this.editorialLibro = editorialLibro;
        this.anyoLibro = anyoLibro;
    }
    
    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public Autor getAutorLibro() {
        return autorLibro;
    }

    public void setAutorLibro(Autor autorLibro) {
        this.autorLibro = autorLibro;
    }

    public String getEditorialLibro() {
        return editorialLibro;
    }

    public void setEditorialLibro(String editorialLibro) {
        this.editorialLibro = editorialLibro;
    }

    public int getAnyoLibro() {
        return anyoLibro;
    }

    public void setAnyoLibro(int anyoLibro) {
        this.anyoLibro = anyoLibro;
    }
    
    @Override
    public String toString() {
        return "id: " + idLibro + "\ntitulo: " + tituloLibro + "\nAutor: " + autorLibro.getNombreAutor() + " " + autorLibro.getApellidosAutor() + "\neditorial: " + editorialLibro + "\nano publicacion: " + anyoLibro;
    }
}
