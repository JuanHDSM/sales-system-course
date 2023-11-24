package com.JuanHDSM.Vendas.domain.dtos;

import com.JuanHDSM.Vendas.domain.entities.Client;
import com.JuanHDSM.Vendas.domain.entities.Order;
import com.JuanHDSM.Vendas.domain.entities.OrderItem;

import java.time.LocalDate;
import java.util.Set;

public record ResponseOrderByClientDTO(
        Long id,

        Client client,
        LocalDate orderDate,
        Set<OrderItem> items,
        Double total
) {
    public static ResponseOrderByClientDTO fromResponseOrderDTO(Order entity) {
        return new ResponseOrderByClientDTO(
        entity.getId(),
        entity.getClient(),
        entity.getOrderDate(),
        entity.getItems(),
        entity.getTotal()
        );
    }
}
