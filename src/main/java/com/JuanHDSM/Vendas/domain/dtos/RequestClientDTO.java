package com.JuanHDSM.Vendas.domain.dtos;

import com.JuanHDSM.Vendas.domain.entities.Client;

public record RequestClientDTO(String name, String cpf) {
    public static RequestClientDTO fromClientDTO(Client entity) {
        return new RequestClientDTO(
                entity.getName(),
                entity.getCpf()
        );
    }
}
