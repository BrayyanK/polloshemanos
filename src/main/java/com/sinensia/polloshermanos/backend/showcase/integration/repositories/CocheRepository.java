package com.sinensia.polloshermanos.backend.showcase.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sinensia.polloshermanos.backend.showcase.business.model3.Coche;
import com.sinensia.polloshermanos.backend.showcase.business.model3.Motor;

public interface CocheRepository extends JpaRepository<Coche, String> {

	@Query("SELECT c FROM Coche c WHERE c.precio > :min AND c.precio < :max")
	List<Coche> buscarEntrePrecios(double min, double max);
	
	List<Coche> findByPrecioBetween(double min, double max);
	List<Coche> findByPrecioGreaterThan(double min);
	List<Coche> findByMotor(Motor motor);
	List<Coche> findByMotorOrderByPrecio(Motor motor);
	List<Coche> findByMotorAndPrecioBetweenOrderByPrecio(Motor motor, double min, double max);
	
}
