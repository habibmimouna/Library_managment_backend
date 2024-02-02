package com.gestion_biblio.gestion_biblio.services;

import com.gestion_biblio.gestion_biblio.dtos.ReservationDto;
import com.gestion_biblio.gestion_biblio.models.Reservations;
import com.gestion_biblio.gestion_biblio.repositories.ReservationsRepository;
import com.gestion_biblio.gestion_biblio.services.UserService;
import com.gestion_biblio.gestion_biblio.services.LivreService;
import com.gestion_biblio.gestion_biblio.services.impl.UtilisateurServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationsService {

    private final ReservationsRepository reservationsRepository;
    private final UtilisateurServiceImpl userService;
    private final LivreService livreService;

    @Autowired
    public ReservationsService(ReservationsRepository reservationsRepository, UtilisateurServiceImpl userService, LivreService livreService) {
        this.reservationsRepository = reservationsRepository;
        this.userService = userService;
        this.livreService = livreService;
    }

    public List<Reservations> getAllReservations() {
        return reservationsRepository.findAll();
    }

    public Reservations getReservationById(Integer reservationId) {
        return reservationsRepository.findById(reservationId).orElse(null);
    }

    public Reservations createReservation(ReservationDto reservationDto) {
        Reservations reservation = new Reservations();
        mapDtoToReservation(reservationDto, reservation);
        return reservationsRepository.save(reservation);
    }

    public Reservations updateReservation(Integer reservationId, ReservationDto updatedReservationDto) {
        Optional<Reservations> existingReservationOptional = reservationsRepository.findById(reservationId);

        if (existingReservationOptional.isPresent()) {
            Reservations existingReservation = existingReservationOptional.get();
            mapDtoToReservation(updatedReservationDto, existingReservation);
            return reservationsRepository.save(existingReservation);
        } else {
            throw new EntityNotFoundException("Reservation not found with ID: " + reservationId);
        }
    }


    public void deleteReservation(Integer reservationId) {
        reservationsRepository.deleteById(reservationId);
    }

    private void mapDtoToReservation(ReservationDto reservationDto, Reservations reservation) {
        reservation.setDateReservation(reservationDto.getDateReservation());

        if (reservationDto.getUserId() != null) {
            reservation.setUser(userService.getUserById(reservationDto.getUserId()));
        }

        if (reservationDto.getLivreId() != null) {
            reservation.setLivre(livreService.getLivreById(reservationDto.getLivreId()));
        }
    }
}
