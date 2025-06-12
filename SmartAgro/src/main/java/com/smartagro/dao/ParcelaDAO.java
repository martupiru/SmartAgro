/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartagro.dao;

import com.smartagro.model.Parcela;
import java.util.List;
/**
 *
 * @author Marti
 */
public interface ParcelaDAO {
    void insertarParcela(Parcela parcela);
    void modificarParcela(Parcela parcela);
    void eliminarParcela(Parcela parcela);  // borrado lógico
    Parcela getParcela(Long id);
    List<Parcela> getParcelasActivas();
    List<Parcela> getParcelasPorAgricultor(Long idAgricultor);
}