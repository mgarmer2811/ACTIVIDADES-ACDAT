/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Usuario14
 */
@Embeddable
public class MatriculasPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_estudiante")
    private int idEstudiante;
    @Basic(optional = false)
    @Column(name = "id_asignatura")
    private int idAsignatura;
    @Basic(optional = false)
    @Column(name = "anio")
    @Temporal(TemporalType.DATE)
    private Date anio;

    public MatriculasPK() {
    }

    public MatriculasPK(int idEstudiante, int idAsignatura, Date anio) {
        this.idEstudiante = idEstudiante;
        this.idAsignatura = idAsignatura;
        this.anio = anio;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Date getAnio() {
        return anio;
    }

    public void setAnio(Date anio) {
        this.anio = anio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEstudiante;
        hash += (int) idAsignatura;
        hash += (anio != null ? anio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MatriculasPK)) {
            return false;
        }
        MatriculasPK other = (MatriculasPK) object;
        if (this.idEstudiante != other.idEstudiante) {
            return false;
        }
        if (this.idAsignatura != other.idAsignatura) {
            return false;
        }
        if ((this.anio == null && other.anio != null) || (this.anio != null && !this.anio.equals(other.anio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgm.MatriculasPK[ idEstudiante=" + idEstudiante + ", idAsignatura=" + idAsignatura + ", anio=" + anio + " ]";
    }
    
}
