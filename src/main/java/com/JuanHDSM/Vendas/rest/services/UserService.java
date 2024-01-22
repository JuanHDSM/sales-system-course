package com.JuanHDSM.Vendas.rest.services;

import com.JuanHDSM.Vendas.domain.dtos.RequestUserDTO;
import com.JuanHDSM.Vendas.domain.dtos.ResponseUserDTO;
import com.JuanHDSM.Vendas.domain.entities.User;
import com.JuanHDSM.Vendas.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    public List<ResponseUserDTO> findAll() {
        List<User> list = repository.findAll();
        return list.stream().map(ResponseUserDTO::fromResponseUserDTO).toList();
    }

    public ResponseUserDTO findById(String id) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        return ResponseUserDTO.fromResponseUserDTO(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    public User insert(User obj) {
        return repository.save(obj);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public ResponseUserDTO update(RequestUserDTO objDTO, String id) {
        User obj = new User(objDTO);
        User entity = repository.findById(id)
                .map(user -> {
                            obj.setId(user.getId());
                            repository.save(obj);
                            return obj;
                        }
                ).orElseThrow( () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Id do usuário inválido."));
        return ResponseUserDTO.fromResponseUserDTO(entity);
    }
}
