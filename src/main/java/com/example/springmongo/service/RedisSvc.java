package com.example.springmongo.service;

import com.example.springmongo.domain.Product;
import com.example.springmongo.domain.form.DtoProduct;
import org.springframework.http.ResponseEntity;

public interface RedisSvc {

    ResponseEntity<Object> set(DtoProduct dtoProduct);

    ResponseEntity<Object> get(DtoProduct dtoProduct);

    void putIfAbsen(Product product);

    Object hGet();

    Object getById(String id);

    void put(Product product);

    void delete(String id);
}
