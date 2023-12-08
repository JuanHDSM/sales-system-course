package com.JuanHDSM.Vendas.rest.services;

import com.JuanHDSM.Vendas.domain.dtos.RequestClientDTO;
import com.JuanHDSM.Vendas.domain.dtos.ResponseClientDTO;
import com.JuanHDSM.Vendas.domain.entities.Client;
import com.JuanHDSM.Vendas.domain.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;
    public List<ResponseClientDTO> findAll() {
        List<Client> list = repository.findAll();
        return list.stream().map(ResponseClientDTO::fromResponseClientDTO).toList();
    }

    public ResponseClientDTO findById(Long id) {
        Optional<Client> entity = repository.findById(id);
        return ResponseClientDTO.fromResponseClientDTO
                (entity.orElseThrow( () -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Cliente não encontrado")));
    }

    public ResponseClientDTO insert(RequestClientDTO obj) {
        Client entity = new Client(obj);
        repository.save(entity);
        return ResponseClientDTO.fromResponseClientDTO(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
    }

    public ResponseClientDTO update(Long id, RequestClientDTO obj) {
        Optional<Client> entity = repository.findById(id);
        updateData(entity.get(), obj);
        repository.save(entity.get());
        return ResponseClientDTO.fromResponseClientDTO
                (entity.orElseThrow( () -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Cliente não encontrado")));
    }

    private void updateData(Client entity, RequestClientDTO obj) {
        entity.setName(obj.name());
    }
}
