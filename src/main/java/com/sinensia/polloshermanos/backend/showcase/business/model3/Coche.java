package com.sinensia.polloshermanos.backend.showcase.business.model3;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="COCHES")
public class Coche implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String matricula;
	
	@Temporal(TemporalType.DATE)
	private Date fechaMatriculacion;
	
	private String marca;
	private String modelo;
	private double precio;
	private int numeroPuertas;
	private boolean siniestrado;
	private Motor motor;
	private Long kilometros;
	
	public Coche() {
		
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getFechaMatriculacion() {
		return fechaMatriculacion;
	}

	public void setFechaMatriculacion(Date fechaMatriculacion) {
		this.fechaMatriculacion = fechaMatriculacion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getNumeroPuertas() {
		return numeroPuertas;
	}

	public void setNumeroPuertas(int numeroPuertas) {
		this.numeroPuertas = numeroPuertas;
	}

	public boolean isSiniestrado() {
		return siniestrado;
	}

	public void setSiniestrado(boolean siniestrado) {
		this.siniestrado = siniestrado;
	}

	public Motor getMotor() {
		return motor;
	}

	public void setMotor(Motor motor) {
		this.motor = motor;
	}

	public Long getKilometros() {
		return kilometros;
	}

	public void setKilometros(Long kilometros) {
		this.kilometros = kilometros;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		return Objects.equals(matricula, other.matricula);
	}
		
}
