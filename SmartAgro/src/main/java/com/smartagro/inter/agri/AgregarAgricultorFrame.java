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
/**
 *
 * @author Marti
 */

public class AgregarAgricultorFrame extends JFrame {
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private final AgricultorDAO agricultorDAO = new AgricultorDAOImp();

    public AgregarAgricultorFrame(Usuario usuario) {
        setTitle("Agregar Agricultor");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panel.add(txtCorreo);

        JButton btnGuardar = new JButton("💾 Guardar");
        btnGuardar.addActionListener(e -> guardarAgricultor(usuario));

        panel.add(btnGuardar);

        JButton btnCancelar = new JButton("❌ Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        
        panel.add(btnCancelar);

        add(panel);
        setVisible(true);
    }

    private void guardarAgricultor(Usuario usuario) {
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (nombre.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Agricultor nuevo = new Agricultor();
        nuevo.setNombre(nombre);
        nuevo.setCorreo(correo);
        nuevo.setActivo(true);

        try {
            agricultorDAO.insertarAgricultor(nuevo);
            JOptionPane.showMessageDialog(this, "✅ Agricultor agregado exitosamente.");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error al guardar agricultor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}