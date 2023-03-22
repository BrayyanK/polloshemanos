package com.sinensia.polloshermanos.backend.business.services.impl;

import java.util.List;

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
	public void create(Empleado empleado) {
		
		empleadoRepository.save(empleado);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Empleado read(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Empleado empleado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Empleado> findAll() {
		return empleadoRepository.findAll();
	}

	@Override
	public List<Empleado> findByNombreLikeIgnoreCase(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumeroTotalEmpleados() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumeroTotalEmpleadosActivos() {
		// TODO Auto-generated method stub
		return 0;
	}

}
