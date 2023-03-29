package com.sinensia.polloshermanos.backend.presentation.controllers;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.model.dtos.EmpleadoDTO1;
import com.sinensia.polloshermanos.backend.business.model.dtos.EmpleadoDTO2;
import com.sinensia.polloshermanos.backend.business.services.EmpleadoServices;
import com.sinensia.polloshermanos.backend.presentation.config.RespuestaError;
import com.sinensia.polloshermanos.backend.presentation.restcontrollers.EmpleadoController;


@WebMvcTest(controllers = EmpleadoController.class)
class EmpleadoControllerTest {

	@Autowired
	private MockMvc miniPostman;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private EmpleadoServices empleadoServices;

	private Empleado empleado1;
	private Empleado empleado2;
	private Empleado empleado3;

	@BeforeEach
	void setUp() throws Exception {
		initObjects();
	}

	@Test
	void pedimos_todos_los_empleados() throws Exception {

		List<Empleado> empleados = Arrays.asList(empleado1, empleado2, empleado3);

		when(empleadoServices.findAll()).thenReturn(empleados);

		MvcResult mvcResult =  miniPostman.perform(get("/empleados").contentType("application/json"))
			.andExpect(status().isOk()).andReturn();

		String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

		assertThat(responseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(empleados));

	}

	@Test
	void pedimos_empleado_existente() throws Exception {

		when(empleadoServices.read("11111111R")).thenReturn(empleado1);

		MvcResult mvcResult =  miniPostman.perform(get("/empleados/11111111R").contentType("application/json"))
				.andExpect(status().isOk()).andReturn();

		String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

		assertThat(responseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(empleado1));
	}

	@Test
	void creamos_empleado_ok() throws Exception{

		String strEmpleado1 = objectMapper.writeValueAsString(empleado1);

		miniPostman.perform(post("/empleados").contentType("application/json").content(strEmpleado1))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location","http://localhost/empleados/11111111R"));

		verify(empleadoServices, times(1)).create(empleado1);
	}

	@Test
	void creamos_empleado_sin_dni_devuelve_BAD_REQUEST() throws Exception{

		empleado1.setDni(null);

		doThrow(new IllegalStateException("No se puede crear un empleado con dni null")).when(empleadoServices).create(empleado1);

		String strEmpleado1 = objectMapper.writeValueAsString(empleado1);

		MvcResult mvcResult =  miniPostman.perform(post("/empleados").contentType("application/json").content(strEmpleado1))
				.andExpect(status().isBadRequest()).andReturn();

		String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

		RespuestaError respuestaError = new RespuestaError("No se puede crear un empleado con dni null");

		assertThat(responseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(respuestaError));


		verify(empleadoServices, times(1)).create(empleado1);
	}

	@Test
	void creamos_empleado_ya_existente_devuelve_BAD_REQUEST() throws Exception{

		doThrow(new IllegalStateException("El empleado 11111111R ya existe")).when(empleadoServices).create(empleado1);

		String strEmpleado1 = objectMapper.writeValueAsString(empleado1);

		MvcResult mvcResult =  miniPostman.perform(post("/empleados").contentType("application/json").content(strEmpleado1))
				                 			.andExpect(status().isBadRequest())
				                 			.andReturn();

		String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

		RespuestaError respuestaError = new RespuestaError("El empleado 11111111R ya existe");

		assertThat(responseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(respuestaError));

		verify(empleadoServices, times(1)).create(empleado1);
	}

