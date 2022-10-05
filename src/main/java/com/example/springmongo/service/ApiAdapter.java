package com.example.springmongo.service;

import com.example.springmongo.constant.AppConstant;
import com.example.springmongo.domain.DtoProduct;
import com.example.springmongo.domain.Product;
import com.example.springmongo.domain.ResponseUtil;
import com.example.springmongo.ports.TransportLayerSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ApiAdapter implements TransportLayerSvc {

    private final ProductSvc productSvc;

    @Autowired
    public ApiAdapter(ProductSvc productSvc) {
        this.productSvc = productSvc;
    }

    @Override
    public ResponseEntity<Object> createNew(DtoProduct dtoProduct) {
        try {
            Product product = new Product();
            product.setId(dtoProduct.getId());
            product.setQty(dtoProduct.getQty());
            product.setName(dtoProduct.getName());
            product.setPrice(dtoProduct.getPrice());
            Product result = productSvc.create(product);
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, result, HttpStatus.OK);
        }catch (Exception e){
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> findAll() {
        List<Product> products = productSvc.findAll();
        List<HashMap<String, Object>> list = new ArrayList<>();
        for (Product p : products){
            HashMap<String, Object> data = new HashMap<>();
            data.put("namaProduct", p.getName());
            list.add(data);
        }
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, list, HttpStatus.OK);

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
