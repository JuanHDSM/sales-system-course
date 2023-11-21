package com.JuanHDSM.Vendas.domain.dtos;

import com.JuanHDSM.Vendas.domain.entities.Product;

import java.math.BigDecimal;

public record RequestProductDTO(String description, BigDecimal price) {
    public static RequestProductDTO fromRequestProductDTO(Product entity) {
        return new RequestProductDTO(
                entity.getDescription(),
                entity.getPrice()
        );
    }
}
