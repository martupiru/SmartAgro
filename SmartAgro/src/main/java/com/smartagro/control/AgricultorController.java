/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.control;
import com.smartagro.dao.AgricultorDAO;
import com.smartagro.dao.AgricultorDAOImp;
import com.smartagro.model.Agricultor;
import java.util.List;

/**
 *
 * @author Marti
 */

public class AgricultorController {
    private AgricultorDAO agricultorDAO;

    public AgricultorController() {
        agricultorDAO = new AgricultorDAOImp();
    }

    public void insertarAgricultor(Agricultor agricultor) {
        agricultorDAO.insertarAgricultor(agricultor);
    }

    public void modificarAgricultor(Agricultor agricultor) {
        agricultorDAO.modificarAgricultor(agricultor);
    }

    public void eliminarAgricultor(Agricultor agricultor) {
        agricultorDAO.eliminarAgricultor(agricultor);
    }

    public Agricultor getAgricultor(Long id) {
        return agricultorDAO.getAgricultor(id);
    }

    public List<Agricultor> getAgricultoresActivos() {
        return agricultorDAO.getAgricultoresActivos();
    }
}
