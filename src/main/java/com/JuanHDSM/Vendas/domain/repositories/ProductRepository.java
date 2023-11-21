package com.JuanHDSM.Vendas.domain.repositories;

import com.JuanHDSM.Vendas.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
