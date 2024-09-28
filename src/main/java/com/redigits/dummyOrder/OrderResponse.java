package com.redigits.dummyOrder;

public class OrderResponse {

    private int orderId;
    private String product;
    private int quantity;
    private double totalPrice;
    private Client client;
    private Address shippingAddress;
    private Payment payment;

    public OrderResponse(Order order, Client client, Payment payment) {
        this.orderId = order.getId();
        this.product = order.getProduct();
        this.quantity = order.getQuantity();
        this.totalPrice = order.getQuantity() * order.getPricePerUnit();
        this.client = client;
        this.shippingAddress = order.getShippingAddress();
        this.payment = payment;
    }

    // Getters e setters
    public int getOrderId() { return orderId; }
    public String getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
    public Client getClient() { return client; }
    public Address getShippingAddress() { return shippingAddress; }
    public Payment getPayment() { return payment; }
    
}
