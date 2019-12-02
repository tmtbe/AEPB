package com.aepb.parking.dto;

import com.aepb.parking.repo.Table;

public class ManagerBoy implements Table {
    private Long id;
    private String name;
    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
