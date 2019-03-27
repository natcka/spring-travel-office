package com.example.newtraveloffice.entities;

import javax.persistence.Entity;

@Entity
public class AbroadTrip extends Trip {

    private Long insurance;

    public Long getInsurance() {
        return insurance;
    }

    public void setInsurance(Long insurance) {
        this.insurance = insurance;
    }
}
