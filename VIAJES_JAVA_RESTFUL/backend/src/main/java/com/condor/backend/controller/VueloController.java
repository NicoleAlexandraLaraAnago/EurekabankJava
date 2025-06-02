package com.condor.backend.controller;

import com.condor.backend.model.Vuelo;
import com.condor.backend.service.VueloService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://192.168.100.36:3000")
@RestController
@RequestMapping("/vuelos")
public class VueloController {

    private final VueloService vueloService;

    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping
    public List<Vuelo> buscarVuelos(@RequestParam String origen,
                                    @RequestParam String destino,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return vueloService.buscarVuelos(origen, destino, fecha);
    }

    @PostMapping
    public Vuelo guardarVuelo(@RequestBody Vuelo vuelo) {
        return vueloService.guardarVuelo(vuelo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vuelo> obtenerPorId(@PathVariable Long id) {
        return vueloService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVuelo(@PathVariable Long id) {
        vueloService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
