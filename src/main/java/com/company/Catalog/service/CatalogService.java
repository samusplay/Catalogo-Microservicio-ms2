package com.company.Catalog.service;

import com.company.Catalog.models.CatalogPruebaDTO;

import java.util.List;

public interface CatalogService {

    // 🔹 Crear producto
    CatalogPruebaDTO create(CatalogPruebaDTO dto);

    // 🔹 Actualizar producto
    CatalogPruebaDTO update(Long id, CatalogPruebaDTO dto);

    // 🔹 Eliminar producto
    void delete(Long id);

    // 🔹 Obtener por id
    CatalogPruebaDTO findById(Long id);

    // 🔹 Obtener todos
    List<CatalogPruebaDTO> findAll();

    // 🔹 Descontar stock
    CatalogPruebaDTO descontarStock(Long id, Integer cantidad);

    // 🔹 Reponer stock
    CatalogPruebaDTO reponerStock(Long id, Integer cantidad);
}