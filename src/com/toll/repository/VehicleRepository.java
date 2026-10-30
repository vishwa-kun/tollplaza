package com.toll.repository;

import java.util.*;
import com.toll.models.Vehicle;

public class VehicleRepository {
    private final Map<String, Vehicle> vehicles = new LinkedHashMap<>();

    public void add(Vehicle v) {
        vehicles.put(v.getTagId(), v);
    }

    public Vehicle findByTag(String tag) {
        return vehicles.get(tag);
    }

    public Collection<Vehicle> all() {
        return vehicles.values();
    }
}
