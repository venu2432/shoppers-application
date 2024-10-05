package com.venu.inventory_management_service.service.impl;

import com.venu.inventory_management_service.dto.OrderRequestDto;
import com.venu.inventory_management_service.dto.OrderRequestItemDto;
import com.venu.inventory_management_service.entity.Product;
import com.venu.inventory_management_service.dto.ProductDto;
import com.venu.inventory_management_service.repository.ProductRepository;
import com.venu.inventory_management_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDto> getInventoryList(){
        log.info("fetching the InventoryList");
        List<Product> inventoryList = productRepository.findAll();
        return inventoryList.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    public ProductDto getProductById(Long id){
        log.info("fetching the Product by ID: {}", id);
        Optional<Product> item = productRepository.findById(id);
        return item.map(product -> modelMapper.map(product, ProductDto.class))
                .orElseThrow(() -> new RuntimeException("item not found in inventory"));

    }

    @Override
    @Transactional
    public Double reduceStock(OrderRequestDto orderRequestDto) {
        log.info("reducing stock units");
        Double totalPrice = 0.0;
        for(OrderRequestItemDto itemDto : orderRequestDto.getItems()){
            Long productId = itemDto.getProductId();
            Integer quantity = itemDto.getProductQuantity();
            Product product = productRepository.findById(productId).orElseThrow(
                    () -> new RuntimeException("product not found in inventory")
            );
            if(product.getStockUnit() < quantity){
                throw new RuntimeException("Product cannot be fulfilled for this order");
            }
            product.setStockUnit(product.getStockUnit() - quantity);
            productRepository.save(product);
            totalPrice += quantity * product.getPrice();
        }
        return totalPrice;
    }

}
