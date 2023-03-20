package com.sinensia.polloshermanos.backend.showcase.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.polloshermanos.backend.showcase.business.model2.Cancion;

public interface CancionRepository extends JpaRepository<Cancion, String>{

}
