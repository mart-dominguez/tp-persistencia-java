/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.dao;

import com.mavha.cursos.java.app.jpa.modelo.Departamento;
import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author mdominguez
 */
public class EmpleadoDaoJPA implements EmpleadoDao{

    private EntityManager em;
    @Override
    public void guardar(Empleado e) {
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();        
    }

    @Override
    public void borrar(Empleado e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Empleado buscarPorId(Integer id) {
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        Empleado e = em.find(Empleado.class, id);
        em.getTransaction().commit();
        em.close();        
        return e;
    }

    @Override
    public List<Empleado> buscarTodos() {
   String consulta ="SELECT e FROM Empleado e";
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        Query query = em.createQuery(consulta);
        List<Empleado> resultado = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return resultado;
    }

    @Override
    public Empleado buscarPorNombre(String nombre) {
        String consulta ="SELECT e FROM Empleado e WHERE e.nombre = ?1";
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        Query query = em.createQuery(consulta);
        query.setParameter(1, nombre);
        Empleado resultado = (Empleado) query.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return resultado;
    }
    
    @Override
    public List<Empleado> buscarPorNombre(String nombre,Integer inicio,Integer cantidad) {
        String consulta ="SELECT e FROM Empleado e WHERE e.nombre = ?1";
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        Query query = em.createQuery(consulta);
        query.setMaxResults(cantidad);
        query.setFirstResult(inicio);
        query.setParameter(1, nombre);
        List<Empleado>  resultado = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return resultado;
    }

    @Override
    public Empleado buscarPorFechaContratacion(Date fechaIncial, Date fechaFinal) {
    String consulta ="SELECT e FROM Empleado e WHERE e.fechaContratacion BETWEEN :fechaIni AND :fechaFin";
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        Query query = em.createQuery(consulta);
        query.setParameter("fechaIni", fechaIncial,TemporalType.DATE);
        query.setParameter("fechaFin", fechaFinal,TemporalType.DATE);
        Empleado resultado = (Empleado) query.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return resultado;
    }

    @Override
    public void actualizarSueldo(Departamento d) {
        String sql="UPDATE Empleado e SET salario = salario*1.1 WHERE e.departamento = :depto";
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        Query query = em.createQuery(sql);
        query.setParameter("depto", d);
        query.executeUpdate();
        Empleado resultado = (Empleado) query.getSingleResult();
        em.getTransaction().commit();
        em.close();
    }
    
    
}
