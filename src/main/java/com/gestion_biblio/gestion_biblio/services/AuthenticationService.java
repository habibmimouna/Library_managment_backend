package com.gestion_biblio.gestion_biblio.services;

import com.gestion_biblio.gestion_biblio.dtos.JwtAuthenticationResponse;
import com.gestion_biblio.gestion_biblio.dtos.RefreshTokenRequest;
import com.gestion_biblio.gestion_biblio.dtos.SignInRequest;
import com.gestion_biblio.gestion_biblio.dtos.SignUpRequest;
import com.gestion_biblio.gestion_biblio.models.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);
    public JwtAuthenticationResponse signin(SignInRequest signInRequest);

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
