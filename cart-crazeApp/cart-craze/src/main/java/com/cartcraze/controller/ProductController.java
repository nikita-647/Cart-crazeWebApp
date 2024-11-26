package com.cartcraze.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cartcraze.model.Product;
import com.cartcraze.repository.ProductRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public Map<String, Object> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                sortDir.equalsIgnoreCase("asc")
                        ? Sort.by(sortBy).ascending()
                        : Sort.by(sortBy).descending());

        Page<Product> products = productRepository.findAll(pageable);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> meta = new HashMap<>();

        meta.put("currentPage", products.getNumber());
        meta.put("totalPages", products.getTotalPages());
        meta.put("totalElements", products.getTotalElements());
        meta.put("pageSize", products.getSize());

        response.put("data", products.getContent());
        response.put("meta", meta);

        return response;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not Found"));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        System.out.println("Received Product: " + product);
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

}