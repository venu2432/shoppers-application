package com.venu.inventory_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InventoryManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementServiceApplication.class, args);
	}

}
