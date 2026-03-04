package com.company.Catalog.api;

import com.company.Catalog.models.CatalogPruebaDTO;
import com.company.Catalog.models.CrearProductoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface CatalogApi {

    @PutMapping("/{id}/descontar/{cantidad}")
    ResponseEntity<CatalogPruebaDTO> descontarStock(
            @PathVariable Long id,
            @PathVariable Integer cantidad);

    @PutMapping("/{id}/reponer/{cantidad}")
    ResponseEntity<CatalogPruebaDTO> reponerStock(
            @PathVariable Long id,
            @PathVariable Integer cantidad);
}


