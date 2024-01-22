package com.JuanHDSM.Vendas.domain.dtos;

import jakarta.validation.constraints.NotEmpty;

public record AuthenticationDTO(
        @NotEmpty(message = "Login não pode ser vazio.")
        String login,
        @NotEmpty(message = "Senha não pode ser vazia.")
        String password
) {
}
