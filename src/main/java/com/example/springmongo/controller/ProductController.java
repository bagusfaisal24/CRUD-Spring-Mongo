package com.example.springmongo.controller;

import com.example.springmongo.domain.form.DtoProduct;
import com.example.springmongo.service.ApiAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ApiAdapter apiAdapter;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody DtoProduct product) {
        return apiAdapter.createNew(product);
    }

    @GetMapping
    public ResponseEntity<Object> listProduct() {
        return apiAdapter.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") String id) {
        return apiAdapter.findById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateById(@PathVariable("id") String id, @RequestBody DtoProduct dtoProduct) {
        return apiAdapter.update(id, dtoProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        return apiAdapter.delete(id);
    }
}
