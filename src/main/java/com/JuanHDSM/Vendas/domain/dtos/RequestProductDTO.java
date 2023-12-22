package com.JuanHDSM.Vendas.domain.dtos;

import com.JuanHDSM.Vendas.domain.entities.Product;

public record RequestProductDTO(String description, Double price) {
    public static RequestProductDTO fromRequestProductDTO(Product entity) {
        return new RequestProductDTO(
                entity.getDescription(),
                entity.getPrice()
        );
    }
}
