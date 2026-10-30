package com.toll.models;

import java.time.LocalDateTime;

public class TollTransaction {
    private final String id, registration, plaza, lane, status, note;
    private final double amount;
    private final LocalDateTime time;

    public TollTransaction(String id, String registration, String plaza, String lane, double amount, String status,
            String note) {
        this.id = id;
        this.registration = registration;
        this.plaza = plaza;
        this.lane = lane;
        this.amount = amount;
        this.status = status;
        this.note = note;
        this.time = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getRegistration() {
        return registration;
    }

    public String getPlaza() {
        return plaza;
    }

    public String getLane() {
        return lane;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return id + " | " + registration + " | " + plaza + " | " + lane + " | Rs." + amount + " | " + status + " | "
                + note + " | " + time;
    }
}
