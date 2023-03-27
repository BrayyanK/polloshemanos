package com.sinensia.polloshermanos.backend.business.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sinensia.polloshermanos.backend.business.model.EstadoPedido;
import com.sinensia.polloshermanos.backend.business.model.Pedido;
import com.sinensia.polloshermanos.backend.business.services.PedidoServices;
import com.sinensia.polloshermanos.backend.integration.repositories.PedidoRepository;

@Service
@Primary
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
	public void update(Pedido pedido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelar(Long codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preparar(Long codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entregar(Long codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finalizar(Long codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	@Override
	public List<Pedido> findUltimosPendientesRecogida(int ultimos) {
		// TODO Auto-generated method stub
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
