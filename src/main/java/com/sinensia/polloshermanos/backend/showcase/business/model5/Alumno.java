package com.sinensia.polloshermanos.backend.showcase.business.model5;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="ALUMNOS")
public class Alumno implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String nombreCompleto;
	
	@ManyToMany
	@JoinTable(name = "ASOCIA_ALUMNOS_ASIGNATURAS", 
	           joinColumns = @JoinColumn(name = "ID_ALUMNO") , 
	           inverseJoinColumns = @JoinColumn(name = "ID_ASIGNATURA"))
	private List<Asignatura> asignaturas;
	
	public Alumno() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
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
		Alumno other = (Alumno) obj;
		return Objects.equals(id, other.id);
	}
	
}
