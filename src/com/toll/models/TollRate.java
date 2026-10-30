package com.toll.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TollRate {
    public static final List<TollRate> ALL_RATES = new ArrayList<>();
    private int plazaId;
    private String vehicleType, tripType;
    private double amount;
    private LocalDate effectiveDate;

    public TollRate(int plazaId, String vehicleType, String tripType, double amount, LocalDate effectiveDate) {
        this.plazaId = plazaId;
        this.vehicleType = vehicleType;
        this.tripType = tripType;
        this.amount = amount;
        this.effectiveDate = effectiveDate;
    }

    public int getPlazaId() {
        return plazaId;
    }

    public String getVehicleType() {
        return vehicleType;

    }

    public String getTripType() {
        return tripType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    @Override
    public String toString() {
        return vehicleType + " " + tripType + " Rs." + amount + " from " + effectiveDate;
    }
}
