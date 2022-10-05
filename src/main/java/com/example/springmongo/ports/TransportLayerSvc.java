package com.example.springmongo.ports;

import com.example.springmongo.domain.DtoProduct;
import org.springframework.http.ResponseEntity;

public interface TransportLayerSvc {

    ResponseEntity<Object>  createNew(DtoProduct dtoProduct);

    ResponseEntity<Object>  findAll();

    ResponseEntity<Object>  findById(String id);

    ResponseEntity<Object>  update(String id, DtoProduct dtoProduct);

}
