/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.dao;
import com.smartagro.model.Agricultor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
/**
 *
 * @author Marti
 */
public class AgricultorDAOImp implements AgricultorDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("smartagroPU");

    @Override
    public void insertarAgricultor(Agricultor agricultor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(agricultor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void modificarAgricultor(Agricultor agricultor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(agricultor);  // Actualiza directamente la entidad
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw e;  // opcional: propagar excepción
        } finally {
            em.close();
        }
    }
    
    @Override
    public void actualizarAgricultor(Agricultor agricultor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(agricultor);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    @Override
    public void eliminarAgricultor(Agricultor agricultor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Agricultor a = em.find(Agricultor.class, agricultor.getId());
            if (a != null) {
                a.setActivo(false);  // borrado lógico
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Agricultor getAgricultor(Long id) {
        EntityManager em = emf.createEntityManager();
        Agricultor agricultor = null;
        try {
            agricultor = em.find(Agricultor.class, id);
        } finally {
            em.close();
        }
        return agricultor;
    }

    @Override
    public List<Agricultor> getAgricultoresActivos() {
        EntityManager em = emf.createEntityManager();
        List<Agricultor> lista = null;
        try {
            TypedQuery<Agricultor> query = em.createQuery("SELECT a FROM Agricultor a WHERE a.activo = true", Agricultor.class);
            lista = query.getResultList();
        } finally {
            em.close();
        }
        return lista;
    }
}
