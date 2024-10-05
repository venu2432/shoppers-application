package com.venu.inventory_management_service.service;

import com.venu.inventory_management_service.dto.OrderRequestDto;
import com.venu.inventory_management_service.dto.ProductDto;

import java.util.List;

public interface ProductService {
    public List<ProductDto> getInventoryList();
    public ProductDto getProductById(Long id);
    Double reduceStock(OrderRequestDto orderRequestDto);
}
