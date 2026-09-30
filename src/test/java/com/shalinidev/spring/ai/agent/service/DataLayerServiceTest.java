package com.shalinidev.spring.ai.agent.service;

import com.shalinidev.spring.ai.agent.model.InventoryItemEntity;
import com.shalinidev.spring.ai.agent.model.OrderEntity;
import com.shalinidev.spring.ai.agent.repository.InventoryRepository;
import com.shalinidev.spring.ai.agent.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({OrderService.class, InventoryService.class})
class DataLayerServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        inventoryRepository.deleteAll();

        orderRepository.save(new OrderEntity("1042", "Shipped - arriving tomorrow"));
        inventoryRepository.save(new InventoryItemEntity("Bluetooth Headphones", 5));
        inventoryRepository.save(new InventoryItemEntity("USB-C Cable", 0));
    }

    @Test
    void orderServiceShouldReturnPersistedOrderStatus() {
        assertThat(orderService.getOrderStatus("1042")).isEqualTo("Shipped - arriving tomorrow");
    }

    @Test
    void inventoryServiceShouldReportStockForPersistedProduct() {
        assertThat(inventoryService.checkStock("Bluetooth Headphones"))
                .isEqualTo("Product Bluetooth Headphones has 5 items in stock.");
    }

    @Test
    void inventoryServiceShouldReturnTotalProductsInStock() {
        assertThat(inventoryService.getTotalProductsInStock()).isEqualTo(5);
    }
}
