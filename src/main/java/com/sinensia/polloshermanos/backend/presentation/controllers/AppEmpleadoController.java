package com.sinensia.polloshermanos.backend.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.services.EmpleadoServices;

@Controller
@RequestMapping("/polloshermanos")
public class AppEmpleadoController {

	@Autowired
	private EmpleadoServices empleadoServices;

	@GetMapping("/ficha-empleado")
	public ModelAndView getFichaEmpleado(@RequestParam("dni") String dni) {

		Empleado empleado = empleadoServices.read(dni);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("ficha-empleado");
		mav.addObject("empleado", empleado);

		return mav;
	}

	@GetMapping("/empleados")
	public ModelAndView getListadoEmpleados() {

		List<Empleado> empleados = empleadoServices.findAll();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("listado-empleados");	// view
		mav.addObject("empleados", empleados);  // model

		return mav;
	}

}