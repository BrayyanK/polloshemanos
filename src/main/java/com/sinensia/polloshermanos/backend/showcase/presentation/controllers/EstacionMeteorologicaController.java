package com.sinensia.polloshermanos.backend.showcase.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.polloshermanos.backend.showcase.business.model1.EstacionMeteorologica;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.EstacionMeteorologicaRepository;

@RestController
public class EstacionMeteorologicaController {

	@Autowired
	private EstacionMeteorologicaRepository estacionMeteorologicaRepository;
	
	@GetMapping("/estaciones")
	public List<EstacionMeteorologica> getAll(){
		return estacionMeteorologicaRepository.findAll();
	}
	
}
