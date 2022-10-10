package com.example.springmongo.service;

import com.example.springmongo.cache.RedisOperations;
import com.example.springmongo.constant.AppConstant;
import com.example.springmongo.domain.Product;
import com.example.springmongo.domain.ResponseUtil;
import com.example.springmongo.domain.form.DtoProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RedisSvcImpl implements RedisSvc{

    private static final String HASHKEY = "product";
    private final RedisOperations redisOperations;

    @Autowired
    public RedisSvcImpl(RedisOperations redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Override
    public ResponseEntity<Object> set(DtoProduct dtoProduct) {
        try {
            String productName = dtoProduct.getName();
            redisOperations.set(dtoProduct.getKey(), productName);
            return ResponseUtil.build(AppConstant.SUCCESS, productName, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.build(AppConstant.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> get(DtoProduct dtoProduct) {
        try {
            Object result = redisOperations.get(dtoProduct.getKey());
            return ResponseUtil.build(AppConstant.SUCCESS, result, HttpStatus.OK);
        }catch (Exception e){
            return ResponseUtil.build(AppConstant.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void putIfAbsen(Product product) {
        redisOperations.putIfAbsen(HASHKEY, product.getId(), product);
    }

    @Override
    public Object hGet() {
        return redisOperations.hget(HASHKEY);
    }

    @Override
    public Object getById(String id) {
        return redisOperations.hget(HASHKEY, id);
    }

    @Override
    public void put(Product product) {
        redisOperations.hset(HASHKEY, product.getId(), product);
    }

    @Override
    public void delete(String id) {
        redisOperations.deleteByHashKey(HASHKEY, id);
    }
}
