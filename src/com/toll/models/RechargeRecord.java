package com.toll.models;

import java.time.LocalDateTime;

public class RechargeRecord {
    private String tagId, method, status;
    private double amount;
    private LocalDateTime date;

    public RechargeRecord(String tagId, double amount, String method, String status) {
        this.tagId = tagId;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.date = LocalDateTime.now();
    }

    public String getTagId() {
        return tagId;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return tagId + " | Rs." + amount + " | " + method + " | " + status + " | " + date;
    }
}
