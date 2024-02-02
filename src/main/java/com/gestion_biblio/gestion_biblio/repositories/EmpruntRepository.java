package com.gestion_biblio.gestion_biblio.repositories;

import com.gestion_biblio.gestion_biblio.models.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt,Integer> {
}
