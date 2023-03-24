package com.sinensia.polloshermanos.backend.integration.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.model.dtos.EmpleadoDTO2;

public interface EmpleadoRepository extends JpaRepository<Empleado, String>{

	List<Empleado> findByNombreContainingIgnoreCase(String texto);
	
	@Query("SELECT COUNT(e) FROM Empleado e WHERE e.activo = :activo") // JPQL
	long getNumeroTotalEmpleados(boolean activo);
	
	@Query("SELECT UPPER(CONCAT(e.apellido1,' ', e.apellido2, '*', e.nombre)) FROM Empleado e")
	List<String> getEmpleadosDTO1();
	
	@Query("SELECT new com.sinensia.polloshermanos.backend.business.model.dtos.EmpleadoDTO2(e.dni, e.apellido1, e.apellido2, e.nombre) "
		 + "  FROM Empleado e")
	List<EmpleadoDTO2> getEmpleadosDTO2();
	
	// *************************************************************************
	//
	// Queries Nativas SQL
	//
	// *************************************************************************
	
	@Query(value="SELECT UPPER(E.APELLIDO1 || ' ' || E.APELLIDO2 || ', ' || E.NOMBRE) FROM EMPLEADOS E;", nativeQuery = true)
	List<String> getNombresCompletosEmpleados();
	
	@Query(value="SELECT E.DNI,       "
			   + "       E.NOMBRE,    "
			   + "       E.APELLIDO1, "
			   + "       E.APELLIDO2, "
			   + "       E.ACTIVO     "
			   + "  FROM EMPLEADOS E  "
			   + " ORDER BY E.DNI     ", nativeQuery = true)
	List<Empleado> getEmpleadosDeFormaNativa();
	
	// *************************************************************************
	//
	// Obtención de páginas
	//
	// *************************************************************************
	
	@Query("SELECT e FROM Empleado e ORDER BY e.dni")
	Page<Empleado> findPage(Pageable pageable);
	
}
