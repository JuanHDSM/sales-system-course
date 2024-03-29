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

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;
    public List<ResponseClientDTO> findAll() {
        List<Client> list = repository.findAll();
        return list.stream().map(ResponseClientDTO::fromResponseClientDTO).toList();
    }

    public ResponseClientDTO findById(Long id) {
        Client entity = repository.findById(id)
                .orElseThrow( () -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        return ResponseClientDTO.fromResponseClientDTO(entity);
    }

    public ResponseClientDTO insert(RequestClientDTO obj) {
        Client entity = new Client(obj);
        if (findAll().stream().map(ResponseClientDTO::cpf).anyMatch(cpf -> cpf.equalsIgnoreCase(entity.getCpf()))){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "CPF já cadastrado."
            );
        }
        repository.save(entity);
        return ResponseClientDTO.fromResponseClientDTO(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
    }

    public ResponseClientDTO update(Long id, RequestClientDTO objDTO) {
        Client obj = new Client(objDTO);
        Client entity = repository.findById(id)
                .map(c -> {
                    obj.setId(c.getId());
                    repository.save(obj);
                    return obj;
                })
                .orElseThrow( () -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        return ResponseClientDTO.fromResponseClientDTO(entity);
    }

}
