package com.sinensia.polloshermanos.backend.business.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
		
		if(pedido.getCodigo() != null) {
			throw new IllegalStateException("No se puede crear un pedido que ya tienen código.");
		}
		
		Long ultimoCodigo = ((TreeMap<Long, Pedido>) fakeDatabse.getPedidosMap()).lastKey();
		
		Long codigo = ultimoCodigo + 1;
		
		pedido.setCodigo(codigo);
		
		fakeDatabse.getPedidosMap().put(pedido.getCodigo(), pedido);
		
		return pedido;
	}

	@Override
	public Pedido read(Long codigo) {
		return fakeDatabse.getPedidosMap().get(codigo);
	}

	@Override
	public void update(Pedido pedido) {
		
		Long codigo = pedido.getCodigo();
		
		if(codigo == null) {
			throw new IllegalStateException("No se puede actualizar un pedido con código null.");
		}
		
		boolean existe = fakeDatabse.getPedidosMap().containsKey(codigo);
		
		if(!existe) {
			throw new IllegalStateException("El pedido con código " + codigo + " no existe.");
		}
		
		fakeDatabse.getPedidosMap().replace(codigo, pedido);
		
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
		
		return fakeDatabse.getPedidosMap().values().stream()
				.filter(x -> x.getEstado() == estado)
				.collect(Collectors.toList());
	}

	@Override
	public List<Pedido> findByEmpleadoBetweenDates(String dni, Date desde, Date hasta) {
		
		return fakeDatabse.getPedidosMap().values().stream()
				.filter(x -> dni.equals(x.getEmpleado().getDni()))
				.filter(x -> x.getFechaHora().after(desde) && x.getFechaHora().before(hasta))
				.collect(Collectors.toList());
	}

	@Override
	public List<EstadoPedido> getEstados() {
		return Arrays.asList(EstadoPedido.values());
	}

}
