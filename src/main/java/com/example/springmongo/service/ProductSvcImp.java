package com.example.springmongo.service;

import com.example.springmongo.constant.AppConstant;
import com.example.springmongo.domain.Product;
import com.example.springmongo.domain.ResponseUtil;
import com.example.springmongo.domain.form.DtoProduct;
import com.example.springmongo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductSvcImp implements ProductSvc {

    private final ProductRepository productRepository;
    private final RedisSvc redisSvc;

    @Autowired
    public ProductSvcImp(ProductRepository productRepository, RedisSvc redisSvc) {
        this.productRepository = productRepository;
        this.redisSvc = redisSvc;
    }

    @Override
    public ResponseEntity<Object> create(Product product) {
        try {
            productRepository.save(product);
            redisSvc.putIfAbsen(product);
            return ResponseUtil.build(AppConstant.SUCCESS, product, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.build(AppConstant.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> findAll() {
        try {
            HashMap<String, Object> products = (HashMap<String, Object>) redisSvc.hGet();
            List<Product> productList = new ArrayList<>();
            for (String key : products.keySet()){
                Product product = (Product) redisSvc.getById(key);
                productList.add(product);
            }
            return ResponseUtil.build(AppConstant.SUCCESS, productList, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.build(AppConstant.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> findById(String id) {
        try {
//            Product product = productRepository.findById(id).orElse(null);
            Object product = redisSvc.getById(id);
            if (product == null)  return ResponseUtil.build(AppConstant.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            return ResponseUtil.build(AppConstant.SUCCESS, product, HttpStatus.OK);
        }catch (Exception e){
            return ResponseUtil.build(AppConstant.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> update(String id, Product product) {
        try {
            Product productById = (Product) redisSvc.getById(id);
            if (productById == null)  return ResponseUtil.build(AppConstant.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            productById.setName(product.getName());
            productById.setPrice(product.getPrice());
            productById.setQty(product.getQty());
            productRepository.save(productById);
            redisSvc.put(productById);
            return ResponseUtil.build(AppConstant.SUCCESS, product, HttpStatus.OK);
        }catch (Exception e){
            return ResponseUtil.build(AppConstant.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> delete(String id) {
        try {
            Product productById = (Product) redisSvc.getById(id);
            if (productById == null)  return ResponseUtil.build(AppConstant.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            productRepository.delete(productById);
            redisSvc.delete(id);
            return ResponseUtil.build(AppConstant.SUCCESS, null, HttpStatus.OK);
        }catch (Exception e){
            return ResponseUtil.build(AppConstant.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Product setDto(DtoProduct dtoProduct) {
        Product product = new Product();
        product.setId(dtoProduct.getId());
        product.setQty(dtoProduct.getQty());
        product.setName(dtoProduct.getName());
        product.setPrice(dtoProduct.getPrice());
        return product;
    }

}
