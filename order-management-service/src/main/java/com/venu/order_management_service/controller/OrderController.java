package com.venu.order_management_service.controller;

import com.venu.order_management_service.dto.OrderRequestDto;
import com.venu.order_management_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderRequestDto>> getAllOrders() {
        log.info("fetching all orders via controller");
        List<OrderRequestDto> orders = orderService.getAllOrder();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/getOrder/{id}")
    public ResponseEntity<OrderRequestDto> getOrder(@PathVariable long id) {
        log.info("fetching order via controller");
        OrderRequestDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/helloOrders")
    public String helloOrders() {
        return "hello orders";
    }

    @PostMapping("/create-order")
    public OrderRequestDto createOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderRequestDto requestDto = orderService.createOrder(orderRequestDto);
        return requestDto;
    }
}
