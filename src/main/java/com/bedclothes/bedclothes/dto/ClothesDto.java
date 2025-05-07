package com.bedclothes.bedclothes.dto;

import com.bedclothes.bedclothes.model.Image;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClothesDto {
    private Long id;
    private String clothesName;
    private String brand;
    private String color;
    private String size;
    private BigDecimal price;
    private String description;
    private Image image;
}
