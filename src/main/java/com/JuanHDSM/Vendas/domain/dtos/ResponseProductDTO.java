package com.JuanHDSM.Vendas.domain.dtos;

import com.JuanHDSM.Vendas.domain.entities.Product;

import java.math.BigDecimal;

public record ResponseProductDTO(Long id, String description, BigDecimal price) {
    public static ResponseProductDTO fromResponseProductDTO(Product entity) {
        return new ResponseProductDTO(
                entity.getId(),
                entity.getDescription(),
                entity.getPrice()
        );
    }
}
