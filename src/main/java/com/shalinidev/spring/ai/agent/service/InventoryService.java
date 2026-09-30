package com.shalinidev.spring.ai.agent.service;

import com.shalinidev.spring.ai.agent.model.InventoryItemEntity;
import com.shalinidev.spring.ai.agent.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public String checkStock(String productName) {
        return inventoryRepository.findByProductName(productName)
                .map(item -> "Product " + productName + " has " + item.getQuantity() + " items in stock.")
                .orElse("Product " + productName + " is not found in inventory.");
    }

    public Integer getTotalProductsInStock() {
        return inventoryRepository.findAll().stream()
                .mapToInt(InventoryItemEntity::getQuantity)
                .sum();
    }

    public List<String> getAllProductsInStock() {
        return inventoryRepository.findAll().stream()
                .filter(item -> item.getQuantity() > 0)
                .map(item -> item.getProductName() + " - " + item.getQuantity())
                .collect(Collectors.toList());
    }
}
