package com.condor.backend.service;

import com.condor.backend.model.Compra;
import com.condor.backend.repository.CompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// CompraService.java
@Service
public class CompraService {

    private final CompraRepository compraRepository;

    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public Compra registrarCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    public List<Compra> obtenerTodas() {
        return compraRepository.findAll();
    }

    public Optional<Compra> obtenerPorId(Long id) {
        return compraRepository.findById(id);
    }

    public void eliminarPorId(Long id) {
        compraRepository.deleteById(id);
    }
}

