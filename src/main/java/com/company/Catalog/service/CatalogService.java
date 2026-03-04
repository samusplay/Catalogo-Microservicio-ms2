package com.company.Catalog.service;

import com.company.Catalog.models.ActualizarProductoRequest;
import com.company.Catalog.models.CatalogPruebaDTO;
import com.company.Catalog.models.CrearProductoRequest;
import com.company.Catalog.models.ProductoResponse;

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

    ProductoResponse descontarStock(Long id, Integer cantidad);

    ProductoResponse reponerStock(Long id, Integer cantidad);




}
