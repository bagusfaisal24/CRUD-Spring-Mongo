package com.example.springmongo.service;

import com.example.springmongo.domain.DtoProduct;
import com.example.springmongo.ports.TransportLayerSvc;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PubSubAdapter implements TransportLayerSvc {

    @Override
    public ResponseEntity<Object> createNew(DtoProduct dtoProduct) {
        return null;
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
}
