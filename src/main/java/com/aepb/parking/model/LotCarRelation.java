package com.aepb.parking.model;

import com.aepb.parking.repo.Table;

public class LotCarRelation implements Table {
    private Long id;
    private Long lotId;
    private String carId;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}
