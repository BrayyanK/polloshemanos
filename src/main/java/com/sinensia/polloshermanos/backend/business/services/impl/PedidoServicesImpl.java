package com.sinensia.polloshermanos.backend.business.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.sinensia.polloshermanos.backend.business.model.EstadoPedido;
import com.sinensia.polloshermanos.backend.business.model.Pedido;
import com.sinensia.polloshermanos.backend.business.services.PedidoServices;
import com.sinensia.polloshermanos.backend.integration.repositories.PedidoRepository;
@Service
public class PedidoServicesImpl implements PedidoServices{
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	@Transactional
	public Pedido create(Pedido pedido) {
		
		if(pedido.getCodigo() != null) {
			throw new IllegalStateException("Para crear un pedido el código ha de ser null");
		}
	
		return pedidoRepository.save(pedido);
		
	}
	@Override
	public Pedido read(Long codigo) {
		return pedidoRepository.findById(codigo).orElse(null);
	}

	@Override
	@Transactional
	public void update(Pedido pedido) {

		boolean existe = pedidoRepository.existsById(pedido.getCodigo());
		
		if(!existe) {
			throw new IllegalStateException("No existe el pedido con código " + pedido.getCodigo());
		}
		
		pedidoRepository.save(pedido);
		
	}
	@Override
	@Transactional
	public void cancelar(Long codigo) {
		
		boolean existe = pedidoRepository.existsById(codigo);
		
		if(!existe) {
			throw new IllegalStateException("El pedido [" + codigo + "] no existe.");
		}
		
		Pedido pedido = read(codigo);
		
		EstadoPedido estadoActual = pedido.getEstado();
		
		if(estadoActual == EstadoPedido.CANCELADO || estadoActual == EstadoPedido.ENTREGADO) {
			throw new IllegalStateException("No se puede pasar de estado [" + estadoActual + "] a [" + EstadoPedido.CANCELADO + "]");
		}
		
		pedido.setEstado(EstadoPedido.CANCELADO);
		
	}

	@Override
	@Transactional
	public void preparar(Long codigo) {

		boolean existe = pedidoRepository.existsById(codigo);
		
		if(!existe) {
			throw new IllegalStateException("El pedido [" + codigo + "] no existe.");
		}
		
		Pedido pedido = read(codigo);
		
		EstadoPedido estadoActual = pedido.getEstado();
		
		if(estadoActual != EstadoPedido.NUEVO) {
			throw new IllegalStateException("No se puede pasar de estado [" + estadoActual + "] a [" + EstadoPedido.EN_PROCESO + "]");
		}
		
		pedido.setEstado(EstadoPedido.EN_PROCESO);
		
	}

	@Override
	@Transactional
	public void entregar(Long codigo) {

		boolean existe = pedidoRepository.existsById(codigo);
		
		if(!existe) {
			throw new IllegalStateException("El pedido [" + codigo + "] no existe.");
		}
		
		Pedido pedido = read(codigo);
		
		EstadoPedido estadoActual = pedido.getEstado();
		
		if(estadoActual != EstadoPedido.PENDIENTE_ENTEGA) {
			throw new IllegalStateException("No se puede pasar de estado [" + estadoActual + "] a [" + EstadoPedido.ENTREGADO + "]");
		}
		
		pedido.setEstado(EstadoPedido.ENTREGADO);
		
	}

	@Override
	@Transactional
	public void finalizar(Long codigo) {

		boolean existe = pedidoRepository.existsById(codigo);
		
		if(!existe) {
			throw new IllegalStateException("El pedido [" + codigo + "] no existe.");
		}
		
		Pedido pedido = read(codigo);
		
		EstadoPedido estadoActual = pedido.getEstado();
		
		if(estadoActual != EstadoPedido.EN_PROCESO) {
			throw new IllegalStateException("No se puede pasar de estado [" + estadoActual + "] a [" + EstadoPedido.PENDIENTE_ENTEGA + "]");
		}
		
		pedido.setEstado(EstadoPedido.PENDIENTE_ENTEGA);
		
	}
	@Override
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	@Override
	public List<Pedido> findUltimosPendientesRecogida(int ultimos) {
		
	//	Page<Pedido> page = pedidoRepository.findUltimosNPendientesDeEntrega(PageRequest.of(0, ultimos));
		
	//	return page.getContent();
		return null;
	}
	@Override
	public List<Pedido> findByEstado(EstadoPedido estado) {
		return pedidoRepository.findByEstado(estado);
	}
	@Override
	public List<Pedido> findByEmpleadoBetweenDates(String dni, Date desde, Date hasta) {
		return pedidoRepository.findByEmpleadoDniAndFechaHoraBetween(dni, desde, hasta);
	}
	@Override
	public List<EstadoPedido> getEstados() {
		return Arrays.asList(EstadoPedido.values());
	}
}
