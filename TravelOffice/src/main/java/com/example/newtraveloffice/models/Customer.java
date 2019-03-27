package com.example.newtraveloffice.models;

public class Customer {

    private String name;
    private String surname;
    private Address address;
    private Trip trip;

    public Customer() {
    }

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Trip getTrip() {
        return trip;
    }

    public void assignTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (name != null) {
            sb.append("\nKlient: " + name + " " + surname);
        }
        if (address != null) {
            sb.append(",\n" + address.toString());
        }
        if (trip != null) {
            sb.append(",\n" + trip.toString());
            sb.append("\nFinal price: " + trip.getPrice().toString() + "*\n*all extra costs and discounts included");
        }
        return sb.toString();
    }
}
