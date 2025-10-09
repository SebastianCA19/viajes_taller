package com.demo.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback/reservas")
    public ResponseEntity<String> reservasFallback() {
        return ResponseEntity.ok("El servicio de reservas no está disponible por el momento");
    }

    @GetMapping("/fallback/itinerario")
    public ResponseEntity<String> itinerarioFallback() {
        return ResponseEntity.ok("El servicio de itinerarios no está disponible por el momento");
    }
}
