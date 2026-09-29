package com.shalinidev.spring.ai.agent.tools;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderTools {
    private static final Logger log = LoggerFactory.getLogger(OrderTools.class);
    private final Map<String, String> orders = Map.of(
            "1042", "Shipped - arriving tomorrow",
            "1043", "Processing - not yet shipped",
            "1044", "Cancelled - out of stock",
            "1045", "Shipped - arriving next week"
    );

    @Tool(description = "Get the status of an customer order by its  order ID")
    public String getOrderStatus(String orderId) {
        log.info("Getting order status for orderId={}", orderId);
        return orders.getOrDefault(orderId, "Order not found");
    }

    @Tool(description = "Cancel a customer order by its order ID")
    public String cancelOrder(String orderId) {
        log.info("Cancelling order for orderId={}", orderId);
        if (orders.containsKey(orderId)) {
            return "Order " + orderId + " has been cancelled.";
        } else {
            return "Order not found";
        }
    }

    @Tool(description = "Get the total count of customer orders")
    public Integer getOrderCount() {
        log.info("Fetching total order count");
        return orders.size();
    }

}
