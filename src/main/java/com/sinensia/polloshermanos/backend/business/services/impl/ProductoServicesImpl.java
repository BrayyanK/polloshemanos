package com.sinensia.polloshermanos.backend.business.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sinensia.polloshermanos.backend.business.model.Familia;
import com.sinensia.polloshermanos.backend.business.model.Producto;
import com.sinensia.polloshermanos.backend.business.services.ProductoServices;
import com.sinensia.polloshermanos.backend.integration.repositories.ProductoRepository;

@Service
@Primary
public class ProductoServicesImpl implements ProductoServices {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	@Transactional
	public Producto create(Producto producto) {
		
		if(producto.getCodigo() != null) {
			throw new IllegalStateException("Para crear un producto el código ha de ser null");
		}
	
		return productoRepository.save(producto);
	}

	@Override
	public Producto read(Long codigo) {
		return productoRepository.findById(codigo).orElse(null);
	}

	@Override
	@Transactional
	public void update(Producto producto) {
		
		boolean existe = productoRepository.existsById(producto.getCodigo());
		
		if(!existe) {
			throw new IllegalStateException("No existe el producto con código " + producto.getCodigo());
		}
		
		productoRepository.save(producto);
	}

	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public List<Producto> findBetweenDates(Date desde, Date hasta) {
		return productoRepository.findByFechaAltaBetween(desde, hasta);
	}

	@Override
	public List<Producto> findBetweenPriceRange(double min, double max) {
		return productoRepository.findByPrecioBetween(min, max);
	}

	@Override
	public List<Producto> findByFamilia(Familia familia) {
		return productoRepository.findByFamilia(familia);
	}

	@Override
	public List<Producto> findDescatalogados() {
		return productoRepository.findDescatalogados();
	}

	@Override
	public List<Producto> findByNombreLikeIgnoreCase(String nombre) {
		return productoRepository.findByNombreContainingIgnoreCase(nombre);
	}

	@Override
	public void incrementarPreciosByFamilia(Familia familia, double incremento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumeroTotalProductos() {
		return (int) productoRepository.count();
	}

	@Override
	public int getNumeroTotalProductosByFamilia(Familia familia) {
		return (int) productoRepository.getNumeroTotalProductosByFamilia(familia);
	}

	@Override
	public Map<Familia, Integer> getEstadisticaNumeroProductosByFamilia() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Familia, Double> getEstadisticaPrecioMedioProductosByFamilia() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Familia> getFamilias() {
		return Arrays.asList(Familia.values());
	}

}
