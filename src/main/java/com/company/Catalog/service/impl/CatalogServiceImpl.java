package com.company.Catalog.service.impl;

import com.company.Catalog.entity.Catalog;
import com.company.Catalog.exceptions.ProductNotFoundException;
import com.company.Catalog.models.ActualizarProductoRequest;
import com.company.Catalog.models.CrearProductoRequest;
import com.company.Catalog.models.ProductoResponse;
import com.company.Catalog.repository.CatalogRepository;
import com.company.Catalog.service.CatalogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository repository;

    @Override
    public ProductoResponse create(CrearProductoRequest dto) {

        Catalog producto = new Catalog();
        producto.setName(dto.getName());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());

        Catalog guardado = repository.save(producto);

        return new ProductoResponse(
                guardado.getId(),
                guardado.getName(),
                guardado.getPrecio(),
                guardado.getStock()
        );
    }

    @Override
    public ProductoResponse update(Long id, ActualizarProductoRequest dto) {

        Catalog producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setName(dto.getName());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());

        Catalog actualizado = repository.save(producto);

        return new ProductoResponse(
                actualizado.getId(),
                actualizado.getName(),
                actualizado.getPrecio(),
                actualizado.getStock()
        );
    }

    //Para Ver Todos los productos
    @Override
    public List<ProductoResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(producto -> new ProductoResponse(
                        producto.getId(),
                        producto.getName(),
                        producto.getPrecio(),
                        producto.getStock()
                ))
                .toList();
    }

    // Para ver algún objeto en específico
    @Override
    public ProductoResponse findById(Long id) {

        Catalog producto = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return new ProductoResponse(
                producto.getId(),
                producto.getName(),
                producto.getPrecio(),
                producto.getStock()
        );
    }
    // Para eliminar un producto
    public void delete(Long id) {

        Catalog producto = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        repository.delete(producto);
    }



}
