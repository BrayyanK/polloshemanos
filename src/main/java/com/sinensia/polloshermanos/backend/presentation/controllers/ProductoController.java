package com.sinensia.polloshermanos.backend.presentation.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private  Logger logger = LoggerFactory.getLogger(ProductoController.class);
	
	@GetMapping("/productos")
	public List<Producto> getAll(){
		
		logger.info("Me piden todos los productos");

		return productoServices.findAll();
	}
	
	@DeleteMapping("/productos/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long codigo) {
		
		try {
			//productoServices.delete(codigo);
			// TODO Eliminar consistirá en actualizar propiedad descatalogado a TRUE
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	

}
