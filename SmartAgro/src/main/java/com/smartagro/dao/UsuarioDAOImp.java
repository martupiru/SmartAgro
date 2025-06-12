/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.dao;
import javax.persistence.*;
import com.smartagro.model.Usuario;

/**
 *
 * @author Marti
 */
public class UsuarioDAOImp implements UsuarioDAO {
    private EntityManagerFactory emf;

    public UsuarioDAOImp(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Usuario obtenerPorCorreoYContrasena(String correo, String contrasena) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.correo = :correo AND u.contrasena = :contrasena AND u.activo = true",
                Usuario.class);
            query.setParameter("correo", correo);
            query.setParameter("contrasena", contrasena);
            return query.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }
}