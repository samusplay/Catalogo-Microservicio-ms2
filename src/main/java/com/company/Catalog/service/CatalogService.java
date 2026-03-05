package com.company.Catalog.service;

import com.company.Catalog.models.*;

import java.util.List;

public interface CatalogService {

    ProductoResponse create (CrearProductoRequest dto);

    ProductoResponse update(Long id, ActualizarProductoRequest dto);

    // Obtener todos los productos
    List<ProductoResponse> findAll();

    // Obtener producto por ID
    ProductoResponse findById(Long id);

    // Eliminar producto
    void delete(Long id);

    //descontar
    ProductoResponse descontarStock(Long id, Integer cantidad);
     //reponer
    ProductoResponse reponerStock(Long id, Integer cantidad);

    //validar
    Boolean checkStock(StockCheckRequest request, String correlationId);




}
