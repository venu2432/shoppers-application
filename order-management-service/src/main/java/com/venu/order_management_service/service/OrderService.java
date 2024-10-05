package com.venu.order_management_service.service;

import com.venu.order_management_service.dto.OrderRequestDto;
import java.util.List;

public interface OrderService {
    public List<OrderRequestDto> getAllOrder();
    public OrderRequestDto getOrderById(Long id);
    OrderRequestDto createOrder(OrderRequestDto orderRequestDto);
}
