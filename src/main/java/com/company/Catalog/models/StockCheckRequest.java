package com.company.Catalog.models;

import lombok.Data;

@Data
public class StockCheckRequest {
    private Long productId;
    private Integer quantity;
}
