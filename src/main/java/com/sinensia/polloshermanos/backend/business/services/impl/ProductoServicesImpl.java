package com.sinensia.polloshermanos.backend.business.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.sinensia.polloshermanos.backend.business.model.Familia;
import com.sinensia.polloshermanos.backend.business.model.Producto;
import com.sinensia.polloshermanos.backend.business.services.ProductoServices;
import com.sinensia.polloshermanos.backend.integration.utils.FakeDatabase;

public class ProductoServicesImpl implements ProductoServices {

	private FakeDatabase fakeDatabse = FakeDatabase.getInstance();
	
	@Override
	public Producto create(Producto producto) {
		
		if(producto.getCodigo() != null) {
			throw new IllegalStateException("No se puede crear un producto que ya tienen código.");
		}
		
		Long ultimoCodigo = ((TreeMap<Long,Producto>) fakeDatabse.getProductosMap()).lastKey();
		
		Long codigo = ultimoCodigo + 1;
		
		producto.setCodigo(codigo);
		
		fakeDatabse.getProductosMap().put(producto.getCodigo(), producto);
		
		return producto;
	}

	@Override
	public Producto read(Long codigo) {
		return fakeDatabse.getProductosMap().get(codigo);
	}

	@Override
	public void update(Producto producto) {

		Long codigo = producto.getCodigo();
		
		if(codigo == null) {
			throw new IllegalStateException("No se puede actualizar un producto con código null.");
		}
		
		boolean existe = fakeDatabse.getProductosMap().containsKey(codigo);
		
		if(!existe) {
			throw new IllegalStateException("El producto con código " + codigo + " no existe.");
		}
		
		fakeDatabse.getProductosMap().replace(codigo, producto);
		
	}

	@Override
	public void delete(Long codigo) {
		
		boolean existe = fakeDatabse.getProductosMap().containsKey(codigo);
		
		if(!existe) {
			throw new IllegalStateException("El producto " + codigo + " no existe.");
		}
		
		fakeDatabse.getProductosMap().remove(codigo);
		
	}

	@Override
	public List<Producto> findAll() {
		return new ArrayList<>(fakeDatabse.getProductosMap().values());
	}

	@Override
	public List<Producto> findBetweenDates(Date desde, Date hasta) {
		
		List<Producto> productos = new ArrayList<>();
		
		for(Producto producto: fakeDatabse.getProductosMap().values()) {
			if(producto.getFechaAlta().after(desde) && producto.getFechaAlta().before(hasta)) {
				productos.add(producto);
			}
		}
		
		return productos;
	}

	@Override
	public List<Producto> findBetweenPriceRange(double min, double max) {
		
		List<Producto> productos = new ArrayList<>();
		
		for(Producto producto: fakeDatabse.getProductosMap().values()) {
			if(producto.getPrecio() >= min && producto.getPrecio() <= max) {
				productos.add(producto);
			}
		}
		
		return productos;
	}

	@Override
	public List<Producto> findByFamilia(Familia familia) {
		
		List<Producto> productos = new ArrayList<>();
		
		for(Producto producto: fakeDatabse.getProductosMap().values()) {
			if(producto.getFamilia() == familia) {
				productos.add(producto);
			}
		}
		
		return productos;
	}

	@Override
	public List<Producto> findDescatalogados() {
		
		List<Producto> productos = new ArrayList<>();
		
		for(Producto producto: fakeDatabse.getProductosMap().values()) {
			if(producto.isDescatalogado()) {
				productos.add(producto);
			}
		}
		
		return productos;
	}

	public List<Producto> findByNombreLikeIgnoreCase(String nombre) {
		
		List<Producto> productos = new ArrayList<>();
		
		if(nombre == null) {
			return productos;
		}
		
		nombre = nombre.toUpperCase();
		
		for(Producto producto: fakeDatabse.getProductosMap().values()) {
		
			String nombreProducto = producto.getNombre();
			
			if(nombreProducto != null && nombreProducto.toUpperCase().contains(nombre)) {
				productos.add(producto);
			}
		}
		
		return productos;
	}

