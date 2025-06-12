/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.dao;

import com.smartagro.model.Parcela;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Marti
 */
public class ParcelaDAOImp implements ParcelaDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("smartagroPU");

    @Override
    public void insertarParcela(Parcela parcela) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(parcela);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void modificarParcela(Parcela parcela) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Parcela p = em.find(Parcela.class, parcela.getId());
            if (p != null) {
                p.setNombreParcela(parcela.getNombreParcela());
                p.setUbicacion(parcela.getUbicacion());
                p.setActivo(parcela.isActivo());
                p.setAgricultor(parcela.getAgricultor());
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
    public void eliminarParcela(Parcela parcela) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Parcela p = em.find(Parcela.class, parcela.getId());
            if (p != null) {
                p.setActivo(false);
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
    public Parcela getParcela(Long id) {
        EntityManager em = emf.createEntityManager();
        Parcela parcela = null;
        try {
            parcela = em.find(Parcela.class, id);
        } finally {
            em.close();
        }
        return parcela;
    }

    @Override
    public List<Parcela> getParcelasActivas() {
        EntityManager em = emf.createEntityManager();
        List<Parcela> lista = null;
        try {
            TypedQuery<Parcela> query = em.createQuery("SELECT p FROM Parcela p WHERE p.activo = true", Parcela.class);
            lista = query.getResultList();
        } finally {
            em.close();
        }
        return lista;
    }

    @Override
    public List<Parcela> getParcelasPorAgricultor(Long idAgricultor) {
        EntityManager em = emf.createEntityManager();
        List<Parcela> lista = null;
        try {
            TypedQuery<Parcela> query = em.createQuery(
                "SELECT p FROM Parcela p WHERE p.agricultor.id_agricultor = :idAgr AND p.activo = true", Parcela.class);
            query.setParameter("idAgr", idAgricultor);
            lista = query.getResultList();
        } finally {
            em.close();
        }
        return lista;
    }
}
