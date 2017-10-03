/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.logica;

import com.mavha.cursos.java.app.jpa.dao.EmpleadoDao;
import com.mavha.cursos.java.app.jpa.dao.EmpleadoDaoJPA;
import com.mavha.cursos.java.app.jpa.dao.ProyectoDao;
import com.mavha.cursos.java.app.jpa.dao.ProyectoDaoJPA;
import com.mavha.cursos.java.app.jpa.dao.TareaDao;
import com.mavha.cursos.java.app.jpa.dao.TareaDaoJPA;
import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import com.mavha.cursos.java.app.jpa.modelo.Proyecto;
import com.mavha.cursos.java.app.jpa.modelo.Tarea;

/**
 *
 * @author mdominguez
 */
public class ProyectoLogicDefaultImpl implements ProyectoLogic{

    private ProyectoDao proyectoDao;
    private EmpleadoDao empleadoDao;
    private TareaDao tareaDao;
    
    public ProyectoLogicDefaultImpl(){
        proyectoDao = new ProyectoDaoJPA();
        empleadoDao = new EmpleadoDaoJPA();
        tareaDao = new TareaDaoJPA();
    }
    
    @Override
    public Proyecto crearProyecto(String nombre, Double presupuesto, Integer idLider) {
        Empleado e = empleadoDao.buscarPorId(idLider);        
        Proyecto p = null;
        if(nombre!=null && nombre.length()>1 && nombre.length()<20 && presupuesto>0.0 && presupuesto <100000.0){
            p=new Proyecto();
            p.setNombre(nombre);
            p.setPresupuesto(presupuesto);
            p.setLider(e);
            p = proyectoDao.crear(p);
        }
        return p;        
    }
            
    @Override
    public Double presupuestoDisponible(Integer idProyecto) {
        Proyecto p = proyectoDao.buscarPorId(idProyecto);
        System.out.println(p.getTarea());
        Double asignado = p.getTarea().stream().mapToDouble(t-> t.getDuracionEstimada()*t.getResponsable().getSalarioHora()).sum();
        System.out.println(asignado);
        return p.getPresupuesto()-asignado;
    }
    
    @Override
    public Tarea asignarTarea(Integer idProyecto, Integer idResponsable,String descripcion,Integer duracionEstimada) {
        Tarea t =null;
        if(this.presupuestoDisponible(idProyecto)>0) {
            Empleado e = empleadoDao.buscarPorId(idResponsable);        
            Proyecto proy = proyectoDao.buscarPorId(idProyecto);
            t= new Tarea();
            t.setDescripcion(descripcion);
            t.setDuracionEstimada(duracionEstimada);
            t.setProyecto(proy);
            t.setResponsable(e);
            t = tareaDao.crear(t);
        }
        return t;
    } 
}