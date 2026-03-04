package com.company.Catalog.api;

import com.company.Catalog.models.CatalogPruebaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CatalogApi {

    // 🔹 CREAR PRODUCTO
    @PostMapping
    ResponseEntity<CatalogPruebaDTO> create(
            @RequestBody CatalogPruebaDTO dto);

    // 🔻 DESCONTAR STOCK
    @PutMapping("/{id}/descontar/{cantidad}")
    ResponseEntity<CatalogPruebaDTO> descontarStock(
            @PathVariable Long id,
            @PathVariable Integer cantidad);

    // 🔺 REPONER STOCK
    @PutMapping("/{id}/reponer/{cantidad}")
    ResponseEntity<CatalogPruebaDTO> reponerStock(
            @PathVariable Long id,
            @PathVariable Integer cantidad);
}