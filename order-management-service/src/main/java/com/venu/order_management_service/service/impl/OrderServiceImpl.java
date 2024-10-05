package com.venu.order_management_service.service.impl;

import com.venu.order_management_service.clients.InventoryServiceClient;
import com.venu.order_management_service.dto.OrderRequestDto;
import com.venu.order_management_service.entity.OrderItem;
import com.venu.order_management_service.entity.OrderStatus;
import com.venu.order_management_service.entity.Orders;
import com.venu.order_management_service.repository.OrderRepository;
import com.venu.order_management_service.service.OrderService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final InventoryServiceClient inventoryServiceClient;

    public List<OrderRequestDto> getAllOrder(){
        log.info("fetching all order");
        List<Orders> orders = orderRepository.findAll();
        return orders.stream()
                .map(items -> modelMapper.map(items, OrderRequestDto.class))
                .toList();
    }

    public OrderRequestDto getOrderById(Long id){
        log.info("fetching order by id: {}", id);
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return modelMapper.map(order, OrderRequestDto.class);
    }

    @Override
    @Retry(name = "inventoryRetry", fallbackMethod = "createOrderFallBack")
    public OrderRequestDto createOrder(OrderRequestDto orderRequestDto) {
            log.info("calling the createOrder method");
            Double totalPrice = inventoryServiceClient.reduceStock(orderRequestDto).getBody();
            Orders orders = modelMapper.map(orderRequestDto, Orders.class);
            for (OrderItem item : orders.getOrderItems()) {
                item.setOrder(orders);
            }
            orders.setPrice(totalPrice);
            orders.setOrderStatus(OrderStatus.CONFIRMED);
            Orders savedOrder = orderRepository.save(orders);
            return modelMapper.map(savedOrder, OrderRequestDto.class);
    }

    public OrderRequestDto createOrderFallBack(OrderRequestDto orderRequestDto, Throwable throwable){
        log.error("Fall back occurred due to: {}",throwable.getMessage());
        return new OrderRequestDto();
    }

}
