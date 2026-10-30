package com.toll.repository;

import java.util.List;
import com.toll.models.TollRate;

public class TollRateRepository {
    public void add(TollRate rate) {
        TollRate.ALL_RATES.add(rate);
    }

    public List<TollRate> getAll() {
        return TollRate.ALL_RATES;
    }
}
