package com.example.springmongo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/test")
public class TestContoller {


    @GetMapping
    public String helloWorld(){
        return "Hello World";
    }
}
