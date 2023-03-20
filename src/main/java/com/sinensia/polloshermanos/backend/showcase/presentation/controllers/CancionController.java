package com.sinensia.polloshermanos.backend.showcase.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.polloshermanos.backend.showcase.business.model2.Cancion;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.CancionRepository;

@RestController
public class CancionController {

	@Autowired
	private CancionRepository cancionRepository;
	
	@GetMapping("/canciones")
	public List<Cancion> getAll(){
		return cancionRepository.findAll();
	}
	
}
