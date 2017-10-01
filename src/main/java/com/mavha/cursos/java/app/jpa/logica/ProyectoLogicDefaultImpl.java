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
import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import com.mavha.cursos.java.app.jpa.modelo.Proyecto;

/**
 *
 * @author mdominguez
 */
public class ProyectoLogicDefaultImpl implements ProyectoLogic{

    private ProyectoDao proyectoDao;
    private EmpleadoDao empleadoDao;
    
    public ProyectoLogicDefaultImpl(){
        proyectoDao = new ProyectoDaoJPA();
        empleadoDao = new EmpleadoDaoJPA();
    }
    
    @Override
    public void crearProyecto(String nombre, Double presupuesto, Integer idLider) {
        Empleado e = empleadoDao.buscarPorId(idLider);        
        Proyecto p = new Proyecto();
        if(nombre!=null && nombre.length()>1 && nombre.length()<20 && presupuesto>0.0 && presupuesto <100000.0){
            p.setNombre(nombre);
            p.setPresupuesto(presupuesto);
            p.setLider(e);
            proyectoDao.crear(p);
        }
    }
    
}
