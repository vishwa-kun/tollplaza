package com.toll.models;

public class Vehicle {
    private String registration, type, tagId;
    private double balance;
    private boolean activeTag, blacklisted;

    public Vehicle(String registration, String type, String tagId, double balance, boolean activeTag,
            boolean blacklisted) {
        this.registration = registration;
        this.type = type;
        this.tagId = tagId;
        this.balance = balance;
        this.activeTag = activeTag;
        this.blacklisted = blacklisted;
    }

    public String getRegistration() {
        return registration;
    }

    public String getType() {
        return type;
    }

    public String getTagId() {
        return tagId;
    }

    public double getBalance() {
        return balance;
    }

    public boolean hasActiveTag() {
        return activeTag && !blacklisted;
    }

    public boolean isBlacklisted() {
        return blacklisted;
    }

    public boolean deduct(double amount) {
        if (!hasActiveTag() || amount > balance)
            return false;
        balance -= amount;
        if (balance < 100)
            activeTag = false;
        return true;
    }

    @Override
    public String toString() {
        return registration + " | " + type + " | balance Rs." + balance;
    }
}
