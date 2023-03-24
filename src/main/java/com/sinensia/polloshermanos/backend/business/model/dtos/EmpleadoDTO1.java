package com.sinensia.polloshermanos.backend.business.model.dtos;

import java.io.Serializable;
import java.util.Objects;

public class EmpleadoDTO1 implements Serializable {
	private static final long serialVersionUID = 3L;

	private String nombreCompleto;
	
	public EmpleadoDTO1() {
		
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombreCompleto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpleadoDTO1 other = (EmpleadoDTO1) obj;
		return Objects.equals(nombreCompleto, other.nombreCompleto);
	}

	@Override
	public String toString() {
		return "EmpleadoDTO1 [nombreCompleto=" + nombreCompleto + "]";
	}
	
}
