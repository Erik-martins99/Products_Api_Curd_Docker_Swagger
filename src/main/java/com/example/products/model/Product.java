package com.example.products.model;

import com.example.products.dto.CreateProductDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product_name;
    private float price;

    public Product() {}

    public Product(CreateProductDTO data) {
        this.product_name = data.product_name();
        this.price = data.price();
    }

    public Long getId() {
        return this.id;
    }

    public String getProductName() {
        return this.product_name;
    }

    public void setProductName(String data) {
        this.product_name = data;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float data) {
        this.price = data;
    }
}
