package com.sinensia.polloshermanos.backend.showcase.business.model7;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="NOTICIAS")
public class Noticia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private String titular;
	private String subTitular;
	private String contenido;
	
	@ElementCollection
	@CollectionTable(name = "COMENTARIOS", 
			         joinColumns = @JoinColumn(name = "ID_NOTICIA"))
	private List<Comentario> comentarios;
	
	public Noticia() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getSubTitular() {
		return subTitular;
	}

	public void setSubTitular(String subTitular) {
		this.subTitular = subTitular;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Noticia other = (Noticia) obj;
		return Objects.equals(id, other.id);
	}
	
}
