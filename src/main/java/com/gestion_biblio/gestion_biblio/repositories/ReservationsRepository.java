package com.gestion_biblio.gestion_biblio.repositories;

import com.gestion_biblio.gestion_biblio.models.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationsRepository extends JpaRepository<Reservations,Integer> {
}
