/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.modelo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author mdominguez
 */
@Entity
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    private String nombre;
    private Double presupuesto;

    @ManyToOne
    @JoinColumn(name="ID_EMPLEADO_LIDER")
    private Empleado lider;
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "ID_PROYECTO"),
            inverseJoinColumns = @JoinColumn(name = "ID_EMPLEADO")
    )
    private List<Empleado> asignados;
    
    @OneToMany(mappedBy = "proyecto" )
    private List<Tarea> tarea;

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

    public Double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }
    
    

    public Empleado getLider() {
        return lider;
    }

    public void setLider(Empleado lider) {
        this.lider = lider;
    }

    public List<Empleado> getAsignados() {
        return asignados;
    }

    public void setAsignados(List<Empleado> asignados) {
        this.asignados = asignados;
    }

    public List<Tarea> getTarea() {
        return tarea;
    }

    public void setTarea(List<Tarea> tarea) {
        this.tarea = tarea;
    }
    
    
}
