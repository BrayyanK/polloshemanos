package com.sinensia.polloshermanos.backend.presentation.config;

import java.io.Serializable;

public class RespuestaError implements Serializable {
	private static final long serialVersionUID = 1L;

	private String mensaje;

	public RespuestaError(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
}
