package com.example.newtraveloffice.models;

import java.time.LocalDate;

public class AbroadTrip extends Trip {

    private Long insurance;

    public AbroadTrip(LocalDate start, LocalDate end, String destination, Long price, Long insurance, boolean domestic) {
        super(start, end, destination, price, domestic);
        this.insurance = insurance;
    }

    @Override
    public Long getPrice() {
        return (super.getPrice() + insurance);
    }

    public void setInsurance(Long insurance) {
        this.insurance = insurance;
    }
}
