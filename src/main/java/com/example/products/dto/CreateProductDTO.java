package com.example.products.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateProductDTO(
    @NotBlank(message = "Product name is necessary!") String product_name,
    @NotBlank(message = "Price is necessary") float price
) {}
