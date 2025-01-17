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
@Table(name = "cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "fecha_registro")
    private String fechaRegistro;
    
    @Column(name = "fecha_baja")
    private String fechaBaja;
    
    public Cliente(){}

    public Cliente(String nombre, String email, String fechaRegistro, String fechaBaja) {
        this.nombre = nombre;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.fechaBaja = fechaBaja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", fechaRegistro=" + fechaRegistro + ", fechaBaja=" + fechaBaja + '}';
    }
}
    

