package com.JuanHDSM.Vendas.rest.controllers;

import com.JuanHDSM.Vendas.domain.dtos.RequestOrderDTO;
import com.JuanHDSM.Vendas.domain.dtos.RequestOrderItemDTO;
import com.JuanHDSM.Vendas.domain.dtos.ResponseOrderByClientDTO;
import com.JuanHDSM.Vendas.domain.dtos.ResponseOrderDTO;
import com.JuanHDSM.Vendas.rest.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "orders")
public class OrderController {
    @Autowired
    OrderService service;
    @GetMapping
    public List<ResponseOrderDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseOrderDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/client/{id}")
    public List<ResponseOrderByClientDTO> findByClient(@PathVariable Long id) {
        return service.findByClient(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseOrderDTO insert(@RequestBody RequestOrderDTO idClient) {
        return service.insert(idClient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/order-item")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseOrderDTO insertOrderItem(@RequestBody RequestOrderItemDTO obj) {
        return service.insertOrderItem(obj);
    }
}