	@Test
	void testUpdate() throws Exception {
		
		doThrow(new IllegalStateException("No se puede actualizar un empleado con DNI null")).when(empleadoServices).update(empleado1);
		
		empleado1.setDni(null);
		String strEmpleado1 = objectMapper.writeValueAsString(empleado1);

		MvcResult mvcResult =  miniPostman.perform(put("/empleados").contentType("application/json").content(strEmpleado1))
				                 			.andExpect(status().isBadRequest())
				                 			.andReturn();

		String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

		RespuestaError respuestaError = new RespuestaError("No se puede actualizar un empleado con DNI null");

		assertThat(responseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(respuestaError));

		verify(empleadoServices, times(1)).update(empleado1);
		
	}
	@Test
	void testUpdateNoExistente() throws Exception {
		empleado2.setDni("123123L");
		
		doThrow(new IllegalStateException("No se puede actualizar un empleado inexistente")).when(empleadoServices).update(empleado1);
		
		empleado1.setDni(null);
		String strEmpleado1 = objectMapper.writeValueAsString(empleado1);

		MvcResult mvcResult =  miniPostman.perform(put("/empleados").contentType("application/json").content(strEmpleado1))
				                 			.andExpect(status().isBadRequest())
				                 			.andReturn();

		String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

		RespuestaError respuestaError = new RespuestaError("No se puede actualizar un empleado inexistente");

		assertThat(responseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(respuestaError));

		verify(empleadoServices, times(1)).update(empleado1);
		
	}
	@Test
	void editamos_empleado_ok() throws Exception{

		
		String strEmpleado1 = objectMapper.writeValueAsString(empleado1);

		miniPostman.perform(put("/empleados").contentType("application/json").content(strEmpleado1))
				.andExpect(status().isAccepted());
				//.andExpect(header().string("Location","http://localhost/empleados/1111111R"));

		verify(empleadoServices, times(1)).update(empleado1);
	}
	
	@Test
	void testGetEmpleadosDTO1() throws Exception {
		
		EmpleadoDTO1 em1 = new EmpleadoDTO1();
		EmpleadoDTO1 em2 = new EmpleadoDTO1();
		EmpleadoDTO1 em3 = new EmpleadoDTO1();
		
		List<EmpleadoDTO1> empleadosDto1ps = Arrays.asList(em1,em2,em3);
		
		
		when(empleadoServices.getEmpleadosDTO1()).thenReturn((empleadosDto1ps));

		MvcResult mvcResult =  miniPostman.perform(get("/empleados/dto1").contentType("application/json"))
				.andExpect(status().isOk()).andReturn();

		String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

		assertThat(responseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(empleadosDto1ps));
		
	}

	@Test
	void testGetEmpleadosDTO2() throws Exception {


		EmpleadoDTO2 em1 = new EmpleadoDTO2("","","","");
		EmpleadoDTO2 em2 = new EmpleadoDTO2("","","","");
		EmpleadoDTO2 em3 = new EmpleadoDTO2("","","","");
		
		List<EmpleadoDTO2> empleadosDto1ps = Arrays.asList(em1,em2,em3);
		
		
		when(empleadoServices.getEmpleadosDTO2()).thenReturn((empleadosDto1ps));

		MvcResult mvcResult =  miniPostman.perform(get("/empleados/dto2").contentType("application/json"))
				.andExpect(status().isOk()).andReturn();

		String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

		assertThat(responseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(empleadosDto1ps));
		
		
	}

	@Test
	void testGetPage() throws Exception {

		Page <Empleado> pagedResponse = new PageImpl<>(Arrays.asList(empleado1, empleado2, empleado3));	
		
		Mockito.when(empleadoServices.getPage(0, 2)).thenReturn(pagedResponse);


		MvcResult mvcResult =  miniPostman.perform(get("/empleados/pagina")
								.contentType("application/json")
								.param("number", "0")
								.param("size", "2"))
								.andExpect(status().isOk()).andReturn();

		String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		

		assertThat(responseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(pagedResponse));
		
	}

	// *************************************************************************
	//
	// PRIVATE METHODS
	//
	// *************************************************************************

	private void initObjects() {

		empleado1 = new Empleado();
		empleado2 = new Empleado();
		empleado3 = new Empleado();

		empleado1.setDni("11111111R");
		empleado2.setDni("22222222L");
		empleado3.setDni("33333333T");

	}

}
