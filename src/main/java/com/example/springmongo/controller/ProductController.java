package com.example.springmongo.controller;

import com.example.springmongo.domain.DtoProduct;
import com.example.springmongo.domain.Product;
import com.example.springmongo.service.ApiAdapter;
import com.example.springmongo.service.ProductSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductSvc productSvc;
    private final ApiAdapter apiAdapter;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody DtoProduct product){
        return apiAdapter.createNew(product);
    }

    @GetMapping
    public ResponseEntity<Object> listProduct(){
        return apiAdapter.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        Product product = productSvc.findById(id);
        return ResponseEntity.ok(product);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody Product product){
        Product productUpdated = productSvc.update(id, product);
        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        try {
            productSvc.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            InternalError internalError = new InternalError();
            internalError.setStackTrace(e.getStackTrace());
            return ResponseEntity.internalServerError().body(internalError);
        }
    }
}
