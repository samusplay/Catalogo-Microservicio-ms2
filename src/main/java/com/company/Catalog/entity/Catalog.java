package com.company.Catalog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "catalog")
@Entity
public class Catalog {

    @Id
    @Column(name="Id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Significa que la base de datos hace auto-increment
    private Long Id;

    @Column(name="nombre_producto")
    private String name;

    @Column(name="precio_producto")
    private Double precio;

    @Column(name="stock_producto")
    private Integer stock;  // Integer significa número entero porque no pueden haber 1.5 unidades de un producto
}
