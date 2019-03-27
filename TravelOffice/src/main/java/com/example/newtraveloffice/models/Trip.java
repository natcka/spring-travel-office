package com.example.newtraveloffice.models;

import java.time.LocalDate;

public class Trip {

    private LocalDate start;
    private LocalDate end;
    private String destination;
    private Long price;
    private boolean domestic;

    public Trip() {
    }

    public Trip(LocalDate start, LocalDate end, String destination, Long price, boolean domestic) {
        this.start = start;
        this.end = end;
        this.destination = destination;
        this.price = price;
        this.domestic = domestic;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public boolean isDomestic() {
        return domestic;
    }

    public void setDomestic(boolean domestic) {
        this.domestic = domestic;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Wycieczka ");
        if (domestic) {
            sb.append("krajowa: \n");
        } else {
            sb.append("zagraniczna: \n");
        }
        if (destination != null) {
            sb.append("\tdestynacja: " + destination + ",\n");
        }
        if (start != null) {
            sb.append("\tod: " + start.toString() + ",\n");
        }
        if (end != null) {
            sb.append("\tdo: " + end.toString() + ",\n");
        }
        if (price != null) {
            sb.append("\tcena: " + price.toString() + ",\n");
        }
        return sb.toString();
    }
}
