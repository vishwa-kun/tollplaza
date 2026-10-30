package com.toll.service;

import java.util.Collection;
import com.toll.models.Vehicle;
import com.toll.repository.VehicleRepository;

public class VehicleService {
    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public boolean register(Vehicle v) {
        if (v.getTagId() == null || v.getTagId().isBlank() || v.getRegistration().isBlank())
            return false;
        if (repository.findByTag(v.getTagId()) != null)
            return false;
        repository.add(v);
        return true;
    }

    public Collection<Vehicle> all() {
        return repository.all();
    }
}
