/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.modelo;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mdominguez
 */
@Entity
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private Double salarioHora;
    @Temporal(TemporalType.DATE)
    private Date fechaContratacion;    
    @ManyToOne
    @JoinColumn(name="ID_DEPTO_TRABAJA_EN")
    private Departamento trabajaEn;
  
    @ManyToMany(mappedBy = "asignados")
    private List<Proyecto> participaDe;
    
    @OneToMany(mappedBy = "responsable")
    private List<Tarea>tareasAsignadas;
    
    public Empleado() {
    }
    
    

    public Empleado(String nombre, Double salario, Date fechaContratacion) {
        this.nombre = nombre;
        this.salarioHora = salario;
        this.fechaContratacion = fechaContratacion;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSalarioHora() {
        return salarioHora;
    }

    public void setSalarioHora(Double salarioHora) {
        this.salarioHora = salarioHora;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Departamento getTrabajaEn() {
        return trabajaEn;
    }

    public void setTrabajaEn(Departamento trabajaEn) {
        this.trabajaEn = trabajaEn;
    }
    
    
    

    
}
