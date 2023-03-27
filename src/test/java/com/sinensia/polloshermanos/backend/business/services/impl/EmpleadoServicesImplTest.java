package com.sinensia.polloshermanos.backend.business.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.model.dtos.EmpleadoDTO1;
import com.sinensia.polloshermanos.backend.business.model.dtos.EmpleadoDTO2;
import com.sinensia.polloshermanos.backend.integration.repositories.EmpleadoRepository;

@ExtendWith(MockitoExtension.class)
class EmpleadoServicesImplTest {

	@Mock
	private EmpleadoRepository empleadoRepository;
	
	@InjectMocks
	private EmpleadoServicesImpl empleadoServices;
	
	@BeforeEach
	void setUp() throws Exception {
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
		
		Empleado e1 = new Empleado();
		e1.setDni("37667523F");
		
		when(empleadoRepository.existsById("37667523F")).thenReturn(true);
		
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
		
		when(empleadoRepository.existsById("55555555R")).thenReturn(false);
		
		empleadoServices.create(empleado);
		
		verify(empleadoRepository, times(1)).save(empleado);
	}

	
	@Test
	void read_empleado_existente() {
		
		Empleado empleado = new Empleado();
		empleado.setDni("11111111R");
		empleado.setNombre("Nombre Empleado");
		
		when(empleadoRepository.findById("11111111R")).thenReturn(Optional.of(empleado));
		
		empleado = null;
		
		empleado = empleadoServices.read("11111111R");
		
		assertNotNull(empleado);
		assertEquals("Nombre Empleado", empleado.getNombre());
		
	}
	
	@Test
	void read_empleado_no_existente() {
		
		when(empleadoRepository.findById("11111111R")).thenReturn(Optional.empty());
	
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
		empleado.setDni("11111111R");
		
		when(empleadoRepository.existsById("11111111R")).thenReturn(false);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			empleadoServices.update(empleado);
		});
		
		String message = exception.getMessage();
		
		assertEquals("El empleado 11111111R no existe", message);
		
	}
	
	@Test
	void actualizamos_empleado_ok() {
		
		Empleado empleado = new Empleado();
		empleado.setDni("11111111R");
		
		when(empleadoRepository.existsById("11111111R")).thenReturn(true);
		
		empleadoServices.update(empleado);
		
		verify(empleadoRepository, times(1)).save(empleado);
	}

	@Test
	void testFindAll() {
		
		Empleado empleado1 = new Empleado();
		Empleado empleado2 = new Empleado();
		empleado1.setDni("11111111R");
		empleado2.setDni("22222222T");
		
		when(empleadoRepository.findAll()).thenReturn(Arrays.asList(empleado1, empleado2));
		
		List<Empleado> empleados = empleadoServices.findAll();
		
		assertNotNull(empleados);
		assertEquals(2, empleados.size());
		
	}

	@Test
	void testFindByNombreLikeIgnoreCase() {
		
		Empleado empleado1 = new Empleado();
		Empleado empleado2 = new Empleado();
		empleado1.setDni("11111111R");
		empleado2.setDni("22222222T");
		
		when(empleadoRepository.findByNombreContainingIgnoreCase("Fo")).thenReturn(Arrays.asList(empleado1, empleado2));
		
		List<Empleado> empleados = empleadoServices.findByNombreLikeIgnoreCase("Fo");
		
		assertNotNull(empleados);
		assertEquals(2, empleados.size());
	}

	@Test
	void testGetNumeroTotalEmpleados() {
		
		when(empleadoRepository.count()).thenReturn(26895L);
		
		int numeroTotalEmpleados = empleadoServices.getNumeroTotalEmpleados();
		
		assertEquals(26895, numeroTotalEmpleados);
	}

	@Test
	void testGetNumeroTotalEmpleadosActivos() {
		
		when(empleadoRepository.getNumeroTotalEmpleados(true)).thenReturn(567L);
		
		int numeroTotalEmpleadosActivos = empleadoServices.getNumeroTotalEmpleadosActivos();
		
		assertEquals(567, numeroTotalEmpleadosActivos);
		
	}

	@Test
	void testGetEmpleadosDTO1() {
		
		List<String> nombresEmpleados = new ArrayList<>();
		
		nombresEmpleados.add("MARTÍN SALVADOR, HONORIO");
		nombresEmpleados.add("CIFUENTES MERINO, CARLOTA");
		nombresEmpleados.add("GÁLVEZ RIDRUEJO, PEPÍN");
		
		List<EmpleadoDTO1> empleadosDTO1Esperados = new ArrayList<>();
		
		EmpleadoDTO1 empleadoDTO11 = new EmpleadoDTO1();
		EmpleadoDTO1 empleadoDTO12 = new EmpleadoDTO1();
		EmpleadoDTO1 empleadoDTO13 = new EmpleadoDTO1();
		
		empleadoDTO11.setNombreCompleto("MARTÍN SALVADOR, HONORIO");
		empleadoDTO12.setNombreCompleto("CIFUENTES MERINO, CARLOTA");
		empleadoDTO13.setNombreCompleto("GÁLVEZ RIDRUEJO, PEPÍN");
		
		empleadosDTO1Esperados.add(empleadoDTO11);
		empleadosDTO1Esperados.add(empleadoDTO12);
		empleadosDTO1Esperados.add(empleadoDTO13);
		
		when(empleadoRepository.getNombresCompletosEmpleados()).thenReturn(nombresEmpleados);
		
		List<EmpleadoDTO1> empleadosDTO1 = empleadoServices.getEmpleadosDTO1();
		
		assertNotNull(empleadosDTO1);
		assertEquals(3, empleadosDTO1.size());
		assertTrue(empleadosDTO1.containsAll(empleadosDTO1Esperados));
	}

	@Test
	void testGetEmpleadosDTO2() {
		
		EmpleadoDTO2 empleadoDTO21 = new EmpleadoDTO2("11111111R","Apellido1 1","Apellido2 1","Nombre 1");
		EmpleadoDTO2 empleadoDTO22 = new EmpleadoDTO2("22222222T","Apellido1 2","Apellido2 2","Nombre 2");
		
		List<EmpleadoDTO2> empleadosDTO2Esperados = Arrays.asList(empleadoDTO21, empleadoDTO22);
		
		when(empleadoRepository.getEmpleadosDTO2()).thenReturn(Arrays.asList(empleadoDTO21, empleadoDTO22));
		
		List<EmpleadoDTO2> empleadosDTO2 = empleadoServices.getEmpleadosDTO2();
		
		assertNotNull(empleadosDTO2);
		assertEquals(2, empleadosDTO2.size());
		assertTrue(empleadosDTO2.containsAll(empleadosDTO2Esperados));
		
	}

	@Test
	void testGetPage() {
		
		Empleado empleado1 = new Empleado();
		Empleado empleado2 = new Empleado();
		empleado1.setDni("11111111R");
		empleado2.setDni("22222222T");
		List<Empleado> empleados = Arrays.asList(empleado1, empleado2);
		
		when(empleadoRepository.findPage(PageRequest.of(0, 2))).thenReturn(new PageImpl<>(empleados));
		
		Page<Empleado> pagina = empleadoServices.getPage(0, 2);
		
		assertNotNull(pagina);
		assertTrue(pagina.getContent().containsAll(empleados));
		
	}

}
