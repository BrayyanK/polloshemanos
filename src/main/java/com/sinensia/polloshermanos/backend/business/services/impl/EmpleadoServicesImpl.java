package com.sinensia.polloshermanos.backend.business.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.services.EmpleadoServices;
import com.sinensia.polloshermanos.backend.integration.utils.FakeDatabase;

public class EmpleadoServicesImpl implements EmpleadoServices {

	private FakeDatabase fakeDatabse = FakeDatabase.getInstance();
	
	@Override
	public void create(Empleado empleado) {
		
		String dni = empleado.getDni();
		
		if (dni == null) {
			throw new IllegalStateException("No se puede crear un empleado con dni null");
		}
		
		boolean existe = fakeDatabse.getEmpleadosMap().containsKey(dni);
		
		if(existe) {
			throw new IllegalStateException("El empleado " + dni + " ya existe");
		}
		
		fakeDatabse.getEmpleadosMap().put(dni, empleado);
		
	}

	@Override
	public Empleado read(String dni) {
		return fakeDatabse.getEmpleadosMap().get(dni);
	}

	@Override
	public void update(Empleado empleado) {
		
		String dni = empleado.getDni();
		
		boolean existe = fakeDatabse.getEmpleadosMap().containsKey(dni);
		
		if(!existe) {
			throw new IllegalStateException("No existe el empleado con dni " + dni);
		}
		
	}

	@Override
	public List<Empleado> findAll() {
		return new ArrayList<>(fakeDatabse.getEmpleadosMap().values());
	}

	@Override
	public List<Empleado> findByNombreLikeIgnoreCase(String texto) {
		
		final String textoUpper = texto != null ? texto.toUpperCase() : null; 
	
		return fakeDatabse.getEmpleadosMap().values().stream()
				
				.filter(x -> {
					
					String nombre = x.getNombre() != null ? x.getNombre().toUpperCase() : "";
					String apellido1 = x.getApellido1() != null ? x.getApellido1().toUpperCase() : "";
					String apellido2 = x.getApellido2() != null ? x.getApellido2().toUpperCase() : "";	
					
					return(nombre.contains(textoUpper) || apellido1.contains(textoUpper) || apellido2.contains(textoUpper));
					
				})
				.collect(Collectors.toList());
	}
	
	@Override
	public int getNumeroTotalEmpleados() {
		return fakeDatabse.getEmpleadosMap().size();
	}
	
	@Override
	public int getNumeroTotalEmpleadosActivos() {
		
		return (int) fakeDatabse.getEmpleadosMap().values().stream()
				.filter(x -> x.isActivo())
				.count();
	}
	
}
