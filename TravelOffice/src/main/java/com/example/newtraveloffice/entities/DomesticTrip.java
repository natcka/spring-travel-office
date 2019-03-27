package com.example.newtraveloffice.entities;

import javax.persistence.Entity;

@Entity
public class DomesticTrip extends Trip {

    private Long ownArrivalDiscount;

    public Long getOwnArrivalDiscount() {
        return ownArrivalDiscount;
    }

    public void setOwnArrivalDiscount(Long ownArrivalDiscount) {
        this.ownArrivalDiscount = ownArrivalDiscount;
    }
}
