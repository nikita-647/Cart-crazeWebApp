package com.cartcraze.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartcraze.model.Products;
import com.cartcraze.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Products getProductsById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not Found"));
    }

    public Products saveProducts(Products products) {
        return productRepository.save(products);
    }

    public void deleteProdut(Long id) {
        productRepository.deleteById(id);
    }
}