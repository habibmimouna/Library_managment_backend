package com.gestion_biblio.gestion_biblio.controllers;

import com.gestion_biblio.gestion_biblio.dtos.JwtAuthenticationResponse;
import com.gestion_biblio.gestion_biblio.dtos.RefreshTokenRequest;
import com.gestion_biblio.gestion_biblio.dtos.SignInRequest;
import com.gestion_biblio.gestion_biblio.dtos.SignUpRequest;
import com.gestion_biblio.gestion_biblio.models.User;
import com.gestion_biblio.gestion_biblio.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path="/api/v1/auth")
public class AuthenticationController {

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
            return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }
    @PostMapping("/signin")

    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) {
        try {
            JwtAuthenticationResponse authResponse = authenticationService.signin(signInRequest);
            return ResponseEntity.ok().body(authResponse); // Return the JWT authentication response
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid email or password"); // Custom error message on authentication failure
        }
    }


    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

}
