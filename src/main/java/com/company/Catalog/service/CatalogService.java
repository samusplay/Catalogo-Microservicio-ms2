package com.company.Catalog.service;

import com.company.Catalog.models.ActualizarProductoRequest;
import com.company.Catalog.models.CrearProductoRequest;
import com.company.Catalog.models.ProductoResponse;

public interface CatalogService {

    ProductoResponse create (CrearProductoRequest dto);

    ProductoResponse update (ActualizarProductoRequest dto);
}
