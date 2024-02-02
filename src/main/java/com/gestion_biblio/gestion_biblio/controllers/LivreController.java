package com.gestion_biblio.gestion_biblio.controllers;

import com.gestion_biblio.gestion_biblio.dtos.LivreDto;
import com.gestion_biblio.gestion_biblio.models.Livre;
import com.gestion_biblio.gestion_biblio.services.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "api/v1/Livre")
public class LivreController {

    private final LivreService livreService;

    @Autowired
    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @GetMapping
    public List<Livre> getAllLivres() {
        return livreService.getAllLivres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Integer id) {
        Livre livre = livreService.getLivreById(id);
        return livre != null ? ResponseEntity.ok(livre) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createLivre(@RequestBody LivreDto livreDto) {
        Livre livre = livreService.createLivre(livreDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livre.getId())
                .toUri();
        return ResponseEntity.created(location).body(livre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLivre(@PathVariable Integer id, @RequestBody LivreDto updatedLivreDto) {
        ResponseEntity<String> updatedLivre = livreService.updateLivre(id, updatedLivreDto);
        return updatedLivre != null ? ResponseEntity.ok(updatedLivre) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLivre(@PathVariable Integer id) {
        livreService.deleteLivre(id);
        return ResponseEntity.ok("Livre with ID " + id + " deleted successfully");
    }
    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<Livre>> getLivresByCategoryId(@PathVariable Integer categoryId) {
        List<Livre> livres = livreService.getLivresByCategoryId(categoryId);
        return ResponseEntity.ok(livres);
    }

}
