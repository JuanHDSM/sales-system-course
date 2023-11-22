package com.JuanHDSM.Vendas.domain.entities;

import com.JuanHDSM.Vendas.domain.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_items")
public class OrderItem {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();
    private Integer quantity;

    public OrderItem(Order order, Product product, Integer quantity) {
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Double getSubTotal() {
        return id.getProduct().getPrice() * quantity;
    }
}
