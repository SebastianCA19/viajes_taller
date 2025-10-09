package com.demo.itinerario.controllers;

import com.demo.itinerario.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/api/itinerary/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItineraryController {

    // GET /api/itinerary/v1/search?origin=&destination=&departDate=&returnDate=&adults=&rooms=
    @GetMapping("/search")
    public ResponseEntity<SearchResponse> searchItineraries() {
        // Hardcodeado: construimos 1 itinerario de ejemplo (similar al doc)
        FlightLeg outbound = new FlightLeg(
                "IB",
                "IB6584",
                OffsetDateTime.parse("2025-12-10T21:10:00-05:00"),
                OffsetDateTime.parse("2025-12-11T12:10:00+01:00"),
                0
        );

        FlightLeg inbound = new FlightLeg(
                "IB",
                "IB6583",
                OffsetDateTime.parse("2025-12-20T15:30:00+01:00"),
                OffsetDateTime.parse("2025-12-20T20:30:00-05:00"),
                0
        );

        FlightInfo flight = new FlightInfo(outbound, inbound);
        HotelInfo hotel = new HotelInfo(
                "Hotel Gran Vía Madrid",
                LocalDate.parse("2025-12-11"),
                LocalDate.parse("2025-12-20"),
                "Doble + extra",
                4.5
        );

        Money price = new Money("USD", new BigDecimal("1890.50"));

        Itinerary iti = new Itinerary(
                "ITI-BOG-MAD-20251210-20251220-001",
                price,
                flight,
                hotel
        );

        SearchResponse resp = new SearchResponse(List.of(iti), "SRCH-9c2b");
        return ResponseEntity.ok(resp);
    }

    // GET /api/itinerary/v1/details/{itineraryId}
    @GetMapping("/details/{itineraryId}")
    public ResponseEntity<Itinerary> getItineraryDetails(@PathVariable("itineraryId") String itineraryId) {
        // Para DEBUG: imprimimos el id en logs (System.out o usa logger)
        System.out.println("Request for itineraryId = '" + itineraryId + "'");

        // Si quieres que devuelva siempre el ejemplo (evita 404 durante pruebas)
        FlightLeg out = new FlightLeg(
                "IB",
                "IB6584",
                OffsetDateTime.parse("2025-12-10T21:10:00-05:00"),
                OffsetDateTime.parse("2025-12-11T12:10:00+01:00"),
                0
        );
        FlightLeg in = new FlightLeg(
                "IB",
                "IB6583",
                OffsetDateTime.parse("2025-12-20T15:30:00+01:00"),
                OffsetDateTime.parse("2025-12-20T20:30:00-05:00"),
                0
        );
        FlightInfo flight = new FlightInfo(out, in);
        HotelInfo hotel = new HotelInfo(
                "Hotel Gran Vía Madrid",
                LocalDate.parse("2025-12-11"),
                LocalDate.parse("2025-12-20"),
                "Doble + extra",
                4.5
        );
        Money price = new Money("USD", new BigDecimal("1890.50"));
        // Devolvemos el mismo id que llegó para ver que funciona
        Itinerary iti = new Itinerary(itineraryId, price, flight, hotel);
        return ResponseEntity.ok(iti);
    }

    // POST /api/itinerary/v1/user-itineraries
    // Simple: devuelve el mismo itinerario recibido (simulación de "guardar")
    @PostMapping(path = "/user-itineraries", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Itinerary> createUserItinerary(@RequestBody Itinerary request) {
        // Hardcode behavior: asignar un id si viene vacío
        if (request.getId() == null || request.getId().isBlank()) {
            request.setId("ITI-USER-0001");
        }
        // Retornamos el objeto como "creado"
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }

    // GET /api/itinerary/v1/user-itineraries/{id}
    @GetMapping("/user-itineraries/{id}")
    public ResponseEntity<Itinerary> getUserItinerary(@PathVariable("id") String id) {
        System.out.println("getUserItinerary -> id = '" + id + "'");
        // Hardcode: devolvemos solo si es el id correcto; si quieres que siempre devuelva algo, quita la condición
        if ("ITI-USER-0001".equals(id)) {
            Money price = new Money("USD", new BigDecimal("100.00"));
            FlightInfo flight = new FlightInfo(null, null);
            HotelInfo hotel = new HotelInfo("Demo Hotel", LocalDate.now(), LocalDate.now().plusDays(2), "Simple", 3.8);
            Itinerary iti = new Itinerary(id, price, flight, hotel);
            return ResponseEntity.ok(iti);
        } else {
            // Para depuración devuelve 404 pero con mensaje (opcional)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // DELETE /api/itinerary/v1/user-itineraries/{id}
    @DeleteMapping("/user-itineraries/{id}")
    public ResponseEntity<Void> deleteUserItinerary(@PathVariable String id) {
        // Hardcode: siempre retornamos 204 (simulación)
        return ResponseEntity.noContent().build();
    }
}

