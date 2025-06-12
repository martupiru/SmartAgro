/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.main;

import javax.swing.SwingUtilities;
import com.smartagro.inter.LoginFrame;
/**
 *
 * @author Marti
 */

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("Iniciando LoginFrame...");
                LoginFrame login = new LoginFrame();
                login.setVisible(true);
            } catch (Exception e) {
                System.out.println("Error al iniciar la ventana:");
                e.printStackTrace();
            }
        });
    }
}
