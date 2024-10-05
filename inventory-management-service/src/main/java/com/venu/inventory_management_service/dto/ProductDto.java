package com.venu.inventory_management_service.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String title;

    private Integer stockUnit;

    private Double price;
}
