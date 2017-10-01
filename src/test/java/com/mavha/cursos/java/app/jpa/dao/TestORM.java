/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.dao;

import com.mavha.cursos.java.app.jpa.modelo.Departamento;
import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mdominguez
 */
public class TestORM {
    EmpleadoDao dao = new EmpleadoDaoJPA();
    
    public TestORM() {
    }
    
    @Before
    public void setUp() {
        EmpleadoDao dao = new EmpleadoDaoJPA();
    }
    
    @After
    public void tearDown() {
    }

   
    @Test
    public void testContainsOneToMany() {
        Empleado e1 = new Empleado("MARTIN 1 ",100.0,new Date());
        Empleado e2 = new Empleado("MARTIN 2",100.0,new Date());
        Departamento d1 = new Departamento("DEPTO1");
        EntityManager em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        em.persist(d1);
        e1.setTrabajaEn(d1);
        e2.setTrabajaEn(d1);
        em.persist(e1);        
        em.persist(e2);        
        em.getTransaction().commit();
        em.close();        

        em = ConexionJPA.getInstance().em();        
        Departamento d2 = em.find(Departamento.class, d1.getId());
        assertEquals(2, d2.getEmpleados().size());
        em.close();

    }    
    
    
        @Test
    public void testContainsOneToManyBi() {
        Empleado e1 = new Empleado("MARTIN 3 ",100.0,new Date());
        Empleado e2 = new Empleado("MARTIN 4",100.0,new Date());
        Departamento d1 = new Departamento("DEPTO2");
        EntityManager em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        em.persist(d1);
        e1.setTrabajaEn(d1);
        e2.setTrabajaEn(d1);
        em.persist(e1);        
        em.persist(e2);        
        em.getTransaction().commit();
        em.close();        

        em = ConexionJPA.getInstance().em();        
        Departamento d2 = em.find(Departamento.class, d1.getId());
        assertEquals(2, d2.getEmpleados().size());
        em.close();

    }    
}