package com.demo.reservas.controller;

import com.demo.reservas.model.Reservation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class ReservationController {

    private final Map<Long, Reservation> reservas = new HashMap<>();
    private Long idCounter = 1L;

    // Constructor -> inicializa reservas por defecto
    public ReservationController() {
        reservas.put(idCounter, new Reservation(idCounter++, "hotel", "Vladimir Navarro", "Bogotá", "2025-10-15", "confirmada"));
        reservas.put(idCounter, new Reservation(idCounter++, "vuelo", "Ana Pérez", "Medellín", "2025-11-02", "pendiente"));
        reservas.put(idCounter, new Reservation(idCounter++, "hotel", "Carlos Ruiz", "Cartagena", "2025-09-25", "cancelada"));
    }

    // POST -> Crear reserva
    @PostMapping
    public Reservation crearReserva(@RequestBody Reservation nuevaReserva) {
        nuevaReserva.setId(idCounter++);
        nuevaReserva.setEstado("pendiente");
        reservas.put(nuevaReserva.getId(), nuevaReserva);
        return nuevaReserva;
    }

    // GET -> Consultar reserva por ID
    @GetMapping("/{id}")
    public Reservation obtenerReserva(@PathVariable Long id) {
        Reservation r = reservas.get(id);
        if (r == null) {
            return new Reservation(id, "desconocido", "N/A", "N/A", "N/A", "no encontrada");
        }
        return r;
    }

    // GET -> Listar todas las reservas
    @GetMapping
    public Collection<Reservation> obtenerTodasLasReservas() {
        return reservas.values();
    }
}
