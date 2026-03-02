package com.company.Catalog.models;

import lombok.Data;

@Data
public class ActualizarProductoRequest {
    private Long Id; //A diferencia de crear aquí si se pone el Id porque el producto ya existe
    private String name;
    private Double precio;
    private Integer stock;
}
