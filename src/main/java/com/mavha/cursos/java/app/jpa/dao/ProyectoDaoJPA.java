/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.dao;

import com.mavha.cursos.java.app.jpa.modelo.Proyecto;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author mdominguez
 */
public class ProyectoDaoJPA implements ProyectoDao{
    private EntityManager em;

    @Override
    public void crear(Proyecto t) {
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();        
    }

    @Override
    public void borrar(Proyecto t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(Proyecto t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Proyecto buscarPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Proyecto> buscarTodos() {
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        List<Proyecto> p = em.createQuery("SELECT p FROM Proyecto p").getResultList();
        em.getTransaction().commit();
        em.close();     
        return p;
    }
    
}
