/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.dao;

import com.mavha.cursos.java.app.jpa.modelo.Proyecto;
import java.util.List;

/**
 *
 * @author mdominguez
 */
public interface ProyectoDao {
    public void crear(Proyecto t);
    public void borrar(Proyecto t);
    public void actualizar(Proyecto t);
    public Proyecto buscarPorId(Integer id);
    public List<Proyecto> buscarTodos();

}
