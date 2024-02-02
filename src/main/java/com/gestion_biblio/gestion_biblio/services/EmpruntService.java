package com.gestion_biblio.gestion_biblio.services;

import com.gestion_biblio.gestion_biblio.dtos.EmpruntDto;
import com.gestion_biblio.gestion_biblio.models.Emprunt;
import com.gestion_biblio.gestion_biblio.models.Livre;
import com.gestion_biblio.gestion_biblio.models.User;
import com.gestion_biblio.gestion_biblio.repositories.EmpruntRepository;
import com.gestion_biblio.gestion_biblio.repositories.LivreRepository;
import com.gestion_biblio.gestion_biblio.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpruntService {

    private final EmpruntRepository empruntRepository;
    private final UserRepository userRepository;

    private final LivreRepository livreRepository;

    @Autowired
    public EmpruntService(EmpruntRepository empruntRepository, UserRepository userRepository, LivreRepository livreRepository) {
        this.empruntRepository = empruntRepository;
        this.userRepository = userRepository;
        this.livreRepository = livreRepository;
    }

    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    public Emprunt getEmpruntById(Integer empruntId) {
        return empruntRepository.findById(empruntId).orElse(null);
    }

    public Emprunt createEmprunt(EmpruntDto empruntDto) {
        Emprunt emprunt = new Emprunt();
        mapDtoToEmprunt(empruntDto, emprunt);
        return empruntRepository.save(emprunt);
    }

    public Emprunt updateEmprunt(Integer empruntId, EmpruntDto updatedEmpruntDto) {
        Optional<Emprunt> existingEmpruntOptional = empruntRepository.findById(empruntId);

        if (existingEmpruntOptional.isPresent()) {
            Emprunt existingEmprunt = existingEmpruntOptional.get();
            mapDtoToEmprunt(updatedEmpruntDto, existingEmprunt);
            return empruntRepository.save(existingEmprunt);
        } else {
            return null;
        }
    }

    public void deleteEmprunt(Integer empruntId) {
        empruntRepository.deleteById(empruntId);
    }

    private void mapDtoToEmprunt(EmpruntDto empruntDto, Emprunt emprunt) {
        emprunt.setDateDebut(empruntDto.getDateDebut());
        emprunt.setDateFin(empruntDto.getDateFin());

        if (empruntDto.getUserId() != null) {
            User user = userRepository.findById(empruntDto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + empruntDto.getUserId()));
            emprunt.setUser(user);
        }

        if (empruntDto.getLivreId() != null) {
            Livre livre = livreRepository.findById(empruntDto.getLivreId())
                    .orElseThrow(() -> new EntityNotFoundException("Livre not found with ID: " + empruntDto.getLivreId()));
            emprunt.setLivre(livre);
        }
    }
}
