package com.toll.models;

import java.time.LocalDateTime;

public class CashPayment {
    private String registration, plaza, laneOperator;
    private double amount;
    private LocalDateTime date;

    public CashPayment(String registration, String plaza, double amount, String laneOperator) {
        this.registration = registration;
        this.plaza = plaza;
        this.amount = amount;
        this.laneOperator = laneOperator;
        this.date = LocalDateTime.now();
    }

    public String getPlaza() {
        return plaza;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getLaneOperator() {
        return laneOperator;
    }

    @Override
    public String toString() {
        return registration + " | " + plaza + " | Rs." + amount + " | operator: " + laneOperator + " | " + date;
    }
}
