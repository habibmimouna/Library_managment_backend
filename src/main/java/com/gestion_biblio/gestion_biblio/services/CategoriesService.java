package com.gestion_biblio.gestion_biblio.services;

import com.gestion_biblio.gestion_biblio.dtos.CategorieDto;
import com.gestion_biblio.gestion_biblio.models.Categories;
import com.gestion_biblio.gestion_biblio.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<Categories> getAllCategories() {
        List<Categories> categoriesList = categoriesRepository.findAll();
        return categoriesList;
    }

    public CategorieDto getCategoryById(Integer categoryId) {
        Categories category = categoriesRepository.findById(categoryId).orElse(null);
        return (category != null) ? mapToDto(category) : null;
    }

    public CategorieDto createCategory(CategorieDto categorieDto) {
        Categories newCategory = new Categories();
        mapDtoToCategory(categorieDto, newCategory);
        Categories savedCategory = categoriesRepository.save(newCategory);
        return mapToDto(savedCategory);
    }

    public CategorieDto updateCategory(Integer categoryId, CategorieDto updatedCategoryDto) {
        Categories existingCategory = categoriesRepository.findById(categoryId).orElse(null);

        if (existingCategory != null) {
            mapDtoToCategory(updatedCategoryDto, existingCategory);
            Categories updatedCategory = categoriesRepository.save(existingCategory);
            return mapToDto(updatedCategory);
        } else {
            return null;
        }
    }

    public void deleteCategory(Integer categoryId) {
        categoriesRepository.deleteById(categoryId);
    }

    private CategorieDto mapToDto(Categories category) {
        return new CategorieDto(category.getNom());
    }

    private void mapDtoToCategory(CategorieDto categorieDto, Categories category) {
        category.setNom(categorieDto.getNom());
    }
}
