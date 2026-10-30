package com.toll.repository;

import java.util.*;
import com.toll.models.TollTransaction;

public class TransactionRepository {
    private final List<TollTransaction> records = new ArrayList<>();

    public void add(TollTransaction t) {
        records.add(t);
    }

    public List<TollTransaction> all() {
        return records;
    }

    public boolean chargedRecently(String tag, String plaza, java.time.LocalDateTime now) {
        for (TollTransaction t : records)
            if (t.getRegistration().equalsIgnoreCase(tag) && t.getPlaza().equalsIgnoreCase(plaza)
                    && t.getStatus().equals("Success") && t.getTime().isAfter(now.minusMinutes(2)))
                return true;
        return false;
    }
}
