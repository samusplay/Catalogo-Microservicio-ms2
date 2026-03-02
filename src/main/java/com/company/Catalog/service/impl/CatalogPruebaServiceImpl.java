package com.company.Catalog.service.impl;

import com.company.Catalog.entity.CatalogPrueba;
import com.company.Catalog.models.CatalogPruebaDTO;
import com.company.Catalog.repository.CatalogPruebaRepository;
import com.company.Catalog.service.CatalogPruebaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CatalogPruebaServiceImpl implements CatalogPruebaService {
    //Siempre se debe inyectar el repository
    private final CatalogPruebaRepository catalogPruebaRepository;

    @Override
    public CatalogPruebaDTO create(CatalogPruebaDTO dto) {
        //instaciamos para llamar entity
        CatalogPrueba entity = new CatalogPrueba();
        //seteamos
        entity.setNombre(dto.getNombre());

        entity.setStock(dto.getStock());// guardar stock

        //guardamos en el repo
        CatalogPrueba guardado = catalogPruebaRepository.save(entity);

        CatalogPruebaDTO response = new CatalogPruebaDTO();
        response.setId(guardado.getId());
        response.setNombre(guardado.getNombre());
        response.setStock(guardado.getStock());

        return response;
    }

    @Override
    public CatalogPruebaDTO findById(Long id) {
        CatalogPrueba entity = catalogPruebaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el catálogo con ID: " + id));

        CatalogPruebaDTO dto = new CatalogPruebaDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        return dto;
    }
    @Override
    public CatalogPruebaDTO descontarStock(Long id, Integer cantidad) {

        if (cantidad <= 0) {
            throw new RuntimeException("Cantidad inválida");
        }

        CatalogPrueba entity = catalogPruebaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (entity.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        entity.setStock(entity.getStock() - cantidad);

        CatalogPrueba actualizado = catalogPruebaRepository.save(entity);

        CatalogPruebaDTO dto = new CatalogPruebaDTO();
        dto.setId(actualizado.getId());
        dto.setNombre(actualizado.getNombre());
        dto.setStock(actualizado.getStock());

        return dto;
    }
    @Override
    public CatalogPruebaDTO reponerStock(Long id, Integer cantidad) {

        if (cantidad <= 0) {
            throw new RuntimeException("Cantidad inválida");
        }

        CatalogPrueba entity = catalogPruebaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        entity.setStock(entity.getStock() + cantidad);

        CatalogPrueba actualizado = catalogPruebaRepository.save(entity);

        CatalogPruebaDTO dto = new CatalogPruebaDTO();
        dto.setId(actualizado.getId());
        dto.setNombre(actualizado.getNombre());
        dto.setStock(actualizado.getStock());

        return dto;
    }
}
