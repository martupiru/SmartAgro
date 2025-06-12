/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.model;
import javax.persistence.*;
/**
 *
 * @author Marti
 */

@Entity
@Table(name = "Parcela", schema = "sistema")
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parcela")
    private int id;

    @Column(name = "nombreParcela", nullable = false, length = 100)
    private String nombreParcela;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String ubicacion;

    @Column(nullable = false)
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_agricultor", nullable = false)
    private Agricultor agricultor;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreParcela() {
        return nombreParcela;
    }

    public void setNombreParcela(String nombreParcela) {
        this.nombreParcela = nombreParcela;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Agricultor getAgricultor() {
        return agricultor;
    }

    public void setAgricultor(Agricultor agricultor) {
        this.agricultor = agricultor;
    }
    @Override
    public String toString() {
        return nombreParcela + " - " + ubicacion;
    }
}
