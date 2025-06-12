/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.inter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.smartagro.model.Usuario;
import com.smartagro.inter.LoginFrame;
import com.smartagro.report.ReportePDFExporter;
import com.smartagro.model.Agricultor;
import com.smartagro.model.Parcela;
import com.smartagro.dao.AgricultorDAO;
import com.smartagro.dao.AgricultorDAOImp;
import com.smartagro.dao.ParcelaDAO;
import com.smartagro.dao.ParcelaDAOImp;
import com.smartagro.util.BackupUtil;

/**
 *
 * @author Marti
 */

public class MainFrame extends JFrame {
    private AgricultorDAO agricultorDAO;  
    private ParcelaDAO parcelaDAO;
    
    public MainFrame(Usuario usuario) {
        agricultorDAO = new AgricultorDAOImp();  
        parcelaDAO = new ParcelaDAOImp();
        
        setTitle("SmartAgro - Menú Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        Color verde = 	new Color(119, 221, 119);

        JButton btnAgricultores = new JButton("👨‍🌾 Gestionar Agricultores");
        btnAgricultores.addActionListener(e -> {
            dispose(); // cerrar el menú
            new AgricultorFrame(usuario);
        });
        

        
        JButton btnParcelas = new JButton("🌱 Gestionar Parcelas");
        btnParcelas.addActionListener(e -> {
            dispose();
            new ParcelaFrame(usuario);
        });
        
        JButton btnExportarPDF = new JButton("📄 Exportar PDF");
        btnExportarPDF.addActionListener(e -> {
            List<Agricultor> listaAgricultores = agricultorDAO.getAgricultoresActivos();
            List<Parcela> listaParcelas = parcelaDAO.getParcelasActivas();

            ReportePDFExporter exporter = new ReportePDFExporter();
            exporter.exportar(listaAgricultores, listaParcelas);
        });
       
        JButton btnExportarBD = new JButton("📤 Exportar Base de Datos");
        btnExportarBD.addActionListener(e -> BackupUtil.exportarBD());

        JButton btnImportarBD = new JButton("📥 Importar Base de Datos");
        btnImportarBD.addActionListener(e -> BackupUtil.importarBD());
        
        JButton salirButton = new JButton("❌ Cerrar sesión");
        // Mostrar mensaje antes de cerrar
        salirButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Sesión cerrada con éxito");
            System.exit(0); // Cierra la app
        });
              
        btnAgricultores.setBackground(verde);
        btnAgricultores.setOpaque(true);
        btnAgricultores.setBorderPainted(false);
        btnAgricultores.setFocusPainted(false);
        
        btnParcelas.setBackground(verde);
        btnParcelas.setOpaque(true);
        btnParcelas.setBorderPainted(false);
        btnParcelas.setFocusPainted(false);
        
        btnExportarPDF.setBackground(verde);
        btnExportarPDF.setOpaque(true);
        btnExportarPDF.setBorderPainted(false);
        btnExportarPDF.setFocusPainted(false);
        
        btnExportarBD.setBackground(verde);
        btnExportarBD.setOpaque(true);
        btnExportarBD.setBorderPainted(false);
        btnExportarBD.setFocusPainted(false);
        
        btnImportarBD.setBackground(verde);
        btnImportarBD.setOpaque(true);
        btnImportarBD.setBorderPainted(false);
        btnImportarBD.setFocusPainted(false);
        
        salirButton.setBackground(verde);
        salirButton.setOpaque(true);
        salirButton.setBorderPainted(false);
        salirButton.setFocusPainted(false);

        panel.add(btnAgricultores);
        panel.add(btnParcelas);
        panel.add(btnExportarPDF);
        panel.add(btnExportarBD);
        panel.add(btnImportarBD);
        panel.add(salirButton);
        
        add(panel);
        setVisible(true);
    }
}
