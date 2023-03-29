package com.sinensia.polloshermanos.backend.business.model.dtos;

import java.io.Serializable;
import java.util.Objects;

public class ProductoDTO1 implements Serializable{
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String producto;

	public ProductoDTO1() {

	}

	public ProductoDTO1(String codigo, String producto) {
		this.codigo = codigo;
		this.producto = producto;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getProducto() {
		return producto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, producto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoDTO1 other = (ProductoDTO1) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(producto, other.producto);
	}

}