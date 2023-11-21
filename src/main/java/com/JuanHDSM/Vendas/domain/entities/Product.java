package com.JuanHDSM.Vendas.domain.entities;

import com.JuanHDSM.Vendas.domain.dtos.RequestProductDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(precision = 20, scale = 2)
    private BigDecimal price;

    public Product(RequestProductDTO obj) {
        this.description = obj.description();
        this.price = obj.price();
    }
}
