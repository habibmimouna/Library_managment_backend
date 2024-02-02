package com.gestion_biblio.gestion_biblio.dtos;

import java.util.Date;

public class ReservationDto {

    private Date dateReservation;
    private Integer userId;
    private Integer livreId;


    public ReservationDto() {
    }

    public ReservationDto(Date dateReservation, Integer userId, Integer livreId) {
        this.dateReservation = dateReservation;
        this.userId = userId;
        this.livreId = livreId;
    }


    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLivreId() {
        return livreId;
    }

    public void setLivreId(Integer livreId) {
        this.livreId = livreId;
    }
}
