package com.sinensia.polloshermanos.backend.showcase.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.polloshermanos.backend.showcase.business.model1.EstacionMeteorologica;
import com.sinensia.polloshermanos.backend.showcase.business.model2.Cancion;
import com.sinensia.polloshermanos.backend.showcase.business.model3.Coche;
import com.sinensia.polloshermanos.backend.showcase.business.model4.Equipo;
import com.sinensia.polloshermanos.backend.showcase.business.model4.Jugador;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.CancionRepository;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.CocheRepository;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.EquipoRepository;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.EstacionMeteorologicaRepository;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.JugadorRepository;

@RestController
public class PruebasController {

	@Autowired
	private CocheRepository cocheRepository;
	
	@Autowired
	private EstacionMeteorologicaRepository estacionMeteorologicaRepository;
	
	@Autowired
	private CancionRepository cancionRepository;
	
	@Autowired
	private JugadorRepository jugadorRepository;
	
	@Autowired
	private EquipoRepository equipoRepository;
	
	// ***********************************************
	
	@GetMapping("/equipos")
	public List<Equipo> getEquipos(){
		return equipoRepository.findAll();
	}
	
	@GetMapping("/jugadores")
	public List<Jugador> getJugadores(){
		return jugadorRepository.findAll();
	}
	
	@GetMapping("/coches")
	public List<Coche> getCoches(){
		return cocheRepository.findAll();
	}
	
	@GetMapping("/estaciones")
	public List<EstacionMeteorologica> getEstaciones(){
		return estacionMeteorologicaRepository.findAll();
	}
	
	@GetMapping("/canciones")
	public List<Cancion> getCanciones(){
		return cancionRepository.findAll();
	}
}
