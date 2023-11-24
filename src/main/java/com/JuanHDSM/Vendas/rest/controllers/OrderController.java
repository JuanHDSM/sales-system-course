package com.JuanHDSM.Vendas.rest.controllers;

import com.JuanHDSM.Vendas.domain.dtos.RequestOrderDTO;
import com.JuanHDSM.Vendas.domain.dtos.RequestOrderItemDTO;
import com.JuanHDSM.Vendas.domain.dtos.ResponseOrderDTO;
import com.JuanHDSM.Vendas.rest.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/client/{id}")
    public ResponseEntity<List<ResponseOrderDTO>> findByClient(@PathVariable Long id) {
        List<ResponseOrderDTO> list = service.findByClient(id);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<ResponseOrderDTO> insert(@RequestBody RequestOrderDTO idClient) {
        ResponseOrderDTO obj = service.insert(idClient);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.id()).toUri();
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/order-item")
    public ResponseEntity<ResponseOrderDTO> insertOrderItem(@RequestBody RequestOrderItemDTO obj) {
        ResponseOrderDTO objDTO = service.insertOrderItem(obj);
        return ResponseEntity.ok().body(objDTO);
    }
}
