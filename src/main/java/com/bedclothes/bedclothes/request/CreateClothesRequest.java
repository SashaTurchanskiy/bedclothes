package com.bedclothes.bedclothes.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateClothesRequest {

    private String clothesName;
    private String brand;
    private String color;
    private String size;
    private BigDecimal price;
    private String description;
    //private Image image;
}
