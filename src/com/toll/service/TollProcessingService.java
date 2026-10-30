package com.toll.service;

import java.util.UUID;
import java.time.LocalDateTime;
import com.toll.models.*;
import com.toll.repository.*;

public class TollProcessingService {
    private final VehicleRepository vehicles;
    private final TransactionRepository transactions;

    public TollProcessingService(VehicleRepository vehicles, TransactionRepository transactions) {
        this.vehicles = vehicles;
        this.transactions = transactions;
    }

    public TollTransaction process(String tag, String observedType, String plaza, String lane, double normalToll,
            boolean emergency, String cashChoice) {
        Vehicle v = vehicles.findByTag(tag);
        String reg = v == null ? "UNKNOWN" : v.getRegistration();
        if (emergency)
            return save(reg, plaza, lane, 0, "Success", "Emergency vehicle: no charge");
        if (v == null || !v.hasActiveTag()) {
            double cash = normalToll * 2;
            if ("cash".equalsIgnoreCase(cashChoice))
                return save(reg, plaza, "Cash", cash, "Success", "No valid active tag; double toll paid in cash");
            return save(reg, plaza, lane, 0, "Pending", "Vehicle sent to cash lane; cash payment required");
        }
        if (transactions.chargedRecently(tag, plaza, LocalDateTime.now()))
            return save(reg, plaza, lane, 0, "Failed", "Duplicate tag crossing within 2 minutes");
        double amount = normalToll;
        String note = "FASTag deduction";
        if (!v.getType().equalsIgnoreCase(observedType)) {
            amount = Math.max(normalToll, normalToll * 1.5);
            note = "Vehicle type mismatch; flagged for review";
        }
        if (!v.deduct(amount)) {
            if ("cash".equalsIgnoreCase(cashChoice))
                return save(reg, plaza, "Cash", normalToll, "Success",
                        "Insufficient balance; normal toll paid in cash");
            return save(reg, plaza, lane, 0, "Pending", "Insufficient balance; sent to cash lane");
        }
        return save(reg, plaza, lane, amount, "Success", note);
    }

    private TollTransaction save(String reg, String plaza, String lane, double amount, String status, String note) {
        TollTransaction t = new TollTransaction("TX-" + UUID.randomUUID().toString().substring(0, 8), reg, plaza, lane,
                amount, status, note);
        transactions.add(t);
        return t;
    }
}
