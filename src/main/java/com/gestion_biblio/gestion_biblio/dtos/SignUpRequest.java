package com.gestion_biblio.gestion_biblio.dtos;


public class SignUpRequest {
    private String username;
    private String email;
    private String password;

    private Integer NuméroTel;
    private String Adresse;

    public Integer getNuméroTel() {
        return NuméroTel;
    }

    public void setNuméroTel(Integer numéroTel) {
        NuméroTel = numéroTel;
    }

    public String getLibraryCard() {
        return LibraryCard;
    }

    public void setLibraryCard(String libraryCard) {
        LibraryCard = libraryCard;
    }

    private String LibraryCard;

    public SignUpRequest(String username, String email, String password, Integer numéroTel, String adresse, String libraryCard) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.NuméroTel = numéroTel;
        this.Adresse = adresse;
        this.LibraryCard = libraryCard;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }
}
