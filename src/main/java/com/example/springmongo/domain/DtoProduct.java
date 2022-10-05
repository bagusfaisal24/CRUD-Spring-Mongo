package com.example.springmongo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Id;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DtoProduct {

    private String id;
    private String name;
    private Integer qty;
    private Float price;
}
