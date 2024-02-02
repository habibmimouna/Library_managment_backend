package com.gestion_biblio.gestion_biblio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;
@Table(name="Reservations")//nom de la table
@Entity//obligatoire
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "DateReservation")
    private Date dateReservation;

    

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    

    @ManyToOne()
    @JoinColumn(name = "livre_id")
    private Livre livre;

    public Reservations(Integer id, User user, Livre livre, Date dateReservation) {
        this.id = id;
        this.user = user;
        this.livre = livre;
        this.dateReservation = dateReservation;
    }

    public Reservations() {
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

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }
}
