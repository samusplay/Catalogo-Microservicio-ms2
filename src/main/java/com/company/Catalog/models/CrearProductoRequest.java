package com.company.Catalog.models;

import lombok.Data;

@Data
public class CrearProductoRequest {

    private Long Id;

    private String name;
    private Double precio;
    private Integer stock;

}
