/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.bibliotecaXML;

import com.mgm.bibliotecaXML.*;
import com.mgm.biblioteca.utils.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MGM
 */
public class Biblioteca {
    private static ArrayList<Libro> libros = new ArrayList<Libro>();
    private static ArrayList<Autor> autores = new ArrayList<Autor>();
    
    public static void addLibro(Libro libro) {
        libros.add(libro);
    }
    
    public static void listLibros() {
        for(Libro libro : libros){
            System.out.println(libro.toString());
        }
    }
    
    public static void listAutores() {
        for(Autor autor : autores){
            System.out.println(autor.toString());
        }
    }
    
    public static void listLibrosByName(String name) {
        for(Libro libro : libros){
            String nameAutor = libro.getAutorLibro().getNombreAutor();
            String surnameAutor = libro.getAutorLibro().getApellidosAutor();
            
            if((nameAutor.contains(name)) || (surnameAutor.contains(name))){
                System.out.println(libro.toString());
            }
        }
    }
    
    public static void listLibrosByTitle(String title) {
        for(Libro libro : libros){
            String titleBook = libro.getTituloLibro();
            
            if(titleBook.contains(title)){
                System.out.println(libro.toString());
            }
        }
    }
    
    public static void listLibrosByYear() {
        Collections.sort(libros, new YearCompare());
        
        for(Libro libro : libros){
            System.out.println(libro.toString());
        }
    }
    
    public static void listLibrosAlphabetically() {
        Collections.sort(libros, new AlphabeticalCompare());
        
        for(Libro libro : libros){
            System.out.println(libro.toString());
        }
    }
    
    public static void addAutor(Autor autor) {
        autores.add(autor);
    }
    
    public static Autor getAutorById(int id) {
        Autor autor = new Autor();
        for(Autor autorAux : autores){
            if(autorAux.getIdAutor() == id){
                autor = autorAux;
            }
        }
        return autor;
    }
    
    public static void loadAutores() {
        try{
            File file = new File("autores.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(fileReader);
            String line;
            String[] data;
            buffer.readLine(); // Get rid of header
            while((line = buffer.readLine()) != null){
                data = line.split(","); // To separate values in CSV
                Autor autor = new Autor(Integer.parseInt(data[0]),data[1],data[2]);
                addAutor(autor);
            }
        }
        catch(Exception e){}
    }
    
    public static void loadLibros() {
        try{
            File file = new File("libros.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(fileReader);
            String line;
            String data[];
            int autorId;
            buffer.readLine(); // Get rid of header
            while((line = buffer.readLine()) != null){
                data = line.split(",");
                autorId = Integer.parseInt(data[2]); // This is the autor id
                Autor autor = getAutorById(autorId);
                Libro libro = new Libro(Integer.parseInt(data[0]),data[1],autor,data[3],Integer.parseInt(data[4]));
                addLibro(libro);
            }
        }
        catch(Exception e){}
    }
    
    public static void loadLibrosXML() {
        Libro libro = new Libro();
        boolean reading = true;
        XMLDecoder decoder = null;
        try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("libros.xml")));
            
            while(reading){
                try{
                    libro = (Libro) decoder.readObject();
                    addLibro(libro);
                }
                catch(Exception e){
                    reading = false;
                }
            }
        }
        catch(IOException ioe){
            System.err.println("Se ha producido un error leyendo el fichero XML");
        }
        finally{
            if(decoder != null){
                decoder.close();
            }
        }
    }
    
    public static void loadAutoresXML() {
        Autor autor = new Autor();
        boolean reading = true;
        XMLDecoder decoder = null;
        try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("autores.xml")));
            
            while(reading){
                try{
                    autor = (Autor) decoder.readObject();
                    addAutor(autor);
                }
                catch(Exception e){
                    reading = false;
                }
            }
        }
        catch(IOException ioe){
            System.err.println("Se ha producido un error leyendo el fichero XML");
        }
    }
    
    public static void storeAutor(Autor autor) {
        BufferedWriter buffer = null;
        FileWriter fileWriter = null;
        try{
            File file = new File("autores.txt");
            fileWriter = new FileWriter(file,true);
            buffer = new BufferedWriter(fileWriter);
            buffer.write(autor.getIdAutor() + "," + autor.getNombreAutor() + "," + autor.getApellidosAutor());
            buffer.newLine();
        }
        catch(Exception e){}
        finally{
            try{
                if(buffer != null)
                    buffer.close();
            }
            catch(Exception e){}
        }
    }
    
    public static void storeLibro(Libro libro) {
        FileWriter fileWriter = null;
        BufferedWriter buffer = null;
        try{
            File file = new File("libros.txt");
            fileWriter = new FileWriter(file,true);
            buffer = new BufferedWriter(fileWriter);
            buffer.write(libro.getIdLibro() + "," + libro.getTituloLibro() + "," + libro.getAutorLibro().getIdAutor() + "," + libro.getEditorialLibro() + "," + libro.getAnyoLibro());
            buffer.newLine();
        }
        catch(Exception e){}
        finally{
            try{
                if(buffer != null)
                    buffer.close();
            }
            catch(Exception e){}
        }
    }
    
    public static void storeAutorXML(Autor autor) {
        XMLEncoder encoder = null;
        addAutor(autor);
        try{
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("autores.xml")));
            
            for(Autor autor1 : autores){
                try{
                    encoder.writeObject(autor1);
                }
                catch(Exception ie){
                    System.err.println("Ha habido un error añadiendo el autor al fichero XML");
                }
                finally{
                    if(encoder != null){
                        encoder.close();
                    }
                }
            }
        }
        catch(Exception e){
            System.err.println("Ha habido un error escribiendo en el fichero XML");
        }
    }
    
    public static void storeLibroXML(Libro libro) {
        XMLEncoder encoder = null;
        addLibro(libro);
        try{
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("libros.xml")));
            
            for(Libro libro1 : libros){
                try{
                    encoder.writeObject(libro1);
                }
                catch(Exception ie){
                    System.err.println("Ha habido un error añadiendo el libro al fichero XML");
                }
                finally{
                    if(encoder != null){
                        encoder.close();
                    }
                }
            }
        }
        catch(Exception e){
            System.err.println("Ha habido un error escribiendo en el fichero XML");
        }
    }
}
