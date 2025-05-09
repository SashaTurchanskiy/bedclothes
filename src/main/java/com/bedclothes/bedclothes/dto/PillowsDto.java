package com.bedclothes.bedclothes.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PillowsDto {
    private Long id;
    private String name;
    private String size;
    private String color;
    private String brand;
    private BigDecimal price;
    private String description;
    private int quantity;
}
