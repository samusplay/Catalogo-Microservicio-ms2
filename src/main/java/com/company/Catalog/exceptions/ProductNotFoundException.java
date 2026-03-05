package com.company.Catalog.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long Id) {
        super("Producto con id " + Id + " no encontrado");
    }
}
