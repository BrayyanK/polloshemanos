package com.sinensia.polloshermanos.backend.showcase.business.model4;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;


@Entity
@Table(name="EQUIPOS")
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private String nombre;
	private Integer numeroTitulos;
	
	@OneToMany
	@JoinColumn(name="ID_EQUIPO")
	@OrderColumn(name="ORDEN")
	private List<Jugador> jugadores;
	
	public Equipo() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumeroTitulos() {
		return numeroTitulos;
	}

	public void setNumeroTitulos(Integer numeroTitulos) {
		this.numeroTitulos = numeroTitulos;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
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
		Equipo other = (Equipo) obj;
		return Objects.equals(id, other.id);
	}

}
