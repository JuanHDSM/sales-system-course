package com.JuanHDSM.Vendas.rest.controllers;

import com.JuanHDSM.Vendas.config.infra.security.TokenService;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public LoginResponseDTO login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new LoginResponseDTO(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseUserDTO register(@RequestBody @Valid RequestUserDTO user) {
        if (this.repository.findByLogin(user.login()) != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado");

        if (!user.password().matches("^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[^\\w\\s]).{8,}$")) {
            throw new RuntimeException(
                    "Senha precisa ser maior que 8 caracteres.\n" +
                    "Senha precisa ter pelo menos um número.\n" +
                    "Senha precisa ter pelomenos uma letra.\n" +
                    "Senha precisa ter pelo menos um caractere especial.");
        }
        if (!user.password().equals(user.passwordConfirm())) {
            throw new RuntimeException("As senhas não coincidem");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.password());

        User newUser = new User(user.login(), encryptedPassword, user.role());

        this.service.insert(newUser);

        return ResponseUserDTO.fromResponseUserDTO(newUser);
    }
}
