package com.commerce.msproduct.repositories;

import com.commerce.msproduct.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
