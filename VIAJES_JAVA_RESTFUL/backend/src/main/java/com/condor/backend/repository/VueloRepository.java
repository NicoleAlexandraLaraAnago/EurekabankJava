package com.condor.backend.repository;

import com.condor.backend.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {
    List<Vuelo> findByCiudadOrigenAndCiudadDestinoAndFecha(String ciudadOrigen, String ciudadDestino, LocalDate fecha);
}

