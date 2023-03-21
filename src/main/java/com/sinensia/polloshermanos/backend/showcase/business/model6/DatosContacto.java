package com.sinensia.polloshermanos.backend.showcase.business.model6;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DatosContacto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String telefono;
	private String email;
	
	public DatosContacto() {
		
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
