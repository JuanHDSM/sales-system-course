package com.JuanHDSM.Vendas.domain.dtos;

public record RequestOrderItemDTO(
        Long orderId,
        Long productId,
        int quantity
) {
}
