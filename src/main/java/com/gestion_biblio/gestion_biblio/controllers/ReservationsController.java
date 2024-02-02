package com.gestion_biblio.gestion_biblio.controllers;

import com.gestion_biblio.gestion_biblio.dtos.ReservationDto;
import com.gestion_biblio.gestion_biblio.models.Reservations;
import com.gestion_biblio.gestion_biblio.services.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "api/v1/Reservations")
public class ReservationsController {

    private final ReservationsService reservationsService;

    @Autowired
    public ReservationsController(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }

    @GetMapping
    public ResponseEntity<List<Reservations>> getAllReservations() {
        List<Reservations> reservations = reservationsService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservations> getReservationById(@PathVariable Integer id) {
        Reservations reservation = reservationsService.getReservationById(id);
        return reservation != null ? ResponseEntity.ok(reservation) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Reservations> createReservation(@RequestBody ReservationDto reservationDto) {
        Reservations createdReservation = reservationsService.createReservation(reservationDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdReservation.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdReservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservations> updateReservation(@PathVariable Integer id,
                                                          @RequestBody ReservationDto updatedReservationDto) {
        Reservations updatedReservation = reservationsService.updateReservation(id, updatedReservationDto);
        return updatedReservation != null ? ResponseEntity.ok(updatedReservation) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {
        reservationsService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
