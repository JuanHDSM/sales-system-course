package com.JuanHDSM.Vendas.rest.controllers;

import com.JuanHDSM.Vendas.domain.dtos.RequestClientDTO;
import com.JuanHDSM.Vendas.domain.dtos.ResponseClientDTO;
import com.JuanHDSM.Vendas.rest.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "clients")
public class ClientController {
    @Autowired
    ClientService service;

    @GetMapping
    public List<ResponseClientDTO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseClientDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseClientDTO insert(@RequestBody @Valid RequestClientDTO obj) {
        return service.insert(obj);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseClientDTO update(@PathVariable Long id, @RequestBody @Valid RequestClientDTO obj) {
        return service.update(id, obj);
    }
}
