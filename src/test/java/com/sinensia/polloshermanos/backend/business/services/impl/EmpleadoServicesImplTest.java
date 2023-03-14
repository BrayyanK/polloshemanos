package com.sinensia.polloshermanos.backend.business.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.services.EmpleadoServices;
import com.sinensia.polloshermanos.backend.integration.utils.FakeDatabase;

class EmpleadoServicesImplTest {

	private EmpleadoServices empleadoServices;
	
	@BeforeEach
	void setUp() throws Exception {
		empleadoServices = new EmpleadoServicesStreamsImpl();
		FakeDatabase.getInstance().init();
	}

	@Test
	void crear() {
		
		Empleado nuevoEmpleado = new Empleado();
		nuevoEmpleado.setDni("22222222R");
		nuevoEmpleado.setNombre("Nuevo Nombre");
		nuevoEmpleado.setApellido1("Nuevo Apellido1");
		nuevoEmpleado.setApellido2("Nuevo Apellido2");
		nuevoEmpleado.setActivo(true);
		
		empleadoServices.create(nuevoEmpleado);
		
		nuevoEmpleado = null;
		
		nuevoEmpleado = empleadoServices.read("22222222R");
		
		assertNotNull(nuevoEmpleado);
		assertEquals("22222222R", nuevoEmpleado.getDni());
		assertEquals("Nuevo Nombre", nuevoEmpleado.getNombre());
		assertEquals("Nuevo Apellido1", nuevoEmpleado.getApellido1());
		assertEquals("Nuevo Apellido2", nuevoEmpleado.getApellido2());
		assertTrue(nuevoEmpleado.isActivo());
		
	}
	
	@Test
	void crear_con_dni_null() {
		
		Empleado nuevoEmpleado = new Empleado();
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			empleadoServices.create(nuevoEmpleado);
		});
		
		String mensaje = exception.getMessage();
		
		assertEquals("No se puede crear un empleado con dni null", mensaje);
	}
	
	@Test
	void crear_con_dni_existente() {
		
		Empleado nuevoEmpleado = new Empleado();
		nuevoEmpleado.setDni("46722342L");
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			empleadoServices.create(nuevoEmpleado);
		});
		
		String mensaje = exception.getMessage();
		
		assertEquals("El empleado 46722342L ya existe", mensaje);
	}

	@Test
	void read_empleado_existente() {
		
		Empleado empleado = empleadoServices.read("46722342L");
		
		assertNotNull(empleado);
		assertEquals("46722342L", empleado.getDni());
		assertEquals("Pepín", empleado.getNombre());
		assertEquals("Gálvez", empleado.getApellido1());
		assertEquals("Ridruejo", empleado.getApellido2());
		assertTrue(empleado.isActivo());
		
	}
	
	@Test
	void read_empleado_no_existente() {
		
		Empleado empleado = empleadoServices.read("11111111P");
		
		assertNull(empleado);
			
	}

	@Test
	@Disabled
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		
		Empleado empleado1 = new Empleado();
		Empleado empleado2 = new Empleado();
		Empleado empleado3 = new Empleado();
		
		empleado1.setDni("46722342L");
		empleado2.setDni("33699251K");
		empleado3.setDni("25040991R");
		
		List<Empleado> empleadosEsperados = Arrays.asList(empleado1, empleado2, empleado3);
		
		List<Empleado> empleados = empleadoServices.findAll();
		
		assertNotNull(empleados);
		assertEquals(3, empleados.size());
		assertTrue(empleados.containsAll(empleadosEsperados));
		
	}

	@Test
	@Disabled
	void testFindByNombreLikeIgnoreCase() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNumeroTotalEmpleados() {
		
		int numeroTotalEmpleados = empleadoServices.getNumeroTotalEmpleados();
		
		assertEquals(3, numeroTotalEmpleados);
	}

	@Test
	void testGetNumeroTotalEmpleadosActivos() {
		
		int numeroTotalEmpleadosActivos = empleadoServices.getNumeroTotalEmpleadosActivos();
		
		assertEquals(2, numeroTotalEmpleadosActivos);
	}

}
