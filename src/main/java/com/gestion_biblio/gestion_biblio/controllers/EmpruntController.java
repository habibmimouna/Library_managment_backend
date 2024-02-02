package com.gestion_biblio.gestion_biblio.controllers;

import com.gestion_biblio.gestion_biblio.dtos.EmpruntDto;
import com.gestion_biblio.gestion_biblio.models.Emprunt;
import com.gestion_biblio.gestion_biblio.services.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "api/v1/Emprunt")
public class EmpruntController {

    private final EmpruntService empruntService;

    @Autowired
    public EmpruntController(EmpruntService empruntService) {
        this.empruntService = empruntService;
    }

    @GetMapping
    public ResponseEntity<List<Emprunt>> getAllEmprunts() {
        List<Emprunt> emprunts = empruntService.getAllEmprunts();
        return ResponseEntity.ok(emprunts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprunt> getEmpruntById(@PathVariable Integer id) {
        Emprunt emprunt = empruntService.getEmpruntById(id);
        if (emprunt != null) {
            return ResponseEntity.ok(emprunt);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Void> createEmprunt(@RequestBody EmpruntDto empruntDto) {
        Emprunt createdEmprunt = empruntService.createEmprunt(empruntDto);

        if (createdEmprunt != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdEmprunt.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprunt> updateEmprunt(@PathVariable Integer id, @RequestBody EmpruntDto empruntDto) {
        Emprunt updatedEmprunt = empruntService.updateEmprunt(id, empruntDto);

        if (updatedEmprunt != null) {
            return ResponseEntity.ok(updatedEmprunt);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmprunt(@PathVariable Integer id) {
        empruntService.deleteEmprunt(id);
        return ResponseEntity.noContent().build();
    }
}
