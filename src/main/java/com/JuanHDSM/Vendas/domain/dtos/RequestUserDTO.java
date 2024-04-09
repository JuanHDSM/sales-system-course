package com.JuanHDSM.Vendas.domain.dtos;

import com.JuanHDSM.Vendas.domain.entities.User;
import com.JuanHDSM.Vendas.domain.entities.enums.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RequestUserDTO(
        @NotEmpty(message = "Usuário não pode ser vazio.")
        String login,
        @NotEmpty(message = "Senha não pode ser vazia.")
        String password,
        @NotNull(message = "Papel não pode ser vazio")
        UserRole role
) {}
