package com.toll.repository;

import java.util.ArrayList;
import java.util.List;
import com.toll.models.TollPlaza;

public class TollPlazaRepository {
    private final List<TollPlaza> plazas = new ArrayList<>();

    public List<TollPlaza> getAll() {
        return plazas;
    }

    public void add(TollPlaza plaza) {
        plazas.add(plaza);
    }

    public TollPlaza findById(int id) {
        for (TollPlaza p : plazas)
            if (p.getId() == id)
                return p;
        return null;
    }
}
