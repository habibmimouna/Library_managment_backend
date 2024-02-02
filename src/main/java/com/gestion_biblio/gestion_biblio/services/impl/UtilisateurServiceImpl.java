package com.gestion_biblio.gestion_biblio.services.impl;

import com.gestion_biblio.gestion_biblio.models.User;
import com.gestion_biblio.gestion_biblio.repositories.UserRepository;
import com.gestion_biblio.gestion_biblio.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UtilisateurServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));
    }
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



}



