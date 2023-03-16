package com.sinensia.polloshermanos.backend.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.polloshermanos.backend.business.model.Pedido;
import com.sinensia.polloshermanos.backend.business.services.PedidoServices;

@RestController
public class PedidoController {

	@Autowired
	private PedidoServices pedidoServices;
	
	@GetMapping("/pedidos")
	public List<Pedido> getPedidos(){
		return pedidoServices.findAll();
	}
	
}
