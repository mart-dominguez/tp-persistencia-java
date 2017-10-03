/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.dao;

import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author mdominguez
 */
public class EmpleadoDaoJPATest {
    EmpleadoDao dao = new EmpleadoDaoJPA();
    
    public EmpleadoDaoJPATest() {
    }
    
    @Before
    public void setUp() {
        EmpleadoDao dao = new EmpleadoDaoJPA();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of guardar method, of class EmpleadoDaoJPA.
     */
    @Ignore
    public void testGuardar1() {
        Empleado e = new Empleado("MARTIN",100.0,new Date());
        EntityManager em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        em.persist(e);        
        em.getTransaction().commit();
        em.close();        
    }

    @Ignore
    public void testGuardar2() {
        Empleado e = new Empleado("MARTIN2",200.0,new Date());
        EntityManager em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        em.persist(e);        
        e.setNombre("PEPE");
        e.setSalarioHora(2000.0);
        em.getTransaction().commit();
        em.close();
        System.out.println("e id"+e.getId());
        em = ConexionJPA.getInstance().em();        
        Empleado e2 = em.find(Empleado.class, e.getId());
        assertEquals("PEPE", e2.getNombre());
        e2.setNombre("JOHN");
        em.close();

        em = ConexionJPA.getInstance().em();        
        em.getTransaction().begin();
        Empleado e3 = em.find(Empleado.class, e.getId());
        assertEquals("PEPE", e3.getNombre());
        e3.setNombre("JOHN");
        em.getTransaction().commit();
        em.close();
        
        em = ConexionJPA.getInstance().em();        
        Empleado e4 = em.find(Empleado.class, e.getId());
        assertEquals("JOHN", e4.getNombre());
        em.close();

        em = ConexionJPA.getInstance().em();        
        em.getTransaction().begin();
        Empleado e5 = em.find(Empleado.class, e.getId());
        assertEquals("JOHN", e5.getNombre());
        em.remove(e5);
        em.getTransaction().commit();
        em.close();
        
        em = ConexionJPA.getInstance().em();        
        Empleado e6 = em.find(Empleado.class, e.getId());
        assertNull(e6);
        em.close();
        

    }

    //Test(expected = PersistenceException.class)
    @Ignore
    public void testMergeFail() {
        Empleado e = new Empleado("MARTIN2",200.0,new Date());
        EntityManager em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        em.persist(e);        
        e.setNombre("PEPE");
        e.setSalarioHora(2000.0);
        em.getTransaction().commit();
        em.close();
        
        e.setSalarioHora(3000.0);
        em = ConexionJPA.getInstance().em();        
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();
        
    }

    @Ignore
    public void testMergeOk() {
        Empleado e = new Empleado("MARTIN2",200.0,new Date());
        EntityManager em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        em.persist(e);        
        e.setNombre("PEPE");
        e.setSalarioHora(2000.0);
        em.getTransaction().commit();
        em.close();
        
        e.setSalarioHora(3000.0);
        em = ConexionJPA.getInstance().em();        
        em.getTransaction().begin();
        Empleado e2 = em.merge(e);
        e.setNombre("NOMBRE 1");
        e2.setNombre("NOMBRE 2");
        em.getTransaction().commit();
        em.close();
        
        em = ConexionJPA.getInstance().em();        
        em.getTransaction().begin();
        Empleado e5 = em.find(Empleado.class, e.getId());
        assertEquals("NOMBRE 2", e5.getNombre());
        em.getTransaction().commit();
        em.close();
        
    }
    
    @Ignore
    public void testContains() {
        
        EntityManager em = ConexionJPA.getInstance().em();
        
        Empleado e1 = new Empleado("MARTIN",200.0,new Date());
        Empleado e2 = new Empleado("PEPE",100.0,new Date());
        
        em.getTransaction().begin();
        em.persist(e1);        
        em.persist(e2);        
        em.getTransaction().commit();
        em.close();
        
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        assertFalse(em.contains(e1));
        assertFalse(em.contains(e2));
        em.getTransaction().commit();
        em.close();

        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        e1 = em.find(Empleado.class, e1.getId());
        assertTrue(em.contains(e1));
        assertFalse(em.contains(e2));
        em.getTransaction().commit();
        em.close();
        
    }

    
    @Ignore
    public void testContainsOneToMany() {
        
    }    
}
