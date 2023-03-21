package com.sinensia.polloshermanos.backend.showcase.business.model6;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ALMACENES")
public class Almacen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private int metrosCuadrados;
	
	@Embedded
	@AttributeOverrides({
        @AttributeOverride(name="direccion", column=@Column(name="DIR")),
        @AttributeOverride(name="poblacion", column=@Column(name="MUNICIPIO")),
        @AttributeOverride(name="codigoPostal", column=@Column(name="CP")),
        @AttributeOverride(name="pais", column=@Column(name="COUNTRY"))
    })
	private Direccion direccion;
	
	public Almacen() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMetrosCuadrados() {
		return metrosCuadrados;
	}

	public void setMetrosCuadrados(int metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
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
		Almacen other = (Almacen) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
