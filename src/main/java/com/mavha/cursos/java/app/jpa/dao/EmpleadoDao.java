/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.dao;

import com.mavha.cursos.java.app.jpa.modelo.Departamento;
import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import com.mavha.cursos.java.app.jpa.modelo.Tarea;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mdominguez
 */
public interface EmpleadoDao {
    public Empleado guardar(Empleado e);    
    public void borrar(Empleado e);
    public Empleado actualizar(Empleado e);
    public Empleado buscarPorId(Integer id);
    public List<Empleado> buscarTodos();
    public Empleado buscarPorNombre(String nombre);
    public List<Empleado> buscarPorNombre(String nombre,Integer inicio,Integer cantidad);
    public Empleado buscarPorFechaContratacion(Date fechaIncial,Date fechaFinal);
    public void actualizarSueldo(Departamento d);
    public Double salarioPromedioTodos();
    public List<Tarea>  tareasPendientes(Integer idEmpleado);
}
