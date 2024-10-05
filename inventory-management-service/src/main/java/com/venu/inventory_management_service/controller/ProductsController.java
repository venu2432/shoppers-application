package com.venu.inventory_management_service.controller;


import com.venu.inventory_management_service.clients.OrderServiceClient;
import com.venu.inventory_management_service.dto.OrderRequestDto;
import com.venu.inventory_management_service.dto.ProductDto;
import com.venu.inventory_management_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService;
    private final OrderServiceClient orderServiceClient;

    @GetMapping("/getInventoryProducts")
    public ResponseEntity<List<ProductDto>> getInventoryProducts() {
        return ResponseEntity.ok(productService.getInventoryList());
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/fetchOrders")
    public String fetchFromOrderService(){
        return orderServiceClient.helloOrders();
    }

    @PostMapping("/reduceStock")
    public ResponseEntity<Double> reduceStock(@RequestBody OrderRequestDto orderRequestDto) {
        Double totalPrice = productService.reduceStock(orderRequestDto);
        return ResponseEntity.ok(totalPrice);

    }

}
