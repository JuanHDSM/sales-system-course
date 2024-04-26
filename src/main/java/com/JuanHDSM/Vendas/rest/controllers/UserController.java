package com.JuanHDSM.Vendas.rest.controllers;

import com.JuanHDSM.Vendas.domain.dtos.ResponseUserDTO;
import com.JuanHDSM.Vendas.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public List<ResponseUserDTO> findAll() {
         return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseUserDTO findById(@PathVariable String id) {
        return service.findById(id);
    }
}
