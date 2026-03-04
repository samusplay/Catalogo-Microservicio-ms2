package com.company.Catalog.apicontroller;

import com.company.Catalog.api.CatalogApi;
import com.company.Catalog.models.CatalogPruebaDTO;
import com.company.Catalog.service.CatalogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/catalogo")
@AllArgsConstructor
public class CatalogApiController implements CatalogApi {
    //inyetcar servicio
    private final CatalogService catalogService;


    @Override
    public ResponseEntity<CatalogPruebaDTO> create(CatalogPruebaDTO dto) {

        CatalogPruebaDTO response = catalogService.create(dto);
        return ResponseEntity.ok(response);
    }





}
