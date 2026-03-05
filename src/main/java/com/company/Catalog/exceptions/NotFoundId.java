package com.company.Catalog.exceptions;

public class NotFoundId extends RuntimeException {
    public NotFoundId(String message) {
        super(message);
    }
}
