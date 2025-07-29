package com.repository;

import com.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
    Optional<Product> findById(Long id);
    Boolean existsByName(String name);
    boolean existsById(Long id);
}