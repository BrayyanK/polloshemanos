package com.sinensia.polloshermanos.backend.business.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.model.dtos.EmpleadoDTO1;
import com.sinensia.polloshermanos.backend.business.model.dtos.EmpleadoDTO2;
import com.sinensia.polloshermanos.backend.business.services.EmpleadoServices;
import com.sinensia.polloshermanos.backend.integration.repositories.EmpleadoRepository;

@Service
public class EmpleadoServicesImpl implements EmpleadoServices{

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Override
	@Transactional
	public void create(Empleado empleado) {
		
		if(empleado.getDni() == null) {
			throw new IllegalStateException("No se puede crear un empleado con dni null");
		}
		
		boolean existe = empleadoRepository.existsById(empleado.getDni());
		
		if(existe) {
			throw new IllegalStateException("El empleado " + empleado.getDni() + " ya existe");
		}
		
		empleadoRepository.save(empleado);
		
	}

	@Override
	public Empleado read(String dni) {
		return empleadoRepository.findById(dni).orElse(null);
	}

	@Override
	@Transactional
	public void update(Empleado empleado) {
		
		if(empleado.getDni() == null) {
			throw new IllegalStateException("No se puede actualizar un empleado con dni null");
		}
		
		boolean existe = empleadoRepository.existsById(empleado.getDni());
		
		if(!existe) {
			throw new IllegalStateException("El empleado " + empleado.getDni() + " no existe");
		}
		
		empleadoRepository.save(empleado);
		
	}

	@Override
	public List<Empleado> findAll() {
		return empleadoRepository.findAll();
	}

	@Override
	public List<Empleado> findByNombreLikeIgnoreCase(String texto) {
		return empleadoRepository.findByNombreContainingIgnoreCase(texto);
	}

	@Override
	public int getNumeroTotalEmpleados() {
		return (int) empleadoRepository.count();
	}

	@Override
	public int getNumeroTotalEmpleadosActivos() {
		return (int) empleadoRepository.getNumeroTotalEmpleados(true);
	}
	
	@Override
	public List<EmpleadoDTO1> getEmpleadosDTO1() {
		
		return empleadoRepository.getNombresCompletosEmpleados().stream()
				.map(x -> {
					EmpleadoDTO1 empleadoDTO1 = new EmpleadoDTO1();
					empleadoDTO1.setNombreCompleto(x);
					return empleadoDTO1;
				  })
				.collect(Collectors.toList());
		
	}
	
	@Override
	public List<EmpleadoDTO2> getEmpleadosDTO2() {
		return empleadoRepository.getEmpleadosDTO2();
	}

	@Override
	public Page<Empleado> getPage(int numeroPagina, int numeroElementos) {
		return empleadoRepository.findPage(PageRequest.of(numeroPagina, numeroElementos));
	}
}
