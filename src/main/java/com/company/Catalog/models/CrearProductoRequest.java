package com.company.Catalog.models;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class CrearProductoRequest {
    //No se pone Id porque eso ya se genera automáticamente
    @NotBlank (message = "El nombre es obligatorio")
    private String name;

    @NotNull (message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que 0")
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    @PositiveOrZero(message = "El stock no puede ser negativo")
    private Integer stock;

}
