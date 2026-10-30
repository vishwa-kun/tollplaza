package com.toll.models;

import java.util.ArrayList;
import java.util.List;

public class TollPlaza {
    private int id;
    private String name, highwayName, location;
    private boolean open;
    private final List<Lane> lanes = new ArrayList<>();

    public TollPlaza(int id, String name, String highwayName, String location) {
        this.id = id;
        this.name = name;
        this.highwayName = highwayName;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHighwayName() {
        return highwayName;
    }

    public String getLocation() {
        return location;
    }

    public boolean isOpen() {
        return open;
    }

    public List<Lane> getLanes() {
        return lanes;
    }

    public void addLane(Lane lane) {
        lanes.add(lane);
    }

    public boolean canOpen() {
        if (lanes.size() < 2)
            return false;
        for (String type : new String[] { "Car", "Van", "Bus", "Truck", "Multi-axle" }) {
            boolean found = false;
            for (TollRate rate : TollRate.ALL_RATES) {
                if (rate.getPlazaId() == id && rate.getVehicleType().equalsIgnoreCase(type))
                    found = true;
            }
            if (!found)
                return false;
        }
        return true;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "Plaza " + id + " | " + name + " | " + highwayName + " | " + location +
                " | lanes=" + lanes.size() + " | " + (open ? "Open" : "Closed");
    }
}
