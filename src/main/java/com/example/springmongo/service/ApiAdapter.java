package com.example.springmongo.service;

import com.example.springmongo.domain.form.DtoProduct;
import com.example.springmongo.domain.Product;
import com.example.springmongo.ports.TransportLayerSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApiAdapter implements TransportLayerSvc {

    private final ProductSvc productSvc;

    @Autowired
    public ApiAdapter(ProductSvc productSvc) {
        this.productSvc = productSvc;
    }

    @Override
    public ResponseEntity<Object> createNew(DtoProduct dtoProduct) {
        Product product = productSvc.setDto(dtoProduct);
        return productSvc.create(product);
    }

    @Override
    public ResponseEntity<Object> findAll() {
        return productSvc.findAll();
    }

    @Override
    public ResponseEntity<Object> findById(String id) {
        return productSvc.findById(id);
    }

    @Override
    public ResponseEntity<Object> update(String id, DtoProduct dtoProduct) {
        Product product = productSvc.setDto(dtoProduct);
        return productSvc.update(id, product);
    }

    @Override
    public ResponseEntity<Object> delete(String id) {
        return productSvc.delete(id);
    }
}
