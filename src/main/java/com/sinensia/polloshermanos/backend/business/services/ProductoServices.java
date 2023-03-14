package com.sinensia.polloshermanos.backend.business.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sinensia.polloshermanos.backend.business.model.Familia;
import com.sinensia.polloshermanos.backend.business.model.Producto;

public interface ProductoServices {

	Producto create(Producto producto);		// C
	Producto read(Long codigo);				// R
	void update(Producto producto);			// U
	void delete(Long codigo);				// D
	
	List<Producto> findAll();
	List<Producto> findBetweenDates(Date desde, Date hasta);
	List<Producto> findBetweenPriceRange(double min, double max);
	List<Producto> findByFamilia(Familia familia);
	List<Producto> findDescatalogados();
	List<Producto> findByNombreLikeIgnoreCase(String nombre);
	
	/**
	 * El incremento es un porcentaje. 
	 * 
	 * Por ejemplo, para incrementear un 50% pasaremos el argumento 50.0
	 *
	 */
	void incrementarPreciosByFamilia(Familia familia, double incremento);
	
	int getNumeroTotalProductos();
	int getNumeroTotalProductosByFamilia(Familia familia);
	
	Map<Familia, Integer> getEstadisticaNumeroProductosByFamilia();
	Map<Familia, Double> getEstadisticaPrecioMedioProductosByFamilia();

}
