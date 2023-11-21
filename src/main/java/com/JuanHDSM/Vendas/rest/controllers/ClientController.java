package com.JuanHDSM.Vendas.rest.controllers;

import com.JuanHDSM.Vendas.domain.dtos.RequestClientDTO;
import com.JuanHDSM.Vendas.domain.dtos.ResponseClientDTO;
import com.JuanHDSM.Vendas.rest.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "clients")
public class ClientController {
    @Autowired
    ClientService service;
    @GetMapping
    public ResponseEntity<List<ResponseClientDTO>> findAll() {
        List<ResponseClientDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseClientDTO> findById(@PathVariable Long id) {
        ResponseClientDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<ResponseClientDTO> insert(@RequestBody RequestClientDTO obj) {
        ResponseClientDTO objDTO = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objDTO.id()).toUri();
        return ResponseEntity.created(uri).body(objDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseClientDTO> update(@PathVariable Long id, @RequestBody RequestClientDTO obj) {
        ResponseClientDTO objDTO = service.update(id, obj);
        return ResponseEntity.ok().body(objDTO);
    }
}
