package com.toll.repository;

import java.util.*;
import com.toll.models.*;

public class PaymentRepository {
    private final List<RechargeRecord> recharges = new ArrayList<>();
    private final List<CashPayment> cashPayments = new ArrayList<>();

    public void addRecharge(RechargeRecord r) {
        recharges.add(r);
    }

    public void addCash(CashPayment p) {
        cashPayments.add(p);
    }

    public List<RechargeRecord> recharges() {
        return recharges;
    }

    public List<CashPayment> cashPayments() {
        return cashPayments;
    }
}
