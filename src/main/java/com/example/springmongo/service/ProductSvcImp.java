package com.example.springmongo.service;

import com.example.springmongo.constant.AppConstant;
import com.example.springmongo.domain.DtoProduct;
import com.example.springmongo.domain.Product;
import com.example.springmongo.domain.ResponseUtil;
import com.example.springmongo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSvcImp implements ProductSvc {

    private final ProductRepository productRepository;

    @Autowired
    public ProductSvcImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Object> create(Product product) {
        try {
            productRepository.save(product);
            return ResponseUtil.build(AppConstant.SUCCESS, product, HttpStatus.OK);
        }catch (Exception e){
            return ResponseUtil.build(AppConstant.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> findAll() {
        try {
            List<Product> products = productRepository.findAll();
            return ResponseUtil.build(AppConstant.SUCCESS, products, HttpStatus.OK);
        }catch (Exception e){
            return ResponseUtil.build(AppConstant.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> findById(String id) {
        try {
            Product product = productRepository.findById(id).orElse(null);
            if (product == null)  return ResponseUtil.build(AppConstant.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            return ResponseUtil.build(AppConstant.SUCCESS, product, HttpStatus.OK);
        }catch (Exception e){
            return ResponseUtil.build(AppConstant.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> update(String id, Product product) {
        try {
            Product productById = productRepository.findById(id).orElse(null);
            if (productById == null)  return ResponseUtil.build(AppConstant.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            productById.setName(product.getName());
            productById.setPrice(product.getPrice());
            productById.setQty(product.getQty());
            productRepository.save(productById);
            return ResponseUtil.build(AppConstant.SUCCESS, product, HttpStatus.OK);
        }catch (Exception e){
            return ResponseUtil.build(AppConstant.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> delete(String id) {
        try {
            Product productById = productRepository.findById(id).orElse(null);
            if (productById == null)  return ResponseUtil.build(AppConstant.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            productRepository.delete(productById);
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
