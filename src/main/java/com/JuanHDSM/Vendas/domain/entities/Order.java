package com.JuanHDSM.Vendas.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Entity(name = "tb_orders")
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

    @NotNull(message = "Campo total do pedido é obrigatório.")
    public Double getTotal() {
        return items.stream().map(OrderItem::getSubTotal).reduce(0.0, (Double::sum));
    }
}
