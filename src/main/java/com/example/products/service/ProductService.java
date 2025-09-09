package com.example.products.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.products.dto.CreateProductDTO;
import com.example.products.dto.ProductDTO;
import com.example.products.model.Product;
import com.example.products.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductDTO> getAllProducts() {
        var products = repository.findAll()
            .stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
        return products;
    }

    public ProductDTO getProductbyId(Long id) {
        var product = repository.getReferenceById(id);
        try {
            return new ProductDTO(product);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
            "Produto não encontrado com ID: " + id);
        }
    }

    @Transactional
    public ProductDTO createProduct(CreateProductDTO data) {
        var product = new Product(data);
        repository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO updateProduct(Long id, CreateProductDTO data) {
        try {
        var product = repository.getReferenceById(id);

        // Verifica e atualiza nome
        if (data.product_name() != null && !data.product_name().trim().isEmpty()) {
            product.setProductName(data.product_name());
        }

        // Verifica e atualiza preço (usando comparação para float)
        if (data.price() != 0.0f && data.price() > 0.0f) {
            product.setPrice(data.price());
        }
        repository.save(product);
        return new ProductDTO(product);
        
        } catch (EntityNotFoundException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
            "Produto não encontrado com ID: " + id);
        }
    }

    @Transactional
    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }
}
