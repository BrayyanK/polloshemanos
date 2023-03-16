package com.sinensia.polloshermanos.backend.business.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.polloshermanos.backend.business.model.EstadoPedido;
import com.sinensia.polloshermanos.backend.business.model.Pedido;
import com.sinensia.polloshermanos.backend.business.services.PedidoServices;
import com.sinensia.polloshermanos.backend.integration.utils.FakeDatabase;

@Service
public class PedidoServicesImpl implements PedidoServices{

	@Autowired
	private FakeDatabase fakeDatabse;
	
	@Override
	public Pedido create(Pedido pedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido read(Long codigo) {
		// TODO Auto-generated method stub
		return null;
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
		return new ArrayList<>(fakeDatabse.getPedidosMap().values());
	}

	@Override
	public List<Pedido> findUltimosPendientesRecogida(int ultimos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> findByEstado(EstadoPedido estado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> findByEmpleadoBetweenDates(String dni, Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstadoPedido> getEstados() {
		// TODO Auto-generated method stub
		return null;
	}

}
