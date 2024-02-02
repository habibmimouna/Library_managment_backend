package com.gestion_biblio.gestion_biblio.dtos;

public class JwtAuthenticationResponse {

    private String token;
    private String refreshtoken;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }
}
