/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Usuario14
 */
@Entity
@Table(name = "empleado")
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "dni")
    private String dni;
    @Basic(optional = false)
    @Column(name = "departamento")
    private String departamento;
    @Basic(optional = false)
    @Column(name = "sueldo")
    private float sueldo;
    @Basic(optional = false)
    @Column(name = "fecha_contratacion")
    private String fechaContratacion;
    @Column(name = "fecha_finalizacion")
    private String fechaFinalizacion;
    @JoinTable(name = "empleado_proyecto", joinColumns = {
        @JoinColumn(name = "id_empleado", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_proyecto", referencedColumnName = "id")})
    @ManyToMany
    private List<Proyecto> proyectoList;

    public Empleado() {
    }

    public Empleado(int id) {
        this.id = id;
    }

    public Empleado(int id, String nombre, String dni, String departamento, float sueldo, String fechaContratacion) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.departamento = departamento;
        this.sueldo = sueldo;
        this.fechaContratacion = fechaContratacion;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    @Override
    public String toString() {
        return "Empleado" + "id=" + id + ", nombre=" + nombre + ", dni=" + dni + ", departamento=" + departamento + ", sueldo=" + sueldo + ", fechaContratacion=" + fechaContratacion;
    }
}
