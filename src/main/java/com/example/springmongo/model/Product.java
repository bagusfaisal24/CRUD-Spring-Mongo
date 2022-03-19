package com.example.springmongo.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data
@Document("products")
public class Product {

    @Id
    private String id;
    private String name;
    private Integer qty;
    private Float price;
    private List<String> tags;
}
