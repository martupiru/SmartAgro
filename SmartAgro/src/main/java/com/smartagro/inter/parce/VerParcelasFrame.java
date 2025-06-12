/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.inter.parce;

import com.smartagro.dao.ParcelaDAO;
import com.smartagro.dao.ParcelaDAOImp;
import com.smartagro.model.Parcela;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Marti
 */

public class VerParcelasFrame extends JFrame {
    public VerParcelasFrame() {
        setTitle("Lista de Parcelas Activas");
        setSize(600, 300);
        setLocationRelativeTo(null);

        ParcelaDAO parcelaDAO = new ParcelaDAOImp();
        List<Parcela> lista = parcelaDAO.getParcelasActivas();

        String[] columnas = {"ID", "Nombre", "Ubicación", "ID Agricultor"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        for (Parcela p : lista) {
            Object[] fila = {
                p.getId(),
                p.getNombreParcela(),
                p.getUbicacion(),
                p.getAgricultor().getId()
            };
            model.addRow(fila);
        }

        JTable tabla = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll);

        setVisible(true);
    }
}
