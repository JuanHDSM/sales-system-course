package com.JuanHDSM.Vendas;

import com.JuanHDSM.Vendas.domain.entities.Client;
import com.JuanHDSM.Vendas.domain.entities.Product;
import com.JuanHDSM.Vendas.domain.repositories.ClientRepository;
import com.JuanHDSM.Vendas.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ClientRepository clientRepository;
    @Override
    public void run(String... args) throws Exception {
        Client c = new Client(null, "Ciclano", null);
        Client c1 = new Client(null, "Fulano", null);

        clientRepository.saveAll(Arrays.asList(c, c1));

        Product p = new Product(null, "TV", BigDecimal.valueOf(100));
        Product p1 = new Product(null, "Monitor", BigDecimal.valueOf(780));

        productRepository.saveAll(Arrays.asList(p, p1));
    }
}
