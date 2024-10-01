package com.redigits.dummyOrder;

public class Shipment {
    private String city;
    private String country;
    private String status;
    private String dataPartenza;
    private String dataConsegnaPrevista;
    private int id;
    private int orderId;


    public Shipment(int id,int orderId,String city, String status, String dataPartenza, String dataConsegnaPrevista) {
        this.id = id;
        this.city = city;
        this.status = status;
        this.dataPartenza = dataPartenza;
        this.dataConsegnaPrevista = dataConsegnaPrevista;
        this.orderId = orderId;

    }

    // Getters e setters

    public String getCity() { return city; }
    public String getStatus() { return status; }
    public String getDataPartenza() { return dataPartenza; }
    public String getDataConsegnaPrevista() { return dataConsegnaPrevista; }
    public int getId(){ return id;}
    public int getOrderId(){ return orderId;}


    
}
