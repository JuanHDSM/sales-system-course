package com.JuanHDSM.Vendas.domain.dtos;

import com.JuanHDSM.Vendas.domain.entities.Client;
import com.JuanHDSM.Vendas.domain.entities.Order;
import com.JuanHDSM.Vendas.domain.entities.OrderItem;

import java.time.LocalDate;
import java.util.Set;

public record ResponseOrderDTO(
        Long id,
        Client client,
        LocalDate orderDate,
        Set<OrderItem> items,
        Double total
) {
    public static ResponseOrderDTO fromResponseOrderDTO(Order entity) {
        return new ResponseOrderDTO(
        entity.getId(),
        entity.getClient(),
        entity.getOrderDate(),
        entity.getItems(),
        entity.getTotal()
        );
    }
}
