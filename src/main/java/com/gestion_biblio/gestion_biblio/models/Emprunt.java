package com.gestion_biblio.gestion_biblio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Table(name="Emprunt")
@Entity
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    

    @ManyToOne
    @JoinColumn(name = "livre_id")
    private Livre livre;

    @Column(name = "DateDebut")
    private Date dateDebut;

    @Column(name = "DateFin")
    private Date dateFin;

    public Emprunt(Integer id, User user, Livre livre, Date dateDebut, Date dateFin) {
        this.id = id;
        this.user = user;
        this.livre = livre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Emprunt(Integer id) {
        this.id = id;
    }

    public Emprunt() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
