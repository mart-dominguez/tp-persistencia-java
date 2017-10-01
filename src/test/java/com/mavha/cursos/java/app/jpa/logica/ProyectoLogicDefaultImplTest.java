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
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author mdominguez
 */
public class ProyectoLogicDefaultImplTest {
    
    ProyectoLogic logica;
    EmpleadoDao empleadoDao;
    ProyectoDao pryDao;

    
    @Before
    public void setUp() {
        logica = new ProyectoLogicDefaultImpl();
        empleadoDao = new EmpleadoDaoJPA();
        pryDao = new ProyectoDaoJPA();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of crearProyecto method, of class ProyectoLogicDefaultImpl.
     */
    @Test
    public void testCrearProyecto() {
        List<Proyecto> inicial = pryDao.buscarTodos();
        Integer tamanioEsperado = inicial.size()+1;
        Empleado e = new Empleado();
        e.setNombre("MARTIN");
        e.setSalarioHora(100.0);
        e.setFechaContratacion(new Date());
        empleadoDao.guardar(e);
        List<Empleado> lista = empleadoDao.buscarTodos();
        logica.crearProyecto("Proyecto1", 5000.0, lista.get(0).getId());
        List<Proyecto> listaFinal = pryDao.buscarTodos();
        Integer tamanioFinal = listaFinal.size();
        assertEquals(tamanioEsperado, tamanioFinal);   
    }
    
}
