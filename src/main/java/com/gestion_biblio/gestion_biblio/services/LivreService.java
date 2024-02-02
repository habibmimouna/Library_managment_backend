package com.gestion_biblio.gestion_biblio.services;

import com.gestion_biblio.gestion_biblio.dtos.LivreDto;
import com.gestion_biblio.gestion_biblio.models.Categories;
import com.gestion_biblio.gestion_biblio.models.Livre;
import com.gestion_biblio.gestion_biblio.repositories.CategoriesRepository;
import com.gestion_biblio.gestion_biblio.repositories.LivreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {

    private final LivreRepository livreRepository;
    private final CategoriesRepository categoriesRepository;

    @Autowired
    public LivreService(LivreRepository livreRepository, CategoriesRepository categoriesRepository) {
        this.livreRepository = livreRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    public Livre getLivreById(Integer livreId) {
        return livreRepository.findById(livreId).orElse(null);
    }

    public Livre createLivre(LivreDto livreDto) {
        Livre livre = new Livre();
        mapDtoToLivre(livreDto, livre);
        return livreRepository.save(livre);
    }


    public ResponseEntity<String> updateLivre(Integer livreId, LivreDto updatedLivreDto) {
        Optional<Livre> existingLivreOptional = livreRepository.findById(livreId);

        if (existingLivreOptional.isPresent()) {
            Livre existingLivre = existingLivreOptional.get();
            mapDtoToLivre(updatedLivreDto, existingLivre);
            Livre updatedLivre = livreRepository.save(existingLivre);
            return ResponseEntity.ok("Livre updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livre not found with ID: " + livreId);
        }
    }



    public void deleteLivre(Integer livreId) {
        livreRepository.deleteById(livreId);
    }

    private void mapDtoToLivre(LivreDto livreDto, Livre livre) {
        livre.setTitre(livreDto.getTitre());
        livre.setAuteur(livreDto.getAuteur());
        livre.setDatePublication(livreDto.getDatePublication());
        livre.setIsbn(livreDto.getIsbn());

        if (livreDto.getCategoryId() != null) {
            Categories category = categoriesRepository.findById(livreDto.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + livreDto.getCategoryId()));
            livre.setCategories(category);
        }
    }
    public List<Livre> getLivresByCategoryId(Integer categoryId) {
        Categories category = categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + categoryId));
        return livreRepository.findByCategories(category);
    }


}
