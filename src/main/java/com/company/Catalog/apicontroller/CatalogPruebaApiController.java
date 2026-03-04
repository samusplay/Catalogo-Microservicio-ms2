package com.company.Catalog.apicontroller;

import com.company.Catalog.api.CatalogPruebaApi;
import com.company.Catalog.models.CatalogPruebaDTO;
import com.company.Catalog.service.CatalogPruebaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@AllArgsConstructor
@RequestMapping("/prueba")
public class CatalogPruebaApiController implements CatalogPruebaApi {
    private final CatalogPruebaService catalogPruebaService;
    @Override
    public ResponseEntity<CatalogPruebaDTO> createTest(CatalogPruebaDTO dto) {
        //delegamos el servicio
        CatalogPruebaDTO create=catalogPruebaService.create(dto);
        //mandamos un 201 ok
        return  new ResponseEntity<>(create, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CatalogPruebaDTO> findTest(@PathVariable Long id) {

        CatalogPruebaDTO finded = catalogPruebaService.findById(id);

        return ResponseEntity.ok(finded);
    }

}
