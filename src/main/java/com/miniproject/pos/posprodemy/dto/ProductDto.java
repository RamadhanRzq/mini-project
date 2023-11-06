package com.miniproject.pos.posprodemy.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String productName;
    private Long categoryId;
    private double productPrice;
    private String productDescription;
    private String imageName;
}
