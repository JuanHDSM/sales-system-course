package com.JuanHDSM.Vendas.rest.services;

import com.JuanHDSM.Vendas.domain.dtos.RequestClientDTO;
import com.JuanHDSM.Vendas.domain.dtos.ResponseClientDTO;
import com.JuanHDSM.Vendas.domain.entities.Client;
import com.JuanHDSM.Vendas.domain.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Client entity = repository.findById(id).get();
        return ResponseClientDTO.fromResponseClientDTO(entity);
    }

    public ResponseClientDTO insert(RequestClientDTO obj) {
        Client entity = new Client(obj);
        repository.save(entity);
        return ResponseClientDTO.fromResponseClientDTO(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public ResponseClientDTO update(Long id, RequestClientDTO obj) {
        Client entity = repository.getReferenceById(id);
        updateData(entity, obj);
        repository.save(entity);
        return ResponseClientDTO.fromResponseClientDTO(entity);
    }

    private void updateData(Client entity, RequestClientDTO obj) {
        entity.setName(obj.name());
    }
}
