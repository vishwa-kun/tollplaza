package com.toll.repository;

import java.util.ArrayList;
import java.util.List;
import com.toll.models.Lane;

public class LaneRepository {
    private final List<Lane> lanes = new ArrayList<>();

    public void add(Lane lane) {
        lanes.add(lane);
    }

    public List<Lane> getAll() {
        return lanes;
    }
}
