package com.sinensia.polloshermanos.backend.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.model.Pedido;

import com.sinensia.polloshermanos.backend.business.services.PedidoServices;

@Controller
@RequestMapping("/polloshermanos")
public class AppPedidoController {

	@Autowired
	private PedidoServices pedidoServices;

	@GetMapping("/ficha-pedido")
	public ModelAndView getFichaPedido(@RequestParam("codigo") Long codigo) {

		Pedido pedido = pedidoServices.read(codigo);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("ficha-pedido");
		mav.addObject("pedido", pedido);

		return mav;
	}

	@GetMapping("/pedidos")
	public ModelAndView getListadoPedidos() {

		List<Pedido> pedidos = pedidoServices.findAll();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("listado-pedidos");
		mav.addObject("pedidos", pedidos);

		return mav;
	}
	
	

}