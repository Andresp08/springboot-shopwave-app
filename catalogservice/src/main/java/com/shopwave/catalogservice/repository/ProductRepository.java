package com.shopwave.catalogservice.repository;

import com.shopwave.catalogservice.model.Product;
import com.shopwave.catalogservice.model.enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Optional<Product> findByCategory(ProductCategory category);
}
