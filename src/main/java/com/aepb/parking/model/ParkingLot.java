package com.aepb.parking.model;

import com.aepb.parking.repo.Table;

public class ParkingLot implements Table {
    private Long id;
    private String name;
    private Long maxCapacity;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Long maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
