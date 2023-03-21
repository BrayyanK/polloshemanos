package com.sinensia.polloshermanos.backend.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.services.EmpleadoServices;
import com.sinensia.polloshermanos.backend.presentation.config.PresentationException;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

	@Autowired
	private EmpleadoServices empleadoServices;
	
	@GetMapping
	public List<Empleado> getEmpleados(){
		return empleadoServices.findAll();
	}
	
	@GetMapping("/{dni}")
	public Empleado getByDni(@PathVariable String dni) {
		
		Empleado empleado = empleadoServices.read(dni);
		
		if(empleado == null) {
			throw new PresentationException("No se existe el empleado con código [" + dni + "]", HttpStatus.NOT_FOUND);
		} 
		
		return empleado;
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Empleado empleado, UriComponentsBuilder ucb){
		
		try {
			empleadoServices.create(empleado);
		} catch (IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.created(ucb.path("/empleados/{dni}").build(empleado.getDni())).build();
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Empleado empleado){
		
		try {
			empleadoServices.update(empleado);
		} catch (Exception e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.accepted().build();
	}
	

}
