package com.gestion_biblio.gestion_biblio.dtos;

import java.util.Date;

public class LivreDto {

    private String titre;
    private String auteur;
    private Date datePublication;
    private String isbn;
    private String img ;
    private Integer categoryId;

    // Constructors, getters, and setters

    public LivreDto() {
    }

    public LivreDto(String titre, String auteur, Date datePublication, String isbn, Integer categoryId,String img) {
        this.titre = titre;
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.isbn = isbn;
        this.img= img ;
        this.categoryId = categoryId;
    }

    // Getters and Setters for each attribute

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getImg() {
            return img;
        }
    
        public void setImg(String img) {
            this.img = img;
        }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}