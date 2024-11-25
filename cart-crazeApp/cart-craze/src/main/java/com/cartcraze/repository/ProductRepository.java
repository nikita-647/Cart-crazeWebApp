package com.cartcraze.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cartcraze.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

}
