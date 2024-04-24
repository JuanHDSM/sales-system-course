package com.JuanHDSM.Vendas.rest.controllers;

import com.JuanHDSM.Vendas.domain.dtos.RequestProductDTO;
import com.JuanHDSM.Vendas.domain.dtos.ResponseProductDTO;
import com.JuanHDSM.Vendas.rest.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "products")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping
    public List<ResponseProductDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseProductDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseProductDTO insert(@RequestBody @Valid RequestProductDTO obj) {
        return service.insert(obj);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseProductDTO update(
            @PathVariable Long id,
            @RequestBody @Valid RequestProductDTO obj
    ) {
        return service.update(id, obj);
    }
}
