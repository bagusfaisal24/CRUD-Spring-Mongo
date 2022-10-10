package com.example.springmongo.controller;

import com.example.springmongo.domain.form.DtoProduct;
import com.example.springmongo.service.RedisSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisSvc redisSvc;

    @Autowired
    public RedisController(RedisSvc redisSvc) {
        this.redisSvc = redisSvc;
    }

    @PostMapping("/set")
    public ResponseEntity<Object> setEntity(@RequestBody DtoProduct dtoProduct){
        return redisSvc.set(dtoProduct);
    }

    @PostMapping("/get")
    public ResponseEntity<Object> getEntity(@RequestBody DtoProduct dtoProduct){
        return redisSvc.get(dtoProduct);
    }
}
