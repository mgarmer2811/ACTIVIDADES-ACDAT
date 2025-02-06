/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm;

import jakarta.persistence.*;
/**
 *
 * @author Usuario14
 */
@Entity
@Table(name = "libro")
public class Libro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "fecha_publicacion")
    private String fechaPublicacion;
    
    @Column(name = "genero")
    private String genero;
    
    @Column(name = "isbn")
    private String isbn;
    
    @Column(name = "editorial")
    private String editorial;
    
    @ManyToOne
    @JoinColumn(name = "id_autor", referencedColumnName = "id")
    private Autor autor;

    public Libro() {
    }

    public Libro(String titulo, String fechaPublicacion, String genero, String isbn, String editorial, Autor autor) {
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
        this.genero = genero;
        this.isbn = isbn;
        this.editorial = editorial;
        this.autor = autor;
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

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", genero='" + genero + '\'' +
                ", isbn='" + isbn + '\'' +
                ", editorial='" + editorial + '\'' +
                ", idAutor=" + autor.getId() + 
                '}';
    }
}

