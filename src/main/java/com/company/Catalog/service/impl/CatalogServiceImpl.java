package com.company.Catalog.service.impl;

import com.company.Catalog.entity.Producto;
import com.company.Catalog.models.ActualizarProductoRequest;
import com.company.Catalog.models.CrearProductoRequest;
import com.company.Catalog.models.ProductoResponse;
import com.company.Catalog.repository.CatalogRepository;
import com.company.Catalog.service.CatalogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository repository;

    @Override
    public ProductoResponse create(CrearProductoRequest dto) {

        Producto producto = new Producto();
        producto.setName(dto.getName());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());

        Producto guardado = repository.save(producto);

        return new ProductoResponse(
                guardado.getId(),
                guardado.getName(),
                guardado.getPrecio(),
                guardado.getStock()
        );
    }

    @Override
    public ProductoResponse update(ActualizarProductoRequest dto) {

        //  Buscar producto, si no existe se señala
        Producto producto = repository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Actualizar campos
        producto.setName(dto.getName());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());

        // Guardar cambios
        Producto actualizado = repository.save(producto);

        // Convertir a Response
        return new ProductoResponse(
                actualizado.getId(),
                actualizado.getName(),
                actualizado.getPrecio(),
                actualizado.getStock()
        );
    }


}
