package com.example.springmongo.service;

import com.example.springmongo.domain.DtoProduct;
import com.example.springmongo.domain.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductSvc {

    ResponseEntity<Object> create(Product product);
    ResponseEntity<Object> findAll();
    ResponseEntity<Object> findById(String id);
    ResponseEntity<Object> update(String id, Product product);
    ResponseEntity<Object> delete(String id);

    Product setDto(DtoProduct dtoProduct);

}
