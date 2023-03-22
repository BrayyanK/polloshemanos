package com.sinensia.polloshermanos.backend.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.polloshermanos.backend.business.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
