package com.venu.inventory_management_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order-management-service", path = "/order-mgmt-service")
public interface OrderServiceClient {

    @GetMapping("/orders/helloOrders")
    String helloOrders();
}
