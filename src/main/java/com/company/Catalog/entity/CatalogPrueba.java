package com.company.Catalog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "catalog_prueba")
@Data
//Va modelar nues
public class CatalogPrueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(nullable = false)
    private Integer stock = 0; // evita que sea negativo desde base de datos
}
