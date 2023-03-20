package com.sinensia.polloshermanos.backend.showcase.business.model1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ESTACIONES_METEOROLOGICAS")
public class EstacionMeteorologica implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long codigo;
	
	private String nombre;
	
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	private boolean operativa;
	private double longitud;
	private double latitud;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	public EstacionMeteorologica() {
		
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public boolean isOperativa() {
		return operativa;
	}

	public void setOperativa(boolean operativa) {
		this.operativa = operativa;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstacionMeteorologica other = (EstacionMeteorologica) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
}
