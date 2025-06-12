/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.inter.agri;

import com.smartagro.dao.AgricultorDAO;
import com.smartagro.dao.AgricultorDAOImp;
import com.smartagro.model.Agricultor;

import javax.swing.*;
import javax.persistence.Persistence;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Marti
 */
public class VerAgricultoresFrame extends JFrame {
    public VerAgricultoresFrame() {
        setTitle("Lista de Agricultores Activos");
        setSize(500, 300);
        setLocationRelativeTo(null);

        AgricultorDAO agricultorDAO = new AgricultorDAOImp();
        List<Agricultor> lista = agricultorDAO.getAgricultoresActivos();

        String[] columnas = {"ID", "Nombre", "Correo"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        for (Agricultor a : lista) {
            Object[] fila = {a.getId(), a.getNombre(), a.getCorreo()};
            model.addRow(fila);
        }

        JTable tabla = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll);

        setVisible(true);
    }
}
