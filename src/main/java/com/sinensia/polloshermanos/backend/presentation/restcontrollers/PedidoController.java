package com.sinensia.polloshermanos.backend.presentation.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.polloshermanos.backend.business.model.Pedido;
import com.sinensia.polloshermanos.backend.business.model.Producto;
import com.sinensia.polloshermanos.backend.business.services.PedidoServices;
import com.sinensia.polloshermanos.backend.presentation.config.PresentationException;

@RestController
public class PedidoController {

	@Autowired
	private PedidoServices pedidoServices;
	
	@GetMapping("/pedidos")
	public List<Pedido> getPedidos(){
		return pedidoServices.findAll();
	}
	
	@GetMapping("/pedidos/{codigo}")
	public Pedido getByCodigo(@PathVariable Long codigo) {

		Pedido pedido = pedidoServices.read(codigo);

		if (codigo == null) {
			throw new PresentationException("No existe el producto con código [" + codigo + "]", HttpStatus.NOT_FOUND);
		}

		return pedido;
	}
	
}
