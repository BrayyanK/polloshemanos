package com.sinensia.polloshermanos.backend.business.services.dummy.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.polloshermanos.backend.business.model.Familia;
import com.sinensia.polloshermanos.backend.business.model.Producto;
import com.sinensia.polloshermanos.backend.business.services.ProductoServices;
import com.sinensia.polloshermanos.backend.integration.utils.FakeDatabase;

@Service
public class ProductoServicesDummyImpl implements ProductoServices {

	@Autowired
	private FakeDatabase fakeDatabse;
	
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
		
		return fakeDatabse.getProductosMap().values().stream()
				.filter(x -> x.getFechaAlta().after(desde) && x.getFechaAlta().before(hasta))
				.collect(Collectors.toList());
	}

	@Override
	public List<Producto> findBetweenPriceRange(double min, double max) {
		
		return fakeDatabse.getProductosMap().values().stream()
				.filter(x -> x.getPrecio() >= min && x.getPrecio() <= max)
				.collect(Collectors.toList());
			
	}
	
	@Override
	public List<Producto> findByFamilia(Familia familia) {
		
		return fakeDatabse.getProductosMap().values().stream()
				.filter(x -> x.getFamilia() == familia)
				.collect(Collectors.toList());
	}


	@Override
	public List<Producto> findDescatalogados() {
		
		return fakeDatabse.getProductosMap().values().stream()
				.filter(x -> x.isDescatalogado())
				.collect(Collectors.toList());
	}

	public List<Producto> findByNombreLikeIgnoreCase(String nombre) {
		
		if(nombre == null) {
			return new ArrayList<>();
		}
		
		String nombreUpper = nombre.toUpperCase();
		
		return fakeDatabse.getProductosMap().values().stream()
				.filter(x -> {
					
					String strNombre = x.getNombre() != null ? x.getNombre().toUpperCase() : "";
				
					return strNombre.contains(nombreUpper);
	
				})
				.collect(Collectors.toList());
	}

	@Override
	public void incrementarPreciosByFamilia(Familia familia, double incremento) {
		
		fakeDatabse.getProductosMap().values().stream()
			.filter(x -> x.getFamilia() == familia)
			.forEach(x -> {
				double precio = x.getPrecio();
				x.setPrecio(precio + (precio * incremento / 100));
		});	
	}

	@Override
	public int getNumeroTotalProductos() {
		return fakeDatabse.getProductosMap().size();
	}

	@Override
	public int getNumeroTotalProductosByFamilia(Familia familia) {
		
		return (int)fakeDatabse.getProductosMap().values().stream()
				.filter(x -> x.getFamilia() == familia)
				.count();
	}
	
	@Override
	public Map<Familia, Integer> getEstadisticaNumeroProductosByFamilia() {
		
		Map<Familia, Integer> resultado = fakeDatabse.getProductosMap().values().stream()
				  .collect(Collectors.groupingBy(Producto::getFamilia, Collectors.summingInt(e -> 1)));
				  
		Arrays.asList(Familia.values()).stream().forEach(x -> resultado.putIfAbsent(x, 0));
	
		return resultado;
		
	}

	@Override
	public Map<Familia, Double> getEstadisticaPrecioMedioProductosByFamilia() {
		
		Map<Familia, Double> resultado = fakeDatabse.getProductosMap().values().stream()
				  .collect(Collectors.groupingBy(Producto::getFamilia, Collectors.averagingDouble(Producto::getPrecio)));
		
		Arrays.asList(Familia.values()).stream().forEach(x -> resultado.putIfAbsent(x, null));
		
		return resultado;
	}

	@Override
	public List<Familia> getFamilias() {
		// TODO Auto-generated method stub
		return null;
	}

}
