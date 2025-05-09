package com.bedclothes.bedclothes.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatePillowRequest {

    private String name;
    private String size;
    private String color;
    private String brand;
    private BigDecimal price;
    private String description;
    private int quantity;
}
