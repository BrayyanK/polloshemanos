package com.sinensia.polloshermanos.backend.business.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.services.EmpleadoServices;
import com.sinensia.polloshermanos.backend.integration.repositories.EmpleadoRepository;

@Service
@Primary
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

}
