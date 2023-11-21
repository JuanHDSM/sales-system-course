package com.JuanHDSM.Vendas.rest.controllers;

import com.JuanHDSM.Vendas.domain.dtos.ResponseProductDTO;
import com.JuanHDSM.Vendas.rest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "products")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping
    public ResponseEntity<List<ResponseProductDTO>> findAll() {
        List<ResponseProductDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> findById(@PathVariable Long id) {
        ResponseProductDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
