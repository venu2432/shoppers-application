package com.venu.order_management_service.dto;

import com.venu.order_management_service.entity.OrderItem;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequestDto {
    private Long orderId;
    private List<OrderItem> orderItems;
    private BigDecimal price;
}
