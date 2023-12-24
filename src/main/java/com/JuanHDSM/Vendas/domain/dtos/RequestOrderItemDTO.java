package com.JuanHDSM.Vendas.domain.dtos;

import jakarta.validation.constraints.NotNull;

public record RequestOrderItemDTO(
        @NotNull(message = "Informe o código do pedido.")
        Long orderId,
        @NotNull(message = "Informe o código do produto.")
        Long productId,
        @NotNull(message = "Campo quantidade é obrigatório.")
        int quantity
) {
}
