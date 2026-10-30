package com.toll.service;

import java.util.List;
import com.toll.models.TollPlaza;
import com.toll.models.Lane;
import com.toll.repository.TollPlazaRepository;

public class TollPlazaService {
    private final TollPlazaRepository repository;

    public TollPlazaService(TollPlazaRepository repository) {
        this.repository = repository;
    }

    public boolean createPlaza(boolean isAdmin, TollPlaza plaza) {
        if (!isAdmin || plaza.getName().isBlank() || plaza.getLocation().isBlank())
            return false;
        if (repository.findById(plaza.getId()) != null)
            return false;
        repository.add(plaza);
        return true;
    }

    public boolean addLane(boolean isAdmin, int plazaId, Lane lane) {
        TollPlaza plaza = repository.findById(plazaId);
        if (!isAdmin || plaza == null)
            return false;
        for (Lane existing : plaza.getLanes())
            if (existing.getNumber() == lane.getNumber())
                return false;
        plaza.addLane(lane);
        return true;
    }

    public boolean setPlazaOpen(int id, boolean open) {
        TollPlaza plaza = repository.findById(id);
        if (plaza == null)
            return false;
        if (open && !plaza.canOpen())
            return false;
        plaza.setOpen(open);
        return true;
    }

    public boolean closeLane(int plazaId, int laneNo) {
        TollPlaza plaza = repository.findById(plazaId);
        if (plaza == null)
            return false;
        for (Lane lane : plaza.getLanes())
            if (lane.getNumber() == laneNo)
                return lane.close();
        return false;
    }

    public List<TollPlaza> getAll() {
        return repository.getAll();
    }
}
