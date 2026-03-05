package com.company.Catalog.api;

import com.company.Catalog.models.ActualizarProductoRequest;
import com.company.Catalog.models.CrearProductoRequest;
import com.company.Catalog.models.ProductoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@RequestMapping("/productos")
public interface CatalogApi {

    @PostMapping("/crear")
    ResponseEntity<ProductoResponse> crear(
            @RequestBody CrearProductoRequest request);

    @PutMapping("/{id}/update")
    ResponseEntity<ProductoResponse> actualizar(
            @PathVariable Long id,
            @RequestBody ActualizarProductoRequest request);

    @GetMapping("/all")
    ResponseEntity<List<ProductoResponse>> listar();

    @GetMapping("/{id}/getById")
    ResponseEntity<ProductoResponse> obtenerPorId(
            @PathVariable Long id);

    @DeleteMapping("/{id}/delete")
    ResponseEntity<Void> eliminar(
            @PathVariable Long id);

    @PutMapping("/{id}/descontar")
    ResponseEntity<ProductoResponse> descontarStock(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> request);

    @PutMapping("/{id}/reponer")
    ResponseEntity<ProductoResponse> reponerStock(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> request);
}