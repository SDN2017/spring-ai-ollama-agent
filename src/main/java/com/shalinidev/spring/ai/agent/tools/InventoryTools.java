package com.shalinidev.spring.ai.agent.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InventoryTools {
    private static final Logger log = LoggerFactory.getLogger(InventoryTools.class);

    private final Map<String, Integer> stock = Map.of(
            "Bluetooth Headphones", 5,
            "Wireless Mouse", 42,
            "USB-C Cable", 0,
            "Laptop Stand", 12,
            "External Hard Drive", 7,
            "Smartphone Case", 0,
            "Portable Charger", 15,
            "Gaming Keyboard", 3,
            "Webcam", 8,
            "Noise-Cancelling Earbuds", 0
    );

    @Tool(description = "Check the stock availability of a product by its name")
    public String checkStock(String productName) {
        log.info("calling checkStock Tool");
        log.info("Checking stock for product: {}", productName);
        Integer quantity = stock.get(productName);
        if (quantity == null) {
            return "Product not found";
        } else if (quantity > 0) {
            return "In Stock: " + quantity + " units available";
        } else {
            return "Out of Stock";
        }
    }

    @Tool(description = "Get the total count of products in stock")
    public Integer getTotalProductsInStock() {
        log.info("calling getTotalProductsInStock Tool");
        return stock.size();
    }

    // tools to get all products
    @Tool(description = "Get a list of all products in stock")
    public java.util.List<String> getAllProductsInStock() {
       log.info("calling getAllProductsInStock Tool");
        return stock.keySet().stream().filter(name -> stock.get(name) > 0).collect(Collectors.toList());
    }
}
