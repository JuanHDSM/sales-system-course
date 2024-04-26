package com.JuanHDSM.Vendas.rest.controllers;

import com.JuanHDSM.Vendas.domain.dtos.RequestUserDTO;
import com.JuanHDSM.Vendas.domain.dtos.ResponseUserDTO;
import com.JuanHDSM.Vendas.rest.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseUserDTO update(@RequestBody @Valid RequestUserDTO obj, @PathVariable String id) {
        if (!obj.password().matches("^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[^\\w\\s]).{8,}$")) {
            throw new RuntimeException(
                    "Senha precisa ser maior que 8 caracteres.\n" +
                            "Senha precisa ter pelo menos um número.\n" +
                            "Senha precisa ter pelomenos uma letra.\n" +
                            "Senha precisa ter pelo menos um caractere especial.");
        }
        if (!obj.password().equals(obj.passwordConfirm())) {
            throw new RuntimeException("As senhas não coincidem");
        }
        return service.update(obj, id);
    }
}
