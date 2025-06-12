/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.control;

import com.smartagro.dao.ParcelaDAO;
import com.smartagro.dao.ParcelaDAOImp;
import com.smartagro.model.Parcela;
import java.util.List;

/**
 *
 * @author Marti
 */
public class ParcelaController {
    private ParcelaDAO parcelaDAO;

    public ParcelaController() {
        parcelaDAO = new ParcelaDAOImp();
    }

    public void insertarParcela(Parcela parcela) {
        parcelaDAO.insertarParcela(parcela);
    }

    public void modificarParcela(Parcela parcela) {
        parcelaDAO.modificarParcela(parcela);
    }

    public void eliminarParcela(Parcela parcela) {
        parcelaDAO.eliminarParcela(parcela);
    }

    public Parcela getParcela(Long id) {
        return parcelaDAO.getParcela(id);
    }

    public List<Parcela> getParcelasActivas() {
        return parcelaDAO.getParcelasActivas();
    }

    public List<Parcela> getParcelasPorAgricultor(Long idAgricultor) {
        return parcelaDAO.getParcelasPorAgricultor(idAgricultor);
    }
}

