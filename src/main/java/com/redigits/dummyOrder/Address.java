package com.redigits.dummyOrder;

public class Address {
    private String street;
    private String city;
    private String zip;
    private String country;

    public Address(String street, String city, String zip, String country) {
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }

    // Getters e setters
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getZip() { return zip; }
    public String getCountry() { return country; }
}

