/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.inter.parce;

import com.smartagro.dao.ParcelaDAO;
import com.smartagro.dao.ParcelaDAOImp;
import com.smartagro.dao.AgricultorDAO;
import com.smartagro.dao.AgricultorDAOImp;
import com.smartagro.model.Agricultor;
import com.smartagro.model.Parcela;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Marti
 */
public class AgregarParcelaFrame extends JFrame {
    public AgregarParcelaFrame() {
        setTitle("Agregar Parcela");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        

        JTextField nombreField = new JTextField();
        JTextField ubicacionField = new JTextField();

        // Obtener Agricultores para el combo
        AgricultorDAO agricultorDAO = new AgricultorDAOImp();
        List<Agricultor> lista = agricultorDAO.getAgricultoresActivos();

        JComboBox<Agricultor> comboAgricultores = new JComboBox<>();
        for (Agricultor a : lista) comboAgricultores.addItem(a);

        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Ubicación:"));
        panel.add(ubicacionField);
        panel.add(new JLabel("Agricultor:"));
        panel.add(comboAgricultores);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            String nombre = nombreField.getText();
            String ubicacion = ubicacionField.getText();
            Agricultor agricultor = (Agricultor) comboAgricultores.getSelectedItem();

            if (!nombre.isEmpty() && !ubicacion.isEmpty() && agricultor != null) {
                Parcela parcela = new Parcela();
                parcela.setNombreParcela(nombre);
                parcela.setUbicacion(ubicacion);
                parcela.setActivo(true);
                parcela.setAgricultor(agricultor);

                new ParcelaDAOImp().insertarParcela(parcela);
                JOptionPane.showMessageDialog(this, "Parcela agregada con éxito");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Complete todos los campos");
            }
        });

        panel.add(new JLabel());
        panel.add(btnGuardar);

        add(panel);
        setVisible(true);
    }
}
