package com.JuanHDSM.Vendas.rest.controllers;

import com.JuanHDSM.Vendas.domain.dtos.ResponseOrderDTO;
import com.JuanHDSM.Vendas.domain.entities.Order;
import com.JuanHDSM.Vendas.rest.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "orders")
public class OrderController {
    @Autowired
    OrderService service;
    @GetMapping
    public ResponseEntity<List<ResponseOrderDTO>> findAll() {
        List<ResponseOrderDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrderDTO> findById(@PathVariable Long id) {
        ResponseOrderDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }


}
