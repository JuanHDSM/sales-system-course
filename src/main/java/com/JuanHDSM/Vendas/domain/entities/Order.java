package com.JuanHDSM.Vendas.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_orders")
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

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

    public Order(Client client, LocalDate orderDate, Set<OrderItem> items) {
        this.client = client;
        this.orderDate = orderDate;
        this.items = items;
    }

    public Double getTotal() {
        Double sum = 0.0;
        for(OrderItem x : items) {
            sum += x.getSubTotal();
        }
        return sum;
    }
}
