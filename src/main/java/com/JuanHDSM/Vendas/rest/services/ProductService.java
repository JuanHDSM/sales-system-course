package com.JuanHDSM.Vendas.rest.services;

import com.JuanHDSM.Vendas.domain.dtos.RequestProductDTO;
import com.JuanHDSM.Vendas.domain.dtos.ResponseProductDTO;
import com.JuanHDSM.Vendas.domain.entities.Product;
import com.JuanHDSM.Vendas.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;
    public List<ResponseProductDTO> findAll() {
        List<Product> list = repository.findAll();
        return list.stream().map(ResponseProductDTO::fromResponseProductDTO).toList();
    }

    public ResponseProductDTO findById(Long id) {
        Product entity = repository.findById(id).get();
        return ResponseProductDTO.fromResponseProductDTO(entity);
    }

    public ResponseProductDTO insert(RequestProductDTO obj) {
        Product entity = new Product(obj);
        repository.save(entity);
        return ResponseProductDTO.fromResponseProductDTO(entity);
    }
}
