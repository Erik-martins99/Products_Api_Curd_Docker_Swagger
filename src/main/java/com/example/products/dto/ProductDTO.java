package com.example.products.dto;

import com.example.products.model.Product;

public record ProductDTO(Long id, String product_name, float price) {
    
    public ProductDTO(Product data) {
        this(data.getId(), data.getProductName(), data.getPrice());
    }
}
