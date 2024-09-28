package com.redigits.dummyOrder;

public class Payment {
    private int id;
    private int orderId;
    private double amount;
    private String currency;
    private String status;
    private String method;
    private String date;

    public Payment(int id, int orderId, double amount, String currency, String status, String method, String date) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.method = method;
        this.date = date;
    }

    // Getters e setters
    public int getId() { return id; }
    public int getOrderId() { return orderId; }
    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getStatus() { return status; }
    public String getMethod() { return method; }
    public String getDate() { return date; }
}
