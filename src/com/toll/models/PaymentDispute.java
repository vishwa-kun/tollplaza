package com.toll.models;

import java.time.LocalDate;

public class PaymentDispute {
    private String transactionId, registration, reason, status;
    private double amount;
    private LocalDate date;

    public PaymentDispute(String transactionId, String registration, double amount, String reason) {
        this.transactionId = transactionId;
        this.registration = registration;
        this.amount = amount;
        this.reason = reason;
        this.date = LocalDate.now();
        this.status = "Pending";
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getRegistration() {
        return registration;
    }

    public double getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return transactionId + " | " + registration + " | Rs." + amount + " | " + status + " | " + reason + " | "
                + date;
    }
}
