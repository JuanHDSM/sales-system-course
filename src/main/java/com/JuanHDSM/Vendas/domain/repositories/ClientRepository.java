package com.JuanHDSM.Vendas.domain.repositories;

import com.JuanHDSM.Vendas.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "select * from tb_client c where c.name %:nome%", nativeQuery = true)
    List<Client> findByName(@Param("name") String name);

    @Query(value = "delete from tb_client c where c.name = :name")
    void deleteByName(@Param("name") String name);

    boolean existByName(String name);

    @Query(" select c from tb_client c left join fetch c.orders where c.id = :id  ")
    Client findClientFetchOrders(@Param("id") Long id);
}
