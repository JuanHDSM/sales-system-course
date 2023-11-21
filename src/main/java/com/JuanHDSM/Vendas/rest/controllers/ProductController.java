package com.JuanHDSM.Vendas.rest.controllers;

import com.JuanHDSM.Vendas.domain.dtos.RequestProductDTO;
import com.JuanHDSM.Vendas.domain.dtos.ResponseProductDTO;
import com.JuanHDSM.Vendas.rest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<ResponseProductDTO> insert(@RequestBody RequestProductDTO obj) {
        ResponseProductDTO objDTO = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objDTO.id()).toUri();
        return ResponseEntity.created(uri).body(objDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
