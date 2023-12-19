package com.JuanHDSM.Vendas.domain.dtos;

import com.JuanHDSM.Vendas.domain.entities.Client;

public record ResponseClientDTO(Long id, String name, String cpf) {
    public static ResponseClientDTO fromResponseClientDTO(Client entity) {
        return new ResponseClientDTO(
                entity.getId(),
                entity.getName(),
                entity.getCpf()
        );
    }
}
