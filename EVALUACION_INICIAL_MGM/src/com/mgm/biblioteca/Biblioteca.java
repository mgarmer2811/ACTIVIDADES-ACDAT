/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.biblioteca;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author MGM
 */
public class Biblioteca {
    private ArrayList<Libro> libros = new ArrayList<Libro>();
    
    public void mostrarLibro(int indice){
        System.out.println("");
        System.out.println("Id: " + libros.get(indice).getIdLibro());
        System.out.println("Titulo: " + libros.get(indice).getTituloLibro());
        System.out.println("Nombre Autor: " + libros.get(indice).getAutorLibro().getNombreAutor());
        System.out.println("Apellidos Autor: " + libros.get(indice).getAutorLibro().getApellidosAutor());
        System.out.println("Editorial: " + libros.get(indice).getEditorialLibro());
        System.out.println("Anyo Publicacion: " + libros.get(indice).getAnyoPublicacion());
        System.out.println("");
    }
    
    public void mostrarLibros(){
        for(int i = 0; i < libros.size(); i++){
            mostrarLibro(i);
        }
    }
    
    public void mostrarPorTexto(String texto){
        for(int i = 0; i < libros.size(); i++){
            if((libros.get(i).getAutorLibro().getNombreAutor().contains(texto)) || (libros.get(i).getAutorLibro().getApellidosAutor().contains(texto))){
                mostrarLibro(i);
            }
        }
    }
    
    public void mostrarPorTitulo(String titulo){
        for(int i = 0; i < libros.size(); i++){
            if(libros.get(i).getTituloLibro().contains(titulo)){
                mostrarLibro(i);
            }
        }
    }
    
    public void addLibroCompleto(Libro libro){
        libros.add(libro);
    }
    
    public void mostrarAlfabeticamente(){
        
        Collections.sort(libros, new ordenarAlfabeticamente());
        for(int i = 0; i < libros.size(); i++){
           mostrarLibro(i); 
        }
    }
    
    public void mostrarPorTiempo(){
        Collections.sort(libros, new ordenarPorTiempo());
        for(int i = 0; i < libros.size(); i++){
            mostrarLibro(i);
        }
    }
    
    private class ordenarPorTiempo implements Comparator<Libro>{

        @Override
        public int compare(Libro libro1, Libro libro2) {
            return libro2.getAnyoPublicacion() - libro1.getAnyoPublicacion();
        }
        
    }
    
    private class ordenarAlfabeticamente implements Comparator<Libro>{
        
        @Override
        public int compare(Libro libro1, Libro libro2){
            return libro1.getAutorLibro().getApellidosAutor().compareTo(libro2.getAutorLibro().getApellidosAutor());
        }
    }
}


