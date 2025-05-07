package com.bedclothes.bedclothes.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateClothesRequest {
    private BigDecimal price;
    private String description;


}
