package com.sinensia.polloshermanos.backend.business.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	public Producto create(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto read(Long codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public List<Producto> findBetweenDates(Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> findBetweenPriceRange(double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> findByFamilia(Familia familia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> findDescatalogados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> findByNombreLikeIgnoreCase(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void incrementarPreciosByFamilia(Familia familia, double incremento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumeroTotalProductos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumeroTotalProductosByFamilia(Familia familia) {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return null;
	}

}
