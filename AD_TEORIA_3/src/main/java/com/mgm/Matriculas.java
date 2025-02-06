/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Usuario14
 */
@Entity
@Table(name = "matriculas")
@NamedQueries({
    @NamedQuery(name = "Matriculas.findAll", query = "SELECT m FROM Matriculas m")})
public class Matriculas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MatriculasPK matriculasPK;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @Column(name = "calificacion")
    private float calificacion;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estudiantes estudiantes;
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignaturas asignaturas;

    public Matriculas() {
    }

    public Matriculas(MatriculasPK matriculasPK) {
        this.matriculasPK = matriculasPK;
    }

    public Matriculas(MatriculasPK matriculasPK, String estado) {
        this.matriculasPK = matriculasPK;
        this.estado = estado;
    }

    public Matriculas(int idEstudiante, int idAsignatura, Date anio) {
        this.matriculasPK = new MatriculasPK(idEstudiante, idAsignatura, anio);
    }

    public MatriculasPK getMatriculasPK() {
        return matriculasPK;
    }

    public void setMatriculasPK(MatriculasPK matriculasPK) {
        this.matriculasPK = matriculasPK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public Estudiantes getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Estudiantes estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Asignaturas getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Asignaturas asignaturas) {
        this.asignaturas = asignaturas;
    }

    @Override
    public String toString() {
        return "com.mgm.Matriculas[ matriculasPK=" + matriculasPK + " ]";
    }
    
}
