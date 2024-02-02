package com.gestion_biblio.gestion_biblio.repositories;

import com.gestion_biblio.gestion_biblio.models.Categories;
import com.gestion_biblio.gestion_biblio.models.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivreRepository extends JpaRepository<Livre,Integer> {
    List<Livre> findByCategories(Categories categories);

}
