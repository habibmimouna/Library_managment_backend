package com.gestion_biblio.gestion_biblio.dtos;

public class RefreshTokenRequest {

    private String token;

    public RefreshTokenRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
