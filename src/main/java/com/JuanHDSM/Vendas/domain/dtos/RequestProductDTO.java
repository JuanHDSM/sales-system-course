package com.JuanHDSM.Vendas.domain.dtos;

import com.JuanHDSM.Vendas.domain.entities.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RequestProductDTO(
        @NotEmpty(message = "Campo descrição é obrigatório.")
        String description,
        @NotNull(message = "Campo preço é obrigatório.")
        Double price) {
    public static RequestProductDTO fromRequestProductDTO(Product entity) {
        return new RequestProductDTO(
                entity.getDescription(),
                entity.getPrice()
        );
    }
}
