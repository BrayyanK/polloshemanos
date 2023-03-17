package com.sinensia.polloshermanos.backend.presentation.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.polloshermanos.backend.business.model.Producto;
import com.sinensia.polloshermanos.backend.business.services.ProductoServices;
import com.sinensia.polloshermanos.backend.presentation.config.PresentationException;

@RestController
public class ProductoController {

	@Autowired
	private ProductoServices productoServices;
	
	// http://10.250.0.3:8080/productos
	
	@GetMapping("/productos")
	public List<Producto> getAll(HttpServletRequest request){
		
		String ip = request.getRemoteAddr();
		
		// TODO
		
		// 1.- Instanciar un objeto de la clase Peticion
		// 2.- Asignarle el timestamp (Long) junto con la IP
		//
		// Peticion peticion = new Peticion(System.currentTimeMillis(), request.getRemoteAddr());
		//
		// peticionServices.create(peticion);
		
		
		System.out.println(ip);
		
		return productoServices.findAll();
	}
	
	@DeleteMapping("/productos/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long codigo) {
		
		try {
			productoServices.delete(codigo);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	

}
