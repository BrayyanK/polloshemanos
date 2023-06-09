package com.sinensia.polloshermanos.backend.business.model.dtos;

import java.io.Serializable;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(fichaEmpleado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpleadoDTO2 other = (EmpleadoDTO2) obj;
		return Objects.equals(fichaEmpleado, other.fichaEmpleado);
	}

}
