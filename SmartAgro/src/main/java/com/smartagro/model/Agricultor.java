/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.model;
import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Marti
 */

@Entity
@Table(name = "Agricultor", schema = "sistema")
public class Agricultor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agricultor")
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Column(nullable = false)
    private boolean activo;

    @OneToMany(mappedBy = "agricultor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parcela> parcelas;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }
    
    @Override
    public String toString() {
        return nombre + " (" + correo + ")";
    }
}
