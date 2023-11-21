package com.JuanHDSM.Vendas.domain.repositories;

import com.JuanHDSM.Vendas.domain.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
