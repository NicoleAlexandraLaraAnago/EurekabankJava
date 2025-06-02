package com.condor.backend.service;

import com.condor.backend.repository.VueloRepository;
import com.condor.backend.model.Vuelo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// VueloService.java
@Service
public class VueloService {

    private final VueloRepository vueloRepository;

    public VueloService(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    public List<Vuelo> buscarVuelos(String origen, String destino, LocalDate fecha) {
        return vueloRepository.findByCiudadOrigenAndCiudadDestinoAndFecha(origen, destino, fecha);
    }

    public Vuelo guardarVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    public List<Vuelo> obtenerTodos() {
        return vueloRepository.findAll();
    }

    public Optional<Vuelo> obtenerPorId(Long id) {
        return vueloRepository.findById(id);
    }

    public void eliminarPorId(Long id) {
        vueloRepository.deleteById(id);
    }
}
