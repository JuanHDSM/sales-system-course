package com.JuanHDSM.Vendas.rest.services;

import com.JuanHDSM.Vendas.domain.dtos.RequestOrderDTO;
import com.JuanHDSM.Vendas.domain.dtos.RequestOrderItemDTO;
import com.JuanHDSM.Vendas.domain.dtos.ResponseOrderByClientDTO;
import com.JuanHDSM.Vendas.domain.dtos.ResponseOrderDTO;
import com.JuanHDSM.Vendas.domain.entities.Client;
import com.JuanHDSM.Vendas.domain.entities.Order;
import com.JuanHDSM.Vendas.domain.entities.OrderItem;
import com.JuanHDSM.Vendas.domain.entities.Product;
import com.JuanHDSM.Vendas.domain.pk.OrderItemPK;
import com.JuanHDSM.Vendas.domain.repositories.ClientRepository;
import com.JuanHDSM.Vendas.domain.repositories.OrderItemRepository;
import com.JuanHDSM.Vendas.domain.repositories.OrderRepository;
import com.JuanHDSM.Vendas.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    OrderRepository repository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ProductRepository productRepository;

    public List<ResponseOrderDTO> findAll() {
        List<Order> list = repository.findAll();
        return list.stream().map(ResponseOrderDTO::fromResponseOrderDTO).toList();
    }

    public ResponseOrderDTO findById(Long id) {
        Order entity = repository.findById(id).get();
        return ResponseOrderDTO.fromResponseOrderDTO(entity);
    }

    public List<ResponseOrderByClientDTO> findByClient(Long id) {
        Client client = clientRepository.findById(id).get();
        List<Order> list = repository.findByClient(client);
        return list.stream().map(ResponseOrderByClientDTO::fromResponseOrderDTO).toList();
    }

    public ResponseOrderDTO insert(RequestOrderDTO obj) {
        Client client = clientRepository.findById(obj.clientId()).get();
        Set<OrderItem> items = new HashSet<>();
        Order entity = new Order(client, LocalDate.now(), items);
        repository.save(entity);
        return ResponseOrderDTO.fromResponseOrderDTO(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ResponseOrderDTO insertOrderItem(RequestOrderItemDTO obj) {
        Order order = repository.findById(obj.orderId()).get();
        Product product = productRepository.findById(obj.productId()).get();
        OrderItemPK id = new OrderItemPK();
        id.setOrder(order);
        id.setProduct(product);
        OrderItem item = new OrderItem(id, obj.quantity());
        if(item.getQuantity() <= 0) {
            orderItemRepository.delete(item);
        } else {
            orderItemRepository.save(item);
        }
        return ResponseOrderDTO.fromResponseOrderDTO(order);
    }
}
