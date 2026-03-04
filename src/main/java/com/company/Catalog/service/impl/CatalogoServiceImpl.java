package com.company.Catalog.service.impl;

import com.company.Catalog.entity.CatalogPrueba;
import com.company.Catalog.models.CatalogPruebaDTO;
import com.company.Catalog.repository.CatalogPruebaRepository;
import com.company.Catalog.service.CatalogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CatalogoServiceImpl implements CatalogService {

    private final CatalogPruebaRepository catalogPruebaRepository;

    // 🔹 CREATE
    @Override
    public CatalogPruebaDTO create(CatalogPruebaDTO dto) {

        CatalogPrueba entity = new CatalogPrueba();
        entity.setNombre(dto.getNombre());
        entity.setStock(dto.getStock());

        CatalogPrueba guardado = catalogPruebaRepository.save(entity);

        return mapToDTO(guardado);
    }

    // 🔹 UPDATE
    @Override
    public CatalogPruebaDTO update(Long id, CatalogPruebaDTO dto) {

        CatalogPrueba entity = catalogPruebaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el catálogo con ID: " + id));

        entity.setNombre(dto.getNombre());
        entity.setStock(dto.getStock());

        return mapToDTO(catalogPruebaRepository.save(entity));
    }

    // 🔹 DELETE
    @Override
    public void delete(Long id) {

        CatalogPrueba entity = catalogPruebaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el catálogo con ID: " + id));

        catalogPruebaRepository.delete(entity);
    }

    // 🔹 FIND BY ID
    @Override
    public CatalogPruebaDTO findById(Long id) {

        CatalogPrueba entity = catalogPruebaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el catálogo con ID: " + id));

        return mapToDTO(entity);
    }

    // 🔹 FIND ALL
    @Override
    public List<CatalogPruebaDTO> findAll() {

        return catalogPruebaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // 🔹 DESCONTAR STOCK
    @Override
    public CatalogPruebaDTO descontarStock(Long id, Integer cantidad) {

        CatalogPrueba entity = catalogPruebaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el catálogo con ID: " + id));

        if (entity.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        entity.setStock(entity.getStock() - cantidad);

        return mapToDTO(catalogPruebaRepository.save(entity));
    }

    // 🔹 REPONER STOCK
    @Override
    public CatalogPruebaDTO reponerStock(Long id, Integer cantidad) {

        CatalogPrueba entity = catalogPruebaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el catálogo con ID: " + id));

        entity.setStock(entity.getStock() + cantidad);

        return mapToDTO(catalogPruebaRepository.save(entity));
    }

    // 🔹 MÉTODO PRIVADO PARA CONVERTIR
    private CatalogPruebaDTO mapToDTO(CatalogPrueba entity) {

        CatalogPruebaDTO dto = new CatalogPruebaDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setStock(entity.getStock());

        return dto;
    }
}