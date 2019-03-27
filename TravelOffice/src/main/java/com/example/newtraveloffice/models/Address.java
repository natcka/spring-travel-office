package com.example.newtraveloffice.models;

public class Address {

    private String street;
    private String zip;
    private String city;

    public Address(String street, String zip, String city) {
        this.street = street;
        this.zip = zip;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Adres: \n");
        if (street != null) {
            sb.append("\tulica: " + street);
        }
        if (city != null) {
            sb.append(",\n\tmiasto: " + city);
        }
        if (zip != null) {
            sb.append(",\n\tkod: " + zip);
        }
        return sb.toString();
    }
}
