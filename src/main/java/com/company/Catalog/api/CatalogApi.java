package com.company.Catalog.api;

import com.company.Catalog.models.ActualizarProductoRequest;
import com.company.Catalog.models.CrearProductoRequest;
import com.company.Catalog.models.ProductoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/productos")
public interface CatalogApi {

    @PostMapping
    ResponseEntity<ProductoResponse> crear(
            @RequestBody CrearProductoRequest request);

    @PutMapping("/{id}")
    ResponseEntity<ProductoResponse> actualizar(
            @PathVariable Long id,
            @RequestBody ActualizarProductoRequest request);

    @GetMapping
    ResponseEntity<List<ProductoResponse>> listar();

    @GetMapping("/{id}")
    ResponseEntity<ProductoResponse> obtenerPorId(
            @PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> eliminar(
            @PathVariable Long id);


    ResponseEntity<ProductoResponse> descontarStock(Long id, Integer cantidad);
    ResponseEntity<ProductoResponse> reponerStock(Long id, Integer cantidad);
}