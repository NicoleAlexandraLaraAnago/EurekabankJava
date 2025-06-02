package com.condor.backend.controller;

import com.condor.backend.model.Usuario;
import com.condor.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://192.168.100.36:3000")
public class AuthController {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            return ResponseEntity.badRequest().body("Correo ya registrado");
        }

        // Rol por defecto
        if (usuario.getRol() == null || usuario.getRol().isBlank()) {
            usuario.setRol("USER");
        }

        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario.getNombre());
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Usuario login) {
        return usuarioRepository.findByCorreo(login.getCorreo())
                .filter(u -> u.getPassword().equals(login.getPassword()))
                .map(u -> ResponseEntity.ok(Map.of(
                        "mensaje", "Login exitoso",
                        "nombre", u.getNombre(),
                        "rol", u.getRol()
                )))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                        Map.of("mensaje", "Credenciales incorrectas")
                ));
    }


}