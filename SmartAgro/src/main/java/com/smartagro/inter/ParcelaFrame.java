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
import com.smartagro.inter.parce.VerParcelasFrame;
import com.smartagro.inter.parce.EditarParcelaFrame;
import com.smartagro.inter.parce.AgregarParcelaFrame;
import com.smartagro.inter.parce.EliminarParcelaFrame;
import java.awt.Color;

/**
 *
 * @author Marti
 */
public class ParcelaFrame extends JFrame {
    public ParcelaFrame(Usuario usuario) {
        setTitle("Gestión de Parcelas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10)); 
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        Color verde = 	new Color(119, 221, 119);

        panel.add(new JLabel("Bienvenido/a: " + usuario.getCorreo(), JLabel.CENTER));
        
        
        JButton verButton = new JButton("📋 Ver Parcelas");
        verButton.addActionListener(e -> new VerParcelasFrame());
        
        verButton.setBackground(verde);
        verButton.setOpaque(true);
        verButton.setBorderPainted(false);
        verButton.setFocusPainted(false);
        panel.add(verButton);
        
        JButton btnAgregar = new JButton("➕ Agregar Parcela");
        btnAgregar.addActionListener(e -> new AgregarParcelaFrame());
        
        btnAgregar.setBackground(verde);
        btnAgregar.setOpaque(true);
        btnAgregar.setBorderPainted(false);
        btnAgregar.setFocusPainted(false);
        
        panel.add(btnAgregar);
        
        JButton btnEditar = new JButton("✏️ Editar Parcela");
        btnEditar.addActionListener(e -> new EditarParcelaFrame());
        
        btnEditar.setBackground(verde);
        btnEditar.setOpaque(true);
        btnEditar.setBorderPainted(false);
        btnEditar.setFocusPainted(false);
        
        panel.add(btnEditar);
        
        
        JButton btnEliminar = new JButton("❌ Eliminar Parcela");
        btnEliminar.addActionListener(e -> new EliminarParcelaFrame());
        
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
