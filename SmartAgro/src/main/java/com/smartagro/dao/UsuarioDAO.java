/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.smartagro.dao;
import com.smartagro.model.Usuario;
/**
 *
 * @author Marti
 */
public interface UsuarioDAO {
    Usuario obtenerPorCorreoYContrasena(String correo, String contrasena);
}
