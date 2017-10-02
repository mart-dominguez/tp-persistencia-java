/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.logica;

import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import com.mavha.cursos.java.app.jpa.modelo.Proyecto;

/**
 *
 * @author mdominguez
 */
public interface ProyectoLogic {

    /**
     * Si se cumple la condicion 
     * @param nombre: el nombre del proyecto es obligatorio
     * @param presupuesto: el presupuesto del proyecto es obligatorio
     * @param idLider: el ID del lider del proyecto. 
     */
    public void crearProyecto(String nombre, Double presupuesto,Integer idLider);
    public void asignarTarea(Integer idProyecto, Integer idResponsable,Integer duracionEstimada);
    public Double presupuestoConsumido(Integer idProyecto);
}
