package com.company.Catalog.models;

import lombok.Data;

@Data
public class CrearProductoRequest {
    //No se pone Id porque eso ya se genera automáticamente
    private String name;
    private Double precio;
    private Integer stock;

}
