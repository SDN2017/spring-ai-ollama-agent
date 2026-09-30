package com.shalinidev.spring.ai.agent.service;

import com.shalinidev.spring.ai.agent.model.OrderEntity;
import com.shalinidev.spring.ai.agent.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String getOrderStatus(String orderId) {
        return orderRepository.findById(orderId)
                .map(OrderEntity::getStatus)
                .orElse("Order not found");
    }

    public String cancelOrder(String orderId) {
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            return "Order not found";
        }

        OrderEntity entity = order.get();
        entity.setStatus("Cancelled - cancelled by customer");
        orderRepository.save(entity);
        return "Order " + orderId + " has been cancelled.";
    }

    public Integer getOrderCount() {
        return Math.toIntExact(orderRepository.count());
    }
}
