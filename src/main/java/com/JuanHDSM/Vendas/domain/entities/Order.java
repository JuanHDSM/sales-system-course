package com.JuanHDSM.Vendas.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    private LocalDate orderDate;
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items;

    public Order(Long id, Client client, LocalDate orderDate) {
        this.id = id;
        this.client = client;
        this.orderDate = orderDate;
    }

    public Double getTotal() {
        Double sum = 0.0;
        for(OrderItem x : items) {
            sum += x.getSubTotal();
        }
        return sum;
    }
}
