package com.example.springmongo.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Data
@Document("products")
public class Product implements Serializable {

    private static final long serialVersionUID = 5997353193872930935L;
    
    @Id
    private String id;
    private String name;
    private Integer qty;
    private Float price;
}
