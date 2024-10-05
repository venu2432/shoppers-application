package com.venu.order_management_service.clients;

import com.venu.order_management_service.dto.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-management-service", path = "/inventory-mgmt-service")
public interface InventoryServiceClient {

    @PostMapping("/products/reduceStock")
    ResponseEntity<Double> reduceStock(@RequestBody OrderRequestDto orderRequestDto);

}
