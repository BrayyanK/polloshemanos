package com.sinensia.polloshermanos.backend.showcase.business.model7;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String autor;
	private String comentario;
	private int estrellas; // 0..5
	
	public Comentario() {
		
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}
	
}
