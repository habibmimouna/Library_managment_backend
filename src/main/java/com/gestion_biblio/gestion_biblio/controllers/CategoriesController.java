package com.gestion_biblio.gestion_biblio.controllers;

import com.gestion_biblio.gestion_biblio.dtos.CategorieDto;
import com.gestion_biblio.gestion_biblio.models.Categories;
import com.gestion_biblio.gestion_biblio.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "api/v1/Categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public ResponseEntity<List<Categories>> getAllCategories() {
        List<Categories> categories = categoriesService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieDto> getCategoryById(@PathVariable Integer id) {
        CategorieDto category = categoriesService.getCategoryById(id);
        return (category != null) ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategorieDto categorieDto) {
        CategorieDto createdCategory = categoriesService.createCategory(categorieDto);

        if (createdCategory != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdCategory.getNom())
                    .toUri();
            return ResponseEntity.created(location).body(createdCategory);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create category");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody CategorieDto updatedCategoryDto) {
        CategorieDto updatedCategory = categoriesService.updateCategory(id, updatedCategoryDto);

        return (updatedCategory != null) ?
                ResponseEntity.ok(updatedCategory) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        categoriesService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
