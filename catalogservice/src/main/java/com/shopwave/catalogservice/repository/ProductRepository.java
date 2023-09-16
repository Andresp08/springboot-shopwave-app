package com.shopwave.catalogservice.repository;

import com.shopwave.catalogservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
