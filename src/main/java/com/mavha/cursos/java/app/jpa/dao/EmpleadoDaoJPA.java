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
    public Empleado guardar(Empleado e) {
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        em.persist(e);
        // forzar el insert en la base de datos
        em.flush();
        // refrescar con los datos de la base de datos
        em.refresh(e);
        em.getTransaction().commit();
        em.close();   
        return e;
    }

    @Override
    public void borrar(Empleado e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Empleado actualizar(Empleado e) {
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        Empleado empActualizado = em.merge(e);
        em.getTransaction().commit();
        em.close();        
        return empActualizado;
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

    @Override
    public Double salarioPromedioTodos(){
        Double d = 0.0;
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT AVG(e.salarioHora) FROM Empleado e");
        Number promedio = (Number) query.getSingleResult();
        if(promedio!=null) d = promedio.doubleValue();
        em.getTransaction().commit();
        em.close();
        return d;
    }

    @Override
    public List<Tarea> tareasPendientes(Integer idEmpleado) {
        List<Tarea> lista = null;
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT t FROM Empleado e "
                + " JOIN e.tareasAsignadas t "
                + "WHERE e.id = :idEmpleado AND t.fechaFin IS NULL");
        query.setParameter("idEmpleado", idEmpleado);
        lista = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return lista;
    }

    
    
}
