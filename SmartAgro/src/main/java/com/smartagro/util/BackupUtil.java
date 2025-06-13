/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.util;


import javax.swing.*;
import java.io.*;

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

                // Leer salida estándar
                new Thread(() -> {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

                // Leer errores
                new Thread(() -> {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.err.println(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

                int exitCode = p.waitFor();
                if (exitCode == 0) {
                    JOptionPane.showMessageDialog(null, "Base de datos exportada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al exportar la base de datos.");
                }

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
                String comando = "psql -U postgres -d SmartAgro -f \"" + ruta + "\"";
                ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", comando);
                pb.environment().put("PGPASSWORD", "123456789");
                Process p = pb.start();

                // Leer salida estándar
                new Thread(() -> {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

                // Leer errores
                new Thread(() -> {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.err.println(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

                int exitCode = p.waitFor();
                if (exitCode == 0) {
                    JOptionPane.showMessageDialog(null, "Base de datos importada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al importar la base de datos.");
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al importar la base de datos: " + e.getMessage());
            }
        }
    }
}