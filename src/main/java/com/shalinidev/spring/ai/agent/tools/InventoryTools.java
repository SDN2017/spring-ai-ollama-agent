package com.shalinidev.spring.ai.agent.tools;

import com.shalinidev.spring.ai.agent.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryTools {
    private static final Logger log = LoggerFactory.getLogger(InventoryTools.class);
    private final InventoryService inventoryService;

    public InventoryTools(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Tool(description = "Check the stock availability of a product by its name")
    public String checkStock(String productName) {
        log.info("Checking stock for product: {}", productName);
        return inventoryService.checkStock(productName);
    }

    @Tool(description = "Get the total count of products in stock")
    public Integer getTotalProductsInStock() {
        log.info("Fetching total products in stock");
        return inventoryService.getTotalProductsInStock();
    }

    @Tool(description = "Get a list of all products in stock")
    public List<String> getAllProductsInStock() {
        log.info("Fetching all products in stock");
        return inventoryService.getAllProductsInStock();
    }
}
