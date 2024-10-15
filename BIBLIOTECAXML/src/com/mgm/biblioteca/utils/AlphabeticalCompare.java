/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.biblioteca.utils;

import com.mgm.bibliotecaXML.*;
import java.util.Comparator;

/**
 *
 * @author Usuario
 */
public class AlphabeticalCompare implements Comparator<Libro> {
    
    @Override
    public int compare(Libro libro1, Libro libro2){
        return libro1.getAutorLibro().getApellidosAutor().compareTo(libro2.getAutorLibro().getApellidosAutor());
    }
}
