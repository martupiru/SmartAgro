/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.report;

import com.smartagro.model.Agricultor;
import com.smartagro.model.Parcela;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author Marrti
 */

public class ReportePDFExporter {

    public void exportar(List<Agricultor> agricultores, List<Parcela> parcelas) {
        Document document = new Document();

        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte PDF");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                if (!filePath.toLowerCase().endsWith(".pdf")) {
                    filePath += ".pdf";
                }

                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Título del documento
                Font fontTitle = new Font(Font.FontFamily.HELVETICA, 19, Font.BOLD);
                Paragraph title = new Paragraph("Reporte de SmartAgro", fontTitle);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(20);
                document.add(title);

                // Agricultores
                Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
                document.add(new Paragraph("Agricultores", sectionFont));
                PdfPTable tablaAgricultores = new PdfPTable(3);
                tablaAgricultores.setSpacingBefore(10);
                tablaAgricultores.addCell("ID");
                tablaAgricultores.addCell("Nombre");
                tablaAgricultores.addCell("Correo");

                for (Agricultor a : agricultores) {
                    tablaAgricultores.addCell(String.valueOf(a.getId()));            
                    tablaAgricultores.addCell(a.getNombre());
                    tablaAgricultores.addCell(a.getCorreo());
                }

                document.add(tablaAgricultores);

                // Espacio entre tablas
                document.add(Chunk.NEWLINE);

                // Parcelas
                document.add(new Paragraph("Parcelas", sectionFont));
                PdfPTable tablaParcelas = new PdfPTable(4);
                tablaParcelas.setSpacingBefore(10);
                tablaParcelas.addCell("ID");
                tablaParcelas.addCell("Nombre Parcela");
                tablaParcelas.addCell("Ubicación");
                tablaParcelas.addCell("Agricultor");

                for (Parcela p : parcelas) {
                    tablaParcelas.addCell(String.valueOf(p.getId()));                 
                    tablaParcelas.addCell(p.getNombreParcela());                      
                    tablaParcelas.addCell(p.getUbicacion());
                    tablaParcelas.addCell(p.getAgricultor().getNombre()); // accede al agricultor vinculado
                }

                document.add(tablaParcelas);

                JOptionPane.showMessageDialog(null, "Reporte PDF generado correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el reporte PDF: " + e.getMessage());
        } finally {
            document.close();
        }
    }
}