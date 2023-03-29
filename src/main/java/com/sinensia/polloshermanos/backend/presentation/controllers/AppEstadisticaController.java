package com.sinensia.polloshermanos.backend.presentation.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.sinensia.polloshermanos.backend.business.model.Producto;
import com.sinensia.polloshermanos.backend.business.services.ProductoServices;

@Controller
@RequestMapping("/polloshermanos")
public class AppEstadisticaController {

	@Autowired
	private ProductoServices productoServices;
	
	@GetMapping("/esta")
	public ModelAndView getPaginaEstadisticas() {
		
		// 1.- Calcular el número total de productos
		
		Integer numeroTotalProductos = productoServices.getNumeroTotalProductos();
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("estadisticas");  // lo va a ir a buscar a webapp/vistas/estadistica.jsp
		mav.addObject("numeTot", numeroTotalProductos);
		
		return mav;
	}
	
	// http://localhost:8080/polloshermanos/productos?codigo=873555
	
//	@GetMapping("/productos")
//	public ModelAndView getPaginaProducto(@RequestParam Long codigo) {
//		
//		Producto producto = productoServices.read(codigo);
//		
//		ModelAndView mav = new ModelAndView();
//		
//		mav.setViewName("ficha-producto");
//		mav.addObject("producto", producto);
//		
//		return mav;
//	}
	
}