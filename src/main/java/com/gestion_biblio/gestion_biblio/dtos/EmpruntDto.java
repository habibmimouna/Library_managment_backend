package com.gestion_biblio.gestion_biblio.dtos;

import com.gestion_biblio.gestion_biblio.models.Livre;
import com.gestion_biblio.gestion_biblio.models.User;
import jakarta.persistence.*;

import java.util.Date;

public class EmpruntDto {



    private Date dateDebut;

    private Date dateFin;
    private Integer UserId;

    private Integer LivreId;


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

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public Integer getLivreId() {
        return LivreId;
    }

    public void setLivreId(Integer livreId) {
        LivreId = livreId;
    }
}
