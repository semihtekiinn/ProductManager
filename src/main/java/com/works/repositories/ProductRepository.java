package com.works.repositories;

import com.works.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    int countAllBy();

    List<Product> findByTitleContainsIgnoreCaseOrDetailContainsIgnoreCase(String title, String detail);

    List<Product> findByPriceGreaterThanEqual(double price);
}