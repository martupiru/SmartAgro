/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.dao;

import com.smartagro.model.Agricultor;
import java.util.List;
/**
 *
 * @author Marti
 */
public interface AgricultorDAO {
    void insertarAgricultor(Agricultor agricultor);
    void modificarAgricultor(Agricultor agricultor);
    void actualizarAgricultor(Agricultor agricultor);
    void eliminarAgricultor(Agricultor agricultor);  // borrado lógico con activo=false
    Agricultor getAgricultor(Long id);
    List<Agricultor> getAgricultoresActivos();
}

