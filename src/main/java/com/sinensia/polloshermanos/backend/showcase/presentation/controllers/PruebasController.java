package com.sinensia.polloshermanos.backend.showcase.presentation.controllers;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.polloshermanos.backend.business.model.Familia;
import com.sinensia.polloshermanos.backend.business.model.Producto;
import com.sinensia.polloshermanos.backend.integration.repositories.ProductoRepository;
import com.sinensia.polloshermanos.backend.showcase.business.model1.EstacionMeteorologica;
import com.sinensia.polloshermanos.backend.showcase.business.model2.Cancion;
import com.sinensia.polloshermanos.backend.showcase.business.model3.Coche;
import com.sinensia.polloshermanos.backend.showcase.business.model3.Motor;
import com.sinensia.polloshermanos.backend.showcase.business.model4.Equipo;
import com.sinensia.polloshermanos.backend.showcase.business.model4.Jugador;
import com.sinensia.polloshermanos.backend.showcase.business.model5.Alumno;
import com.sinensia.polloshermanos.backend.showcase.business.model5.Asignatura;
import com.sinensia.polloshermanos.backend.showcase.business.model6.Almacen;
import com.sinensia.polloshermanos.backend.showcase.business.model6.Cliente;
import com.sinensia.polloshermanos.backend.showcase.business.model7.Noticia;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.AlmacenRepository;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.AlumnoRepository;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.AsignaturaRepository;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.CancionRepository;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.ClienteRepository;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.CocheRepository;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.EquipoRepository;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.EstacionMeteorologicaRepository;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.JugadorRepository;
import com.sinensia.polloshermanos.backend.showcase.integration.repositories.NoticiaRepository;

@RestController
public class PruebasController {

	@Autowired
	private NoticiaRepository noticiaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private AlmacenRepository almacenRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	private AsignaturaRepository asignaturaRepository;
	
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
	
	@GetMapping("/noticias")
	public List<Noticia> getNoticias(){
		return noticiaRepository.findAll();
	}
	
	@GetMapping("/crear50productos")
	@Transactional
	public String crear50Productos() {
		
		for(int i = 0; i < 50; i++) {
			
			Producto producto = new Producto();
			producto.setNombre("Producto_" + i);
			producto.setDescatalogado(false);
			producto.setFamilia(Familia.LICOR);
			producto.setFechaAlta(new Date());
			producto.setDescripcion("descripción... _ " + i);
			
			productoRepository.save(producto);
			
		}
		
		return "ok";
	}
	
	
	@GetMapping("/almacenes")
	public List<Almacen> getAlmacenes(){
		return almacenRepository.findAll();
	}
	
	@GetMapping("/clientes")
	public List<Cliente> getClientes(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/alumnos")
	public List<Alumno> getAlumnos(){
		return alumnoRepository.findAll();
	}
	
	@GetMapping("/asignaturas")
	public List<Asignatura> getAsignaturas(){
		return asignaturaRepository.findAll();
	}
	
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
		
		List<Coche> coches = cocheRepository.findByMotorAndPrecioBetweenOrderByPrecio(Motor.DIESEL, 1000.0, 7000.0);
		
		return coches;
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
