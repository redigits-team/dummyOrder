package com.redigits.dummyOrder;

public class Client {
    private int id;
    private String name;
    private String email;
    private String phone;
    private Address address;

    public Client(int id, String name, String email, String phone, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getters e setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Address getAddress() { return address; }
}
