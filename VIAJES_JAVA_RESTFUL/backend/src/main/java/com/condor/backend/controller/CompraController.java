package com.condor.backend.controller;

import com.condor.backend.model.Compra;
import com.condor.backend.service.CompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://192.168.100.36:3000")
@RestController
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    public Compra realizarCompra(@RequestBody Compra compra) {
        return compraService.registrarCompra(compra);
    }

    @GetMapping
    public List<Compra> obtenerTodasLasCompras() {
        return compraService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> obtenerCompraPorId(@PathVariable Long id) {
        return compraService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable Long id) {
        compraService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
