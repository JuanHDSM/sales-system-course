package com.JuanHDSM.Vendas.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException() {
        super("Pedido não encontrado.");
    }
}
