package com.gestion_biblio.gestion_biblio.services.impl;

import com.gestion_biblio.gestion_biblio.dtos.JwtAuthenticationResponse;
import com.gestion_biblio.gestion_biblio.dtos.RefreshTokenRequest;
import com.gestion_biblio.gestion_biblio.dtos.SignInRequest;
import com.gestion_biblio.gestion_biblio.dtos.SignUpRequest;
import com.gestion_biblio.gestion_biblio.models.Role;
import com.gestion_biblio.gestion_biblio.models.User;
import com.gestion_biblio.gestion_biblio.repositories.UserRepository;
import com.gestion_biblio.gestion_biblio.services.AuthenticationService;
import com.gestion_biblio.gestion_biblio.services.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public User signup(SignUpRequest signUpRequest){

        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setUsername(signUpRequest.getUsername());
        user.setAdresse(signUpRequest.getAdresse());
        user.setNuméroTel(signUpRequest.getNuméroTel());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.USER);
        user.setLibraryCard(generateRandomLibraryCard());

    return userRepository.save(user);


    }


    public JwtAuthenticationResponse signin(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));

        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() ->new IllegalArgumentException("Invalid Email or Password"));

        var jwt = jwtService.generateToken(user);
        var refreshtoken = jwtService.generateRefreshToken(new HashMap<>(),user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshtoken(refreshtoken);
        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.ExtractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user))
        {
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshtoken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;

        }
        return null;
    }
    private String generateRandomLibraryCard() {
        // Generate a random 10-digit number as the library card
        Random random = new Random();
        long libraryCardNumber = Math.abs(random.nextLong()) % 10000000000L;
        return String.format("%010d", libraryCardNumber);
    }



}
