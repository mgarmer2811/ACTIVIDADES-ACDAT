
package com.mgm.main;

public class Cliente {
    private int id = 0;
    private String nombre;
    private String email;
    private String ciudad;
    private String telefono;
    
    public Cliente(){}
    
    public Cliente(int id, String nombre, String email, String ciudad, String telefono){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.ciudad = ciudad;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "id: " + id + "\tnombre: " + nombre + "\temail: " + email + "\tciudad: " + ciudad + "\ttelefono: " + telefono;
    }
}

