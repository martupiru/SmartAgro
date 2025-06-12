/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.inter.parce;

import com.smartagro.dao.ParcelaDAO;
import com.smartagro.dao.ParcelaDAOImp;
import com.smartagro.model.Parcela;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Marti
 */

public class EliminarParcelaFrame extends JFrame {

    private JComboBox<Parcela> comboParcelas;
    private final ParcelaDAO parcelaDAO = new ParcelaDAOImp();

    public EliminarParcelaFrame() {
        setTitle("Eliminar Parcela");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        comboParcelas = new JComboBox<>();
        cargarParcelas();

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarParcela());

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.add(new JLabel("Seleccione parcela:"), BorderLayout.NORTH);
        panel.add(comboParcelas, BorderLayout.CENTER);
        panel.add(btnEliminar, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private void cargarParcelas() {
        comboParcelas.removeAllItems();
        List<Parcela> lista = parcelaDAO.getParcelasActivas();
        for (Parcela p : lista) {
            comboParcelas.addItem(p);
        }
    }

    private void eliminarParcela() {
        Parcela seleccionada = (Parcela) comboParcelas.getSelectedItem();
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(this, "No hay parcela seleccionada.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Eliminar parcela \"" + seleccionada.getNombreParcela() + "\"?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                parcelaDAO.eliminarParcela(seleccionada);
                JOptionPane.showMessageDialog(this, "Parcela eliminada correctamente.");
                cargarParcelas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}