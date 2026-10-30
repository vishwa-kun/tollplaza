package com.toll.repository;

import java.util.*;
import com.toll.models.PaymentDispute;

public class DisputeRepository {
    private final List<PaymentDispute> disputes = new ArrayList<>();

    public void add(PaymentDispute d) {
        disputes.add(d);
    }

    public List<PaymentDispute> all() {
        return disputes;
    }
}
