/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.inter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.persistence.Persistence;
import com.smartagro.model.Usuario;
import com.smartagro.dao.UsuarioDAO;
import com.smartagro.dao.UsuarioDAOImp;

/**
 *
 * @author Marti
 */

public class LoginFrame extends JFrame {
    private JTextField correoField;
    private JPasswordField contrasenaField;
    private UsuarioDAO usuarioDAO;

    public LoginFrame() {
        usuarioDAO = new UsuarioDAOImp(Persistence.createEntityManagerFactory("smartagroPU"));
        setTitle("Login SmartAgro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 180);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("Correo:"));
        correoField = new JTextField();
        panel.add(correoField);

        panel.add(new JLabel("Contraseña:"));
        contrasenaField = new JPasswordField();
        panel.add(contrasenaField);

        JButton loginButton = new JButton("Ingresar");
        loginButton.addActionListener(e -> autenticar());
        panel.add(loginButton);
        
        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.addActionListener(e -> System.exit(0)); // Cierra la app
        panel.add(cerrarButton);
        

        add(panel);
        setVisible(true);
    }

    private void autenticar() {
        String correo = correoField.getText();
        String contrasena = new String(contrasenaField.getPassword());
        Usuario usuario = usuarioDAO.obtenerPorCorreoYContrasena(correo, contrasena);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Bienvenido/a " + usuario.getCorreo());
            new MainFrame(usuario);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales inválidas o usuario inactivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
