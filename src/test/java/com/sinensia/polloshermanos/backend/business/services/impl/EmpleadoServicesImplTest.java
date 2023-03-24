package com.sinensia.polloshermanos.backend.business.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.services.EmpleadoServices;

@SpringBootTest
@Sql(scripts = {"/data/h2/schema_test.sql", "/data/h2/data_test.sql"})
class EmpleadoServicesImplTest {

	@Autowired
	private EmpleadoServices empleadoServices;
	
	private Empleado e1;
	private Empleado e2;
	private Empleado e3;
	private Empleado e4;
	
	@BeforeEach
	void beforeEach() {
		init();
	}

	@Test
	void creamos_empleado_con_dni_null() {
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			empleadoServices.create(new Empleado());
		});
		
		String message = exception.getMessage();
		
		assertEquals("No se puede crear un empleado con dni null", message);
		
	}
	
	@Test
	void creamos_empleado_con_dni_existente() {
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			empleadoServices.create(e1);
		});
		
		String message = exception.getMessage();
		
		assertEquals("El empleado 37667523F ya existe", message);
	}
	
	@Test
	void creamos_empleado_ok() {
		
		Empleado empleado = new Empleado();
		empleado.setDni("55555555R");
		empleado.setNombre("Nombre Nuevo");
		empleado.setApellido1("Apellido1 Nuevo");
		empleado.setApellido2("Apellido2 Nuevo");
		empleado.setActivo(true);
		
		empleadoServices.create(empleado);
		
		int numeroEmpleados = empleadoServices.getNumeroTotalEmpleados();
		
		assertEquals(5, numeroEmpleados);
		
		empleado = null;
		
		empleado = empleadoServices.read("55555555R");
		
		assertNotNull(empleado);
		assertEquals("55555555R", empleado.getDni());
		assertEquals("Nombre Nuevo", empleado.getNombre());
		assertEquals("Apellido1 Nuevo", empleado.getApellido1());
		assertEquals("Apellido2 Nuevo", empleado.getApellido2());
		assertTrue(empleado.isActivo());
		
	}

	@Test
	void read_empleado_existente() {
		
		Empleado empleado = empleadoServices.read("45899212H");
		
		assertNotNull(empleado);
		assertEquals("45899212H", empleado.getDni());
		assertEquals("Anna", empleado.getNombre());
		assertEquals("Roca", empleado.getApellido1());
		assertEquals("Arderiu", empleado.getApellido2());
		assertTrue(empleado.isActivo());
	}
	
	@Test
	void read_empleado_no_existente() {
		
		Empleado empleado = empleadoServices.read("11111111R");
		
		assertNull(empleado);
		
	}

	@Test
	void actualizamos_empleado_con_dni_null() {
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			empleadoServices.update(new Empleado());
		});
		
		String message = exception.getMessage();
		
		assertEquals("No se puede actualizar un empleado con dni null", message);
	}
	
	@Test
	void actualizamos_empleado_con_dni_no_existente() {
		
		Empleado empleado = new Empleado();
		empleado.setDni("55555555R");
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			empleadoServices.update(empleado);
		});
		
		String message = exception.getMessage();
		
		assertEquals("El empleado 55555555R no existe", message);
	}
	
	@Test
	void actualizamos_empleado_ok() {
		
		e1.setNombre("NOMBRE ACTUALIZADO");
		e1.setApellido1("APELLIDO1 ACTUALIZADO");
		e1.setApellido2("APELLIDO2 ACTUALIZADO");
		e1.setActivo(false);
		
		empleadoServices.update(e1);
		
		e1 = null;
		
		e1 = empleadoServices.read("37667523F");
		
		assertNotNull(e1);
		assertEquals("NOMBRE ACTUALIZADO", e1.getNombre());
		assertEquals("APELLIDO1 ACTUALIZADO", e1.getApellido1());
		assertEquals("APELLIDO2 ACTUALIZADO", e1.getApellido2());
		assertFalse(e1.isActivo());
		
	}
	
	
	

	@Test
	void testFindAll() {
		
		List<Empleado> empleadosEsperados = Arrays.asList(e1, e2, e3, e4);
		
		List<Empleado> empleados = empleadoServices.findAll();
		
		assertNotNull(empleados);
		assertEquals(4, empleados.size());
		assertTrue(empleados.containsAll(empleadosEsperados));
		
	}

	@Test
	void testFindByNombreLikeIgnoreCase() {
		
		List<Empleado> empleadosEsperados = Arrays.asList(e2, e3);
		
		List<Empleado> empleados = empleadoServices.findByNombreLikeIgnoreCase("an");
		
		assertNotNull(empleados);
		assertEquals(2, empleados.size());
		assertTrue(empleados.containsAll(empleadosEsperados));
		
	}

	@Test
	void testGetNumeroTotalEmpleados() {
		
		int numeroEmpleadosActivos = empleadoServices.getNumeroTotalEmpleados();
		
		assertEquals(4, numeroEmpleadosActivos);
	}

	@Test
	void testGetNumeroTotalEmpleadosActivos() {
		
		int numeroEmpleadosActivos = empleadoServices.getNumeroTotalEmpleadosActivos();
		
		assertEquals(3, numeroEmpleadosActivos);
		
	}
	
	// *************************************************************************
	//
	// PRIVATE METHODS
	//
	// *************************************************************************

	private void init() {
		
		e1 = new Empleado();
		e2 = new Empleado();
		e3 = new Empleado();
		e4 = new Empleado();
		
		e1.setDni("37667523F");
		e2.setDni("45899212H");
		e3.setDni("20098127Y");
		e4.setDni("19822376G");

	}
}
