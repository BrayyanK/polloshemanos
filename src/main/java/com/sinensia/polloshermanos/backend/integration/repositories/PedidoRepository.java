package com.sinensia.polloshermanos.backend.integration.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.polloshermanos.backend.business.model.EstadoPedido;
import com.sinensia.polloshermanos.backend.business.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findByEstado(EstadoPedido estado);
	
	List<Pedido> findByEmpleadoDniAndFechaHoraBetween(String dni, Date desde, Date hasta);

	//Page<Pedido> findUltimosNPendientesDeEntrega(PageRequest of);
	
}
