package com.sinensia.polloshermanos.backend.business.services;

import java.util.Date;
import java.util.List;

import com.sinensia.polloshermanos.backend.business.model.EstadoPedido;
import com.sinensia.polloshermanos.backend.business.model.Pedido;

public interface PedidoServices {

	Pedido create(Pedido pedido);
	Pedido read(Long codigo);
	void update(Pedido pedido);
	
	void cancelar(Long codigo);
	void preparar(Long codigo);
	void entregar(Long codigo);
	void finalizar(Long codigo);
	
	List<Pedido> findAll();
	List<Pedido> findUltimosPendientesRecogida(int ultimos);
	List<Pedido> findByEstado(EstadoPedido estado);
	List<Pedido> findByEmpleadoBetweenDates(String dni, Date desde, Date hasta);
	
	List<EstadoPedido> getEstados();
}
