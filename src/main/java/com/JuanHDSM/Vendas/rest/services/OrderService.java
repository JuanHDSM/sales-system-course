package com.JuanHDSM.Vendas.rest.services;

import com.JuanHDSM.Vendas.domain.dtos.ResponseOrderDTO;
import com.JuanHDSM.Vendas.domain.entities.Order;
import com.JuanHDSM.Vendas.domain.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository repository;

    public List<ResponseOrderDTO> findAll() {
        List<Order> list = repository.findAll();
        return list.stream().map(ResponseOrderDTO::fromResponseOrderDTO).toList();
    }

    public ResponseOrderDTO findById(Long id) {
        Order entity = repository.findById(id).get();
        return ResponseOrderDTO.fromResponseOrderDTO(entity);
    }

}
