package com.JuanHDSM.Vendas.domain.dtos;

import com.JuanHDSM.Vendas.domain.entities.OrderItem;

public record RequestOrderDTO(
        Long clientId,
        OrderItem items
) {
}
