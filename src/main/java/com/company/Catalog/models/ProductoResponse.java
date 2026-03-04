package com.company.Catalog.models;
// Lo que va a usar el sistema para responder a las funciones, menos eliminar producto

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductoResponse {

    private Long id;
    private String name;
    private Double precio;
    private Integer Stock;


}
