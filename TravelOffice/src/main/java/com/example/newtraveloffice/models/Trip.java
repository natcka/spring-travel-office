package com.example.newtraveloffice.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Trip {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date end;
    private String destination;
    private Long price;
    private boolean domestic;

    public Trip() {
    }

    public Trip(Date start, Date end, String destination, Long price, boolean domestic) {
        this.start = start;
        this.end = end;
        this.destination = destination;
        this.price = price;
        this.domestic = domestic;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
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
