package com.JuanHDSM.Vendas.domain.dtos;

import com.JuanHDSM.Vendas.domain.entities.User;
import com.JuanHDSM.Vendas.domain.entities.enums.UserRole;
import jakarta.validation.constraints.NotEmpty;

public record RequestUserDTO(
        @NotEmpty(message = "Usuário não pode ser vazio.")
        String login,
        @NotEmpty(message = "Senha não pode ser vazia.")
        String password,
        @NotEmpty(message = "Papel não pode ser vazio")
        UserRole role
) {}
