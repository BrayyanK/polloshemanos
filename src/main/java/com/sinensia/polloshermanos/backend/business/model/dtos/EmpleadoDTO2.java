package com.sinensia.polloshermanos.backend.business.model.dtos;

import java.io.Serializable;

public class EmpleadoDTO2 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String fichaEmpleado;
	
	public EmpleadoDTO2(String dni, String apellido1, String apellido2, String nombre) {
		
		apellido2 = apellido2 == null ? "": apellido2;
		
		this.fichaEmpleado = ("[" + dni + "] " + apellido1 + " " + apellido2 + ", " + nombre).toUpperCase();
	}

	public String getFichaEmpleado() {
		return fichaEmpleado;
	}

}
