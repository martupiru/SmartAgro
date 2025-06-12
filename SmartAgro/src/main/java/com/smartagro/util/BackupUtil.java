/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.util;


import javax.swing.*;
import java.io.File;

/**
 *
 * @author Marti
 */
public class BackupUtil {

    public static void exportarBD() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar ubicación para guardar el respaldo");
        int seleccion = fileChooser.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            String ruta = archivo.getAbsolutePath();
            if (!ruta.endsWith(".sql")) {
                ruta += ".sql";
            }

            try {
                String comando = "pg_dump -U postgres -F p -b -v -f \"" + ruta + "\" SmartAgro";
                ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", comando);
                pb.environment().put("PGPASSWORD", "123456789");

                Process p = pb.start();
                p.waitFor();

                JOptionPane.showMessageDialog(null, "Base de datos exportada correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al exportar la base de datos: " + e.getMessage());
            }
        }
    }

    public static void importarBD() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo de respaldo");
        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            String ruta = archivo.getAbsolutePath();

            try {
                String comando = "pg_restore -U postgres -d SmartAgro -f \"" + ruta + "\"";
                ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", comando);
                pb.environment().put("PGPASSWORD", "123456789");

                Process p = pb.start();
                p.waitFor();

                JOptionPane.showMessageDialog(null, "Base de datos importada correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al importar la base de datos: " + e.getMessage());
            }
        }
    }
}