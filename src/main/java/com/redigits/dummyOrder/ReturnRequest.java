package com.redigits.dummyOrder;

import java.time.LocalDate;

public class ReturnRequest {
    private int id;
    private int orderId;
    private String reason;
    private LocalDate requestDate;
    private String status;

    public ReturnRequest(int id, int orderId, String reason, LocalDate requestDate, String status) {
        this.id = id;
        this.orderId = orderId;
        this.reason = reason;
        this.requestDate = requestDate;
        this.status = status;
    }

    // Getters e setters
    public int getId() { return id; }
    public int getOrderId() { return orderId; }
    public String getReason() { return reason; }
    public LocalDate getRequestDate() { return requestDate; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int i) {
        // TODO Auto-generated method stub
        this.id=i;
    }

    public void setRequestDate(LocalDate now) {
        // TODO Auto-generated method stub
        this.requestDate=now;
    }
}
