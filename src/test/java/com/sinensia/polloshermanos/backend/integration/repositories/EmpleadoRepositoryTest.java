package com.sinensia.polloshermanos.backend.integration.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.sinensia.polloshermanos.backend.business.model.Empleado;

@DataJpaTest
@Sql(scripts = {"/data/h2/schema_test.sql", "/data/h2/data_test.sql"})
public class EmpleadoRepositoryTest {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Test
	void findByNombreContainingIgnoreCase() {
		
		Empleado empleado1 = new Empleado();
		Empleado empleado2 = new Empleado();
		empleado1.setDni("45899212H");
		empleado2.setDni("20098127Y");
		
		List<Empleado> empleadosEsperados = Arrays.asList(empleado1, empleado2);
		
		List<Empleado> empleados = empleadoRepository.findByNombreContainingIgnoreCase("AN");
		
		assertNotNull(empleados);
		assertEquals(2, empleados.size());
		assertTrue(empleados.containsAll(empleadosEsperados));
	
	}
	
	@Test
	void getNombresCompletosEmpleados() {
		
		String nombreCompleto1 = "CIFUENTES MERINO, CARLOTA";
		
		List<String> nombresCompletos = empleadoRepository.getNombresCompletosEmpleados();
		
		assertNotNull(nombresCompletos);
		assertEquals(4, nombresCompletos.size());
		assertTrue(nombresCompletos.contains(nombreCompleto1));
		
	}
	
}
