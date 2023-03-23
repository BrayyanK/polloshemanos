package com.sinensia.polloshermanos.backend.integration.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sinensia.polloshermanos.backend.business.model.Familia;
import com.sinensia.polloshermanos.backend.business.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

	List<Producto> findByFechaAltaBetween(Date desde, Date hasta);
	
	List<Producto> findByPrecioBetween(double min, double max);
	
	List<Producto> findByFamilia(Familia familia);
	
	@Query("SELECT p FROM Producto p WHERE p.descatalogado = true")
	List<Producto> findDescatalogados();
	
	List<Producto> findByNombreContainingIgnoreCase(String nombre); 
	
	@Query("SELECT COUNT(p) FROM Producto p WHERE p.familia = :familia")
	long getNumeroTotalProductosByFamilia(Familia familia);
	
}
