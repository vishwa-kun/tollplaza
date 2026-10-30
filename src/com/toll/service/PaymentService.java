package com.toll.service;

import java.util.*;
import com.toll.models.*;
import com.toll.repository.PaymentRepository;

public class PaymentService {
    private final PaymentRepository repository;
    private final Map<String, Double> balances = new HashMap<>();
    private final List<String> alerts = new ArrayList<>();
    private final Map<String, Integer> laneCounts = new HashMap<>();

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public void setBalance(String tag, double balance) {
        if (balance >= 0)
            balances.put(tag, balance);
    }

    public double getBalance(String tag) {
        return balances.getOrDefault(tag, 0.0);
    }

    public boolean recharge(String tag, double amount, String method, String status) {
        if (!Arrays.asList("UPI", "Card", "Net Banking").contains(method) || amount < 100 || amount > 10000)
            return false;
        double balance = getBalance(tag);
        if (status.equals("Paid") && balance + amount > 50000)
            return false;
        repository.addRecharge(new RechargeRecord(tag, amount, method, status));
        if (status.equals("Paid"))
            balances.put(tag, balance + amount);
        return true;
    }

    public boolean recordCash(String reg, String plaza, double amount, String operator) {
        if (amount < 0 || operator.isBlank())
            return false;
        repository.addCash(new CashPayment(reg, plaza, amount, operator));
        return true;
    }

    public void afterDeduction(String tag, double amount) {
        double balance = getBalance(tag) - amount;
        if (balance < 0)
            balance = 0;
        balances.put(tag, balance);
        if (balance < 200)
            alerts.add("LOW BALANCE: " + tag + " has Rs." + balance + " remaining.");
    }

    public List<String> alerts() {
        return alerts;
    }

    public void recordLaneVehicle(String plaza, String lane) {
        String key = plaza + "|" + lane;
        laneCounts.put(key, laneCounts.getOrDefault(key, 0) + 1);
    }

    public double dailyCashRevenue(String plaza, java.time.LocalDate date) {
        double total = 0;
        for (CashPayment p : repository.cashPayments()) {
            if (p.getPlaza().equalsIgnoreCase(plaza) && p.getDate().toLocalDate().equals(date))
                total += p.getAmount();
        }
        return total;
    }

    public Map<String, Integer> laneVehicleCounts() {
        return laneCounts;
    }

    public PaymentRepository repository() {
        return repository;
    }
}
