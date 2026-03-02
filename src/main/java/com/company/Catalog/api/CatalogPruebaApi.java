package com.company.Catalog.api;

import com.company.Catalog.models.CatalogPruebaDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CatalogPruebaApi {

    @PostMapping("/crear")
    ResponseEntity<CatalogPruebaDTO>createTest(@RequestBody CatalogPruebaDTO dto);

    @GetMapping("/{id}")
    ResponseEntity<CatalogPruebaDTO>findTest(@PathVariable Long id);


    // 🔹 DESCONTAR STOCK
    @PutMapping("/{id}/descontar/{cantidad}")
    ResponseEntity<CatalogPruebaDTO> descontarStock(
            @PathVariable Long id,
            @PathVariable Integer cantidad);

    // 🔹 REPONER STOCK
    @PutMapping("/{id}/reponer/{cantidad}")
    ResponseEntity<CatalogPruebaDTO> reponerStock(
            @PathVariable Long id,
            @PathVariable Integer cantidad);
}

