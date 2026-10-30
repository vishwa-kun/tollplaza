package com.toll.service;

import java.time.LocalDate;
import com.toll.models.TollRate;
import com.toll.repository.TollRateRepository;

public class TollRateService {
    private final TollRateRepository repository;

    public TollRateService(TollRateRepository repository) {
        this.repository = repository;
    }

    public boolean setRate(boolean isAdmin, TollRate rate) {
        if (!isAdmin || rate.getAmount() <= 0 || rate.getEffectiveDate().isBefore(LocalDate.of(2000, 1, 1)))
            return false;
        repository.add(rate);
        return true;
    }

    public double findRate(int plazaId, String vehicleType, String tripType, LocalDate date) {
        TollRate best = null;
        for (TollRate r : repository.getAll()) {
            if (r.getPlazaId() == plazaId && r.getVehicleType().equalsIgnoreCase(vehicleType)
                    && r.getTripType().equalsIgnoreCase(tripType) && !r.getEffectiveDate().isAfter(date)
                    && (best == null || r.getEffectiveDate().isAfter(best.getEffectiveDate())))
                best = r;
        }
        return best == null ? -1 : best.getAmount();
    }
}
