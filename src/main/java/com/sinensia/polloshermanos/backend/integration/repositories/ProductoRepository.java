package com.sinensia.polloshermanos.backend.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.polloshermanos.backend.business.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
