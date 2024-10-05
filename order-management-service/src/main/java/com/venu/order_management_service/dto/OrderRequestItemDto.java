package com.venu.order_management_service.dto;


import lombok.Data;

@Data
public class OrderRequestItemDto {
    private Long productId;
    private Integer productQuantity;
}
