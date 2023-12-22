package com.JuanHDSM.Vendas.domain.dtos;

import com.JuanHDSM.Vendas.domain.entities.Client;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

public record RequestClientDTO(
        @NotEmpty(message = "Campo nome é obrigatório")
        String name,
        @NotEmpty(message = "Campo CPF é obrigatório")
        @CPF(message = "Informe um CPF válido.")
        String cpf
) {
    public static RequestClientDTO fromClientDTO(Client entity) {
        return new RequestClientDTO(
                entity.getName(),
                entity.getCpf()
        );
    }
}
