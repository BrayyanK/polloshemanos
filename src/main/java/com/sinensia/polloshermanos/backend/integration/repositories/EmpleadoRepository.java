package com.sinensia.polloshermanos.backend.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.polloshermanos.backend.business.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, String>{

}
