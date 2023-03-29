package com.sinensia.polloshermanos.backend.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.model.Producto;
import com.sinensia.polloshermanos.backend.business.services.ProductoServices;

@Controller
@RequestMapping("/polloshermanos")
public class AppProductoController {

	@Autowired
	private ProductoServices productoServices;

	@GetMapping("/ficha-producto")
	public ModelAndView getFichaProducto(@RequestParam Long codigo) {

		Producto producto = productoServices.read(codigo);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("ficha-producto");
		mav.addObject("producto", producto);

		return mav;
	}
	@GetMapping("/listado-productos")
	public ModelAndView getListadoProductos() {

		List<Producto> productos = productoServices.findAll();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("listado-productos");
		mav.addObject("productos", productos);

		return mav;
	}
}