	@Override
	public void incrementarPreciosByFamilia(Familia familia, double incremento) {
		
		for(Producto producto: fakeDatabse.getProductosMap().values()) {
			if(producto.getFamilia() == familia) {
				double precio = producto.getPrecio();
				precio = precio + (precio * incremento / 100);
				producto.setPrecio(precio);
			}
		}
		
	}

	@Override
	public int getNumeroTotalProductos() {
		return fakeDatabse.getProductosMap().size();
	}

	@Override
	public int getNumeroTotalProductosByFamilia(Familia familia) {
		
		int contador = 0;
		
		for(Producto producto: fakeDatabse.getProductosMap().values()) {
			if (producto.getFamilia() == familia) {
				contador++;
			}
		}
		
		return contador;
	}
	
	@Override
	public Map<Familia, Integer> getEstadisticaNumeroProductosByFamilia() {
		
		Map<Familia, Integer> estadistica = new HashMap<>();
		
		for(Familia familia: Familia.values()) {
			estadistica.put(familia, 0);
		}
		
		for(Producto producto: fakeDatabse.getProductosMap().values()) {
			Familia familia = producto.getFamilia();
			
			Integer cantidadProductos = estadistica.get(familia);
			cantidadProductos = cantidadProductos + 1;
			estadistica.replace(familia, cantidadProductos);
		}
		
		return estadistica;
	}

	@Override
	public Map<Familia, Double> getEstadisticaPrecioMedioProductosByFamilia() {

		Map<Familia, Double> estadisticaFamilias = new HashMap<>();
		
		int contComida = 0;
		int contRefresco = 0;
		int contCerveza = 0;
		int contLicor = 0; 
		int contTapa = 0;
		int contCafe = 0;
		int contPostre = 0;
		int contBocadillo = 0;

		double precioComida = 0.0;
		double precioRefresco = 0.0;
		double precioCerveza = 0.0;
		double precioLicor = 0.0;
		double precioTapa = 0.0;
		double precioCafe = 0.0;
		double precioPostre = 0.0;
		double precioBocadillo = 0.0;

		for (Producto producto : fakeDatabse.getProductosMap().values()) {
	
			switch (producto.getFamilia()) {

			case COMIDA:
				contComida++;
				precioComida += producto.getPrecio();
				break;
			case REFRESCO:
				contRefresco++;
				precioRefresco += producto.getPrecio();
				break;
			case CERVEZA:
				contCerveza++;
				precioCerveza += producto.getPrecio();
				break;
			case LICOR:
				contLicor++;
				precioLicor += producto.getPrecio();
				break;
			case TAPA:
				contTapa++;
				precioTapa += producto.getPrecio();
				break;
			case CAFE:
				contCafe++;
				precioCafe += producto.getPrecio();
				break;
			case POSTRE:
				contPostre++;
				precioPostre += producto.getPrecio();
				break;
			case BOCADILLO:
				contBocadillo++;
				precioBocadillo += producto.getPrecio();
				break;

			}

		}
		
		estadisticaFamilias.put(Familia.COMIDA, contComida == 0 ? null: precioComida/contComida);
		estadisticaFamilias.put(Familia.REFRESCO, precioRefresco == 0 ? null: precioRefresco/contRefresco);
		estadisticaFamilias.put(Familia.CERVEZA, precioCerveza == 0 ? null: precioCerveza/contCerveza);
		estadisticaFamilias.put(Familia.LICOR, precioLicor == 0 ? null: precioLicor/contLicor);
		estadisticaFamilias.put(Familia.TAPA, precioTapa == 0 ? null: precioTapa/contTapa);
		estadisticaFamilias.put(Familia.CAFE, precioCafe == 0 ? null: precioCafe/contCafe);
		estadisticaFamilias.put(Familia.POSTRE, precioPostre == 0 ? null: precioPostre/contPostre);
		estadisticaFamilias.put(Familia.BOCADILLO, precioBocadillo == 0 ? null: precioBocadillo/contBocadillo);
		
		return estadisticaFamilias;
	
	}
}
