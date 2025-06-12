/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.inter.agri;

import com.smartagro.dao.AgricultorDAO;
import com.smartagro.dao.AgricultorDAOImp;
import com.smartagro.model.Agricultor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Marti
 */
public class EliminarAgricultorFrame extends JFrame {

    private JComboBox<Agricultor> comboAgricultores;
    private AgricultorDAO agricultorDAO = new AgricultorDAOImp();

    public EliminarAgricultorFrame() {
        setTitle("Eliminar Agricultor");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        comboAgricultores = new JComboBox<>();
        cargarAgricultores();

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarAgricultor());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.add(new JLabel("Seleccione agricultor:"), BorderLayout.NORTH);
        panel.add(comboAgricultores, BorderLayout.CENTER);
        panel.add(btnEliminar, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private void cargarAgricultores() {
        comboAgricultores.removeAllItems();
        List<Agricultor> lista = agricultorDAO.getAgricultoresActivos();
        for (Agricultor a : lista) {
            comboAgricultores.addItem(a);
        }
    }

    private void eliminarAgricultor() {
        Agricultor seleccionado = (Agricultor) comboAgricultores.getSelectedItem();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "No hay agricultor seleccionado");
            return;
        }

        // Confirmar
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Eliminar agricultor " + seleccionado.getNombre() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                agricultorDAO.eliminarAgricultor(seleccionado);
                JOptionPane.showMessageDialog(this, "Agricultor eliminado correctamente.");
                cargarAgricultores();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
