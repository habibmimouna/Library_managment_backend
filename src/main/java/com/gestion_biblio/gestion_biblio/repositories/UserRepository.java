package com.gestion_biblio.gestion_biblio.repositories;

import com.gestion_biblio.gestion_biblio.models.Role;
import com.gestion_biblio.gestion_biblio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    User findByRole(Role role);
}
