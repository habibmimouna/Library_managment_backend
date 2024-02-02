package com.gestion_biblio.gestion_biblio.dtos;

public class CategorieDto {

    private String nom;

    public CategorieDto(String nom) {
        this.nom = nom;
    }

    public CategorieDto() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
