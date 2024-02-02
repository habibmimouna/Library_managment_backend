package com.gestion_biblio.gestion_biblio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.Category;

import javax.xml.catalog.Catalog;
import java.util.Date;
import java.util.List;

@Table(name = "Livre")
@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "auteur")
    private String auteur;

    @Column(name = "DatePublication")
    private Date datePublication;

    @Column(name = "isbn")
    private String isbn;
    @JsonBackReference

    @Column(name = "img")
    private String img;
    @JsonBackReference

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categories;

    @JsonBackReference
    @OneToMany(mappedBy = "livre")
    private List<Reservations> reservations;

    public Livre(Integer id, String titre, String auteur, Date datePublication, String isbn, String img,
            Categories categories,
            List<Reservations> reservations) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.isbn = isbn;
        this.img = img;
        this.categories = categories;
        this.reservations = reservations;
    }

    public Livre() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public List<Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservations> reservations) {
        this.reservations = reservations;
    }
}
