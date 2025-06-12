/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.inter.agri;

import com.smartagro.dao.AgricultorDAO;
import com.smartagro.dao.AgricultorDAOImp;
import com.smartagro.model.Agricultor;
import com.smartagro.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Marti
 */
public class EditarAgricultorFrame extends JFrame {
    private final AgricultorDAO agricultorDAO = new AgricultorDAOImp();
    private JComboBox<Agricultor> comboAgricultores;
    private JTextField txtNombre;
    private JTextField txtCorreo;

    public EditarAgricultorFrame() {
        setTitle("Editar Agricultor");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        

        panel.add(new JLabel("Seleccione un agricultor:"));
        comboAgricultores = new JComboBox<>();
        
        comboAgricultores.addActionListener(e -> mostrarDatosSeleccionados());
        panel.add(comboAgricultores);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panel.add(txtCorreo);
        
        cargarAgricultores();
        
        JButton btnGuardar = new JButton("💾 Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios());
        
        panel.add(btnGuardar);

        JButton btnCancelar = new JButton("❌ Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        
        panel.add(btnCancelar);

        add(panel);
        setVisible(true);
    }

    private void cargarAgricultores() {
        List<Agricultor> lista = agricultorDAO.getAgricultoresActivos();
        for (Agricultor a : lista) {
            comboAgricultores.addItem(a);
        }
        if (!lista.isEmpty()) {
            mostrarDatosSeleccionados();
        }
    }

    private void mostrarDatosSeleccionados() {
        Agricultor seleccionado = (Agricultor) comboAgricultores.getSelectedItem();
        if (seleccionado != null) {
            txtNombre.setText(seleccionado.getNombre());
            txtCorreo.setText(seleccionado.getCorreo());
        }
    }

    private void guardarCambios() {
        Agricultor seleccionado = (Agricultor) comboAgricultores.getSelectedItem();
        if (seleccionado != null) {
            String nuevoNombre = txtNombre.getText().trim();
            String nuevoCorreo = txtCorreo.getText().trim();

            if (nuevoNombre.isEmpty() || nuevoCorreo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            seleccionado.setNombre(nuevoNombre);
            seleccionado.setCorreo(nuevoCorreo);

            try {
                agricultorDAO.modificarAgricultor(seleccionado);
                JOptionPane.showMessageDialog(this, "✅ Agricultor actualizado.");
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "❌ Error al modificar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}