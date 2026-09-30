package com.shalinidev.spring.ai.agent.repository;

import com.shalinidev.spring.ai.agent.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
}
