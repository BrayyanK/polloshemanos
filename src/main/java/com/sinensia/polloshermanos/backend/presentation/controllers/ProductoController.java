package com.sinensia.polloshermanos.backend.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.polloshermanos.backend.business.model.Producto;
import com.sinensia.polloshermanos.backend.business.services.ProductoServices;
import com.sinensia.polloshermanos.backend.presentation.config.PresentationException;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoServices productoServices;

	@GetMapping
	public List<Producto> getAll(){
		return productoServices.findAll();
	}
	
	@GetMapping("/{codigo}")
	public Producto getByCodigo(@PathVariable Long codigo) {
		
		Producto producto = productoServices.read(codigo);
		
		if(producto == null) {
			throw new PresentationException("No existe el producto con código [" + codigo + "]", HttpStatus.NOT_FOUND);
		}
		
		return producto;
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Producto producto, UriComponentsBuilder ucb){
		
		Long codigo = null;
		
		try {
			Producto createdProducto = productoServices.create(producto);
			codigo = createdProducto.getCodigo();
		} catch (IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.created(ucb.path("/productos/{codigo}").build(codigo)).build();
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Producto producto){
		
		try {
			productoServices.update(producto);
		} catch (Exception e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.accepted().build();
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
