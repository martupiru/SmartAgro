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

public class EditarParcelaFrame extends JFrame {
    private final ParcelaDAO parcelaDAO = new ParcelaDAOImp();
    private JComboBox<Parcela> comboParcelas;
    private JTextField txtNombre;
    private JTextField txtUbicacion;

    public EditarParcelaFrame() {
        setTitle("Editar Parcela");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Seleccione una parcela:"));
        comboParcelas = new JComboBox<>();
        comboParcelas.addActionListener(e -> mostrarDatosSeleccionados());
        panel.add(comboParcelas);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Ubicación:"));
        txtUbicacion = new JTextField();
        panel.add(txtUbicacion);

        JButton btnGuardar = new JButton("💾 Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios());
        panel.add(btnGuardar);

        JButton btnCancelar = new JButton("❌ Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);

        cargarParcelas();
        add(panel);
        setVisible(true);
    }

    private void cargarParcelas() {
        List<Parcela> lista = parcelaDAO.getParcelasActivas();
        for (Parcela p : lista) {
            comboParcelas.addItem(p);
        }
        if (!lista.isEmpty()) {
            mostrarDatosSeleccionados();
        }
    }

    private void mostrarDatosSeleccionados() {
        Parcela seleccionada = (Parcela) comboParcelas.getSelectedItem();
        if (seleccionada != null) {
            txtNombre.setText(seleccionada.getNombreParcela());
            txtUbicacion.setText(seleccionada.getUbicacion());
        }
    }

    private void guardarCambios() {
        Parcela seleccionada = (Parcela) comboParcelas.getSelectedItem();
        if (seleccionada != null) {
            String nuevoNombre = txtNombre.getText().trim();
            String nuevaUbicacion = txtUbicacion.getText().trim();

            if (nuevoNombre.isEmpty() || nuevaUbicacion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            seleccionada.setNombreParcela(nuevoNombre);
            seleccionada.setUbicacion(nuevaUbicacion);

            try {
                parcelaDAO.modificarParcela(seleccionada);
                JOptionPane.showMessageDialog(this, "✅ Parcela actualizada.");
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "❌ Error al modificar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
