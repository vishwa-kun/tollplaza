package com.toll.models;

public class TollLane {
    private String plaza, name, type;

    public TollLane(String plaza, String name, String type) {
        this.plaza = plaza;
        this.name = name;
        this.type = type;
    }

    public String getPlaza() {
        return plaza;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return plaza + " / " + name + " / " + type;
    }
}
