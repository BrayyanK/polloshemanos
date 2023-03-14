package com.sinensia.polloshermanos.backend.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.services.EmpleadoServices;
import com.sinensia.polloshermanos.backend.integration.utils.FakeDatabase;

public class EmpleadoServicesStreamsImpl implements EmpleadoServices {

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
		
		List<Empleado> empleados = new ArrayList<>();
		
		texto = texto != null ? texto.toUpperCase() : null; 
		
		for(Empleado empleado : fakeDatabse.getEmpleadosMap().values()) {
			
			String nombre = empleado.getNombre() != null ? empleado.getNombre().toUpperCase() : "";
			String apellido1 = empleado.getApellido1() != null ? empleado.getApellido1().toUpperCase() : "";
			String apellido2 = empleado.getApellido2() != null ? empleado.getApellido2().toUpperCase() : "";
			
			if(nombre.contains(texto) || apellido1.contains(texto) || apellido2.contains(texto)) {
				empleados.add(empleado);
			}
		}
		
		return empleados;
	}

	@Override
	public int getNumeroTotalEmpleados() {
		return fakeDatabse.getEmpleadosMap().size();
	}

	@Override
	public int getNumeroTotalEmpleadosActivos() {
		
		int numeroTotalEmpleadosActivos = 0;
		
		for (Empleado empleado: fakeDatabse.getEmpleadosMap().values()) {
			if(empleado.isActivo()) {
				numeroTotalEmpleadosActivos++;
			}
		}
		
		return numeroTotalEmpleadosActivos;
	}
	
}
