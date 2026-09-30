package com.shalinidev.spring.ai.agent.tools;

import com.shalinidev.spring.ai.agent.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class OrderTools {
    private static final Logger log = LoggerFactory.getLogger(OrderTools.class);
    private final OrderService orderService;

    public OrderTools(OrderService orderService) {
        this.orderService = orderService;
    }

    @Tool(description = "Get the status of a customer order by its order ID")
    public String getOrderStatus(String orderId) {
        log.info("Getting order status for orderId={}", orderId);
        return orderService.getOrderStatus(orderId);
    }

    @Tool(description = "Cancel a customer order by its order ID")
    public String cancelOrder(String orderId) {
        log.info("Cancelling order for orderId={}", orderId);
        return orderService.cancelOrder(orderId);
    }

    @Tool(description = "Get the total count of customer orders")
    public Integer getOrderCount() {
        log.info("Fetching total order count");
        return orderService.getOrderCount();
    }
}
