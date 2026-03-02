package com.company.Catalog.service;

import com.company.Catalog.models.CatalogPruebaDTO;

//firma del servicio el contrato
public interface CatalogPruebaService {
    //Servicio para crear
    CatalogPruebaDTO create(CatalogPruebaDTO dto);
    CatalogPruebaDTO findById(Long id);

    CatalogPruebaDTO descontarStock(Long id, Integer cantidad);

    CatalogPruebaDTO reponerStock(Long id, Integer cantidad);
}
