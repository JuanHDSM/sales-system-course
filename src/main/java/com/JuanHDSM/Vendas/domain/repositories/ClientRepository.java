package com.JuanHDSM.Vendas.domain.repositories;

import com.JuanHDSM.Vendas.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "select * from tb_clients c where c.name like '%:name%'", nativeQuery = true)
    List<Client> findByName(@Param("name") String name);

    @Query(value = "delete from tb_clients c where c.name = :name", nativeQuery = true)
    void deleteByName(@Param("name") String name);

    boolean existsByName(String name);

    @Query(value = " select c from tb_clients c left join fetch c.orders where c.id = :id", nativeQuery = true)
    Client findClientFetchOrders(@Param("id") Long id);
}
