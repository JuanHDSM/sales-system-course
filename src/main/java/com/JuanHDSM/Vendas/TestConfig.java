package com.JuanHDSM.Vendas;

import com.JuanHDSM.Vendas.domain.entities.Client;
import com.JuanHDSM.Vendas.domain.entities.Order;
import com.JuanHDSM.Vendas.domain.entities.OrderItem;
import com.JuanHDSM.Vendas.domain.entities.Product;
import com.JuanHDSM.Vendas.domain.repositories.ClientRepository;
import com.JuanHDSM.Vendas.domain.repositories.OrderItemRepository;
import com.JuanHDSM.Vendas.domain.repositories.OrderRepository;
import com.JuanHDSM.Vendas.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Override
    public void run(String... args) throws Exception {
        Client c = new Client(null, "Ciclano", null);
        Client c1 = new Client(null, "Fulano", null);

        clientRepository.saveAll(Arrays.asList(c, c1));

        Product p = new Product(null, "TV", 1800.00);
        Product p1 = new Product(null, "Monitor", 780.00);

        productRepository.saveAll(Arrays.asList(p, p1));

        Order o = new Order(null, c1, LocalDate.now());

        orderRepository.save(o);

        OrderItem oi = new OrderItem(o, p, 1);

        OrderItem oi1 = new OrderItem(o, p1, 2);

        orderItemRepository.saveAll(Arrays.asList(oi, oi1));
    }
}
