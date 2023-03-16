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
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.services.EmpleadoServices;

@RestController
public class EmpleadoController {

	@Autowired
	private EmpleadoServices empleadoServices;
	
	@GetMapping("/empleados")
	public List<Empleado> getEmpleados(){
		return empleadoServices.findAll();
	}
	
	@GetMapping("/empleados/{kukuku}")
	public ResponseEntity<?> getByDni(@PathVariable("kukuku") String dni) {
		
		Empleado empleado = empleadoServices.read(dni);
		
		if(empleado == null) {
			return ResponseEntity.notFound().build();
		} 
		
		return new ResponseEntity<>(empleado, HttpStatus.OK);
	}
	
	@PostMapping("/empleados")
	public ResponseEntity<?> create(@RequestBody Empleado empleado){
		
		try {
			empleadoServices.create(empleado);
		} catch (Exception e) {
			String error = e.getMessage();
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("Se ha creado el empleado " + empleado.getDni(), HttpStatus.OK);
	}
	
	@PutMapping("/empleados")
	public ResponseEntity<?> update(@RequestBody Empleado empleado){
		
		try {
			empleadoServices.update(empleado);
		} catch (Exception e) {
			String error = e.getMessage();
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("Se ha actualizado el empleado " + empleado.getDni(), HttpStatus.OK);
	}
	

}
