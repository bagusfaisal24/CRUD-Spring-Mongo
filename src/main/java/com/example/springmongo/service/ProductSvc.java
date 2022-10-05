package com.example.springmongo.service;

import com.example.springmongo.domain.Product;

import java.util.List;

public interface ProductSvc {

    Product create(Product product);
    List<Product> findAll();
    Product findById(String id);
    Product update(String id, Product product);
    void delete(String id);
}
