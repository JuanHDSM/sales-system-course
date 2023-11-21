package com.JuanHDSM.Vendas.domain.dtos;

import com.JuanHDSM.Vendas.domain.entities.Client;

public record RequestClientDTO(String name) {
    public static RequestClientDTO fromClientDTO(Client entity) {
        return new RequestClientDTO(
                entity.getName()
        );
    }
}
