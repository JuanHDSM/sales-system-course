package com.JuanHDSM.Vendas.domain.entities;

import com.JuanHDSM.Vendas.domain.dtos.RequestProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
    private Double price;

    @JsonIgnore
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    public Product(Long id, String description, Double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public Product(RequestProductDTO obj) {
        this.description = obj.description();
        this.price = obj.price();
    }
}
