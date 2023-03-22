package com.sinensia.polloshermanos.backend.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sinensia.polloshermanos.backend.business.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, String>{

	List<Empleado> findByNombreContainingIgnoreCase(String texto);
	
	@Query("SELECT COUNT(e) FROM Empleado e WHERE e.activo = :activo") // JPQL
	long getNumeroTotalEmpleados(boolean activo);
	
}
