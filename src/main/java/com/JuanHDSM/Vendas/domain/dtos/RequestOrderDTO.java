package com.JuanHDSM.Vendas.domain.dtos;

import jakarta.validation.constraints.NotNull;

public record RequestOrderDTO(
        @NotNull(message = "Informe o código do cliente.")
        Long clientId
) {
}
