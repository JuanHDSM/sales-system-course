package com.JuanHDSM.Vendas.rest.controllers;

import com.JuanHDSM.Vendas.config.infra.TokenService;
import com.JuanHDSM.Vendas.domain.dtos.AuthenticationDTO;
import com.JuanHDSM.Vendas.domain.dtos.LoginResponseDTO;
import com.JuanHDSM.Vendas.domain.dtos.RequestUserDTO;
import com.JuanHDSM.Vendas.domain.dtos.ResponseUserDTO;
import com.JuanHDSM.Vendas.domain.entities.User;
import com.JuanHDSM.Vendas.domain.repositories.UserRepository;
import com.JuanHDSM.Vendas.rest.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthorizationController {
    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity findAll() {
        List<ResponseUserDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = authenticationManager.authenticate((usernamePassword));

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity register(@RequestBody @Valid RequestUserDTO data) {
        if (this.repository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        this.service.insert(data);

        return ResponseEntity.ok().build();

    }
}
