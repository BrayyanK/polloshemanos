package com.sinensia.polloshermanos.backend.business.services;

import java.util.List;

import com.sinensia.polloshermanos.backend.business.model.Empleado;

public interface EmpleadoServices {

	/**
	 * 1.- Si el empleado ya existe se lanza una IllegalStateException con el mensaje...
	 * 
	 * "El empleado 46722324K ya existe"
	 * 
	 * 2.- Si el dni es null se lanza una IllegalStateException con el mensaje...
	 * 
	 * "No se puede crear un empleado con dni null"
	 * 
	 */
	void create(Empleado empleado);
	
	/**
	 * Si el empleado no existe devuelve null
	 *
	 */
	Empleado read(String dni);
	
	
	/**
	 * 1.- Si el empleado no existe se lanza una IllegalStateException con el mensaje...
	 * 
	 * "El empleado 46722324K no existe"
	 * 
	 * 2.- Si el dni es null se lanza una IllegalStateException con el mensaje...
	 * 
	 * "No se puede actualizar un empleado con dni null"
	 * 
	 */
	void update(Empleado empleado);
	
	List<Empleado> findAll();
	
	/**
	 * Devuelve una lista de todos los empleados que contienen el texo (ignorando mayusc/minusc) en cualquiera de los
	 * siguientes atributos:
	 * 
	 * - nombre
	 * - apellido1
	 * - apellido2
	 * 
	 * Ejemplo: Buscamos los empleados con argumento "oRi"
	 * 
	 *  - devolveria: "Honorio Martin Salvador", "ORIOL LOPEZ LOPEZ", "Anna Torino Fernandez"
	 * 
	 */
	List<Empleado> findByNombreLikeIgnoreCase(String texto);
	
	int getNumeroTotalEmpleados();
	int getNumeroTotalEmpleadosActivos();
	
}
