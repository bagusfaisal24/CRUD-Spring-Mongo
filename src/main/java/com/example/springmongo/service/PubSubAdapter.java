package com.example.springmongo.service;

import com.example.springmongo.domain.form.DtoProduct;
import com.example.springmongo.domain.Product;
import com.example.springmongo.ports.TransportLayerSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PubSubAdapter implements TransportLayerSvc {

    private final ProductSvc productSvc;

    @Autowired
    public PubSubAdapter(ProductSvc productSvc) {
        this.productSvc = productSvc;
    }

    @Override
    public ResponseEntity<Object> createNew(DtoProduct dtoProduct) {
        Product product = productSvc.setDto(dtoProduct);
        return productSvc.create(product);
    }

    @Override
    public ResponseEntity<Object> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(String id, DtoProduct dtoProduct) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(String id) {
        return null;
    }
}
