package com.toll.service;

import java.time.LocalDate;
import com.toll.models.PaymentDispute;
import com.toll.repository.DisputeRepository;

public class DisputeService {
    private final DisputeRepository repository;
    private final PaymentService payments;

    public DisputeService(DisputeRepository repository, PaymentService payments) {
        this.repository = repository;
        this.payments = payments;
    }

    public boolean fileDispute(String tx, String reg, double amount, String reason, LocalDate transactionDate) {
        if (transactionDate == null || transactionDate.isBefore(LocalDate.now().minusDays(7))
                || transactionDate.isAfter(LocalDate.now()) || amount <= 0)
            return false;
        repository.add(new PaymentDispute(tx, reg, amount, reason));
        return true;
    }

    public boolean decide(String tx, boolean approve, boolean admin, String tagId) {
        if (!admin)
            return false;
        for (PaymentDispute d : repository.all())
            if (d.getTransactionId().equals(tx) && d.getStatus().equals("Pending")) {
                if (approve) {
                    payments.setBalance(tagId, payments.getBalance(tagId) + d.getAmount());
                    d.setStatus("Approved - Refunded");
                } else
                    d.setStatus("Rejected");
                return true;
            }
        return false;
    }

    public java.util.List<PaymentDispute> all() {
        return repository.all();
    }
}
