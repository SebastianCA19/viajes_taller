package com.demo.reservas.controller;

import com.demo.reservas.model.Reservation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/booking/v1/reservations")
public class ReservationController {

    private final Map<Long, Reservation> reservas = new HashMap<>();
    private Long idCounter = 1L;

    // POST -> Crear reserva de hotel o vuelo
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
}
