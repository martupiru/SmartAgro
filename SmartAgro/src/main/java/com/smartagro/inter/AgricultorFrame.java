/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.inter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import com.smartagro.model.Usuario;
import com.smartagro.inter.LoginFrame;
import com.smartagro.inter.MainFrame;
import com.smartagro.inter.agri.VerAgricultoresFrame;
import com.smartagro.inter.agri.AgregarAgricultorFrame;
import com.smartagro.inter.agri.EditarAgricultorFrame;
import com.smartagro.inter.agri.EliminarAgricultorFrame;
import java.awt.Color;

/**
 *
 * @author Marti
 */

public class AgricultorFrame extends JFrame {
    public AgricultorFrame(Usuario usuario) {
        setTitle("Gestión de Agricultores");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 350); // aumenté un poco la altura
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10)); // ahora 6 filas
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        Color verde = 	new Color(119, 221, 119);

        panel.add(new JLabel("Bienvenido/a: " + usuario.getCorreo(), JLabel.CENTER));

        JButton verButton = new JButton("📋 Ver Agricultores");
        verButton.addActionListener(e -> new VerAgricultoresFrame());
        
        verButton.setBackground(verde);
        verButton.setOpaque(true);
        verButton.setBorderPainted(false);
        verButton.setFocusPainted(false);
        
        panel.add(verButton);
        
        JButton btnAgregar = new JButton("➕ Agregar Agricultor");
        btnAgregar.addActionListener(e -> new AgregarAgricultorFrame(usuario));
        
        btnAgregar.setBackground(verde);
        btnAgregar.setOpaque(true);
        btnAgregar.setBorderPainted(false);
        btnAgregar.setFocusPainted(false);
        
        panel.add(btnAgregar);
        

        JButton btnEditar = new JButton("✏️ Editar Agricultor");
        btnEditar.addActionListener(e -> new EditarAgricultorFrame());
        
        btnEditar.setBackground(verde);
        btnEditar.setOpaque(true);
        btnEditar.setBorderPainted(false);
        btnEditar.setFocusPainted(false);
        
        panel.add(btnEditar);
        
        JButton btnEliminar = new JButton("❌ Eliminar Agricultor");
        btnEliminar.addActionListener(e -> new EliminarAgricultorFrame());
        
        btnEliminar.setBackground(verde);
        btnEliminar.setOpaque(true);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        
        panel.add(btnEliminar);

        // Botón para volver al menú principal
        JButton botonVolver = new JButton("🔙 Volver al Menú Principal");
        botonVolver.addActionListener(e -> {
            dispose(); // cerrar esta ventana
            new MainFrame(usuario); // abrir el menú principal
        });
        panel.add(botonVolver);

        add(panel);
        setVisible(true);
    }
}