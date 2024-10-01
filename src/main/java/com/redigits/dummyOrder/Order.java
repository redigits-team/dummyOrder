package com.redigits.dummyOrder;

public class Order {
    private int id;
    private int clientId;
    private String product;
    private int quantity;
    private double pricePerUnit;
    private Address shippingAddress;
    private int paymentId;
    private String status;
    private int shipmentId;

    public Order(int id, int clientId, String product, int quantity, double pricePerUnit, Address shippingAddress, int paymentId, String status, int shipmentId) { 
        this.id = id;
        this.clientId = clientId;
        this.product = product;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.shippingAddress = shippingAddress;
        this.paymentId = paymentId;
        this.status = status;
        this.shipmentId = shipmentId;
    }

    // Getters e setters
    public int getId() { return id; }
    public int getClientId() { return clientId; }
    public String getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getPricePerUnit() { return pricePerUnit; }
    public Address getShippingAddress() { return shippingAddress; }
    public int getPaymentId() { return paymentId; }
    public String getStatus() { return status; }
    public int getShipmentId() { return shipmentId; }


}
