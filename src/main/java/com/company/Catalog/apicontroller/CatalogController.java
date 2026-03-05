package com.company.Catalog.apicontroller;

import com.company.Catalog.api.CatalogApi;
import com.company.Catalog.models.ActualizarProductoRequest;

import com.company.Catalog.models.CrearProductoRequest;
import com.company.Catalog.models.ProductoResponse;
import com.company.Catalog.models.StockCheckRequest;
import com.company.Catalog.service.CatalogService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/productos")
@AllArgsConstructor
public class CatalogController implements CatalogApi {

    private final CatalogService service;

    @Override
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody CrearProductoRequest request) {
        ProductoResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Override
    public ResponseEntity<ProductoResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarProductoRequest request) {

        ProductoResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<ProductoResponse>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<ProductoResponse> obtenerPorId(Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    public ResponseEntity<Void> eliminar(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProductoResponse> descontarStock(Long id, Map<String, Integer> request) {
        Integer cantidad = request.get("cantidad");
        return ResponseEntity.ok(service.descontarStock(id, cantidad));
    }

    @Override
    public ResponseEntity<ProductoResponse> reponerStock(Long id, Map<String, Integer> request) {
        Integer cantidad = request.get("cantidad");
        return ResponseEntity.ok(service.reponerStock(id, cantidad));
    }

    @Override
    public ResponseEntity<Boolean> checkStock(StockCheckRequest request, String correlationId) {
        //llamos a la logica
        Boolean hasStock=service.checkStock(request,correlationId);

        return ResponseEntity.ok(hasStock);
    }

}
