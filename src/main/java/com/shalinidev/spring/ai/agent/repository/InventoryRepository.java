package com.shalinidev.spring.ai.agent.repository;

import com.shalinidev.spring.ai.agent.model.InventoryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItemEntity, Long> {
    Optional<InventoryItemEntity> findByProductName(String productName);
    List<InventoryItemEntity> findByQuantityGreaterThan(int quantity);
}
