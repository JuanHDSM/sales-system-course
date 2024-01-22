package com.JuanHDSM.Vendas.domain.dtos;

import com.JuanHDSM.Vendas.domain.entities.User;
import com.JuanHDSM.Vendas.domain.entities.enums.UserRole;

public record ResponseUserDTO(
        String id,
        String login,
        UserRole role
        ) {
    public static ResponseUserDTO fromResponseUserDTO(User entity) {
        return new ResponseUserDTO(
                entity.getId(),
                entity.getLogin(),
                entity.getRole()
        );
    }
}